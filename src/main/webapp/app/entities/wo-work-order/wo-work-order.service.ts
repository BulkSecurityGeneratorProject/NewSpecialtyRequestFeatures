import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';

type EntityResponseType = HttpResponse<IWoWorkOrder>;
type EntityArrayResponseType = HttpResponse<IWoWorkOrder[]>;

@Injectable({ providedIn: 'root' })
export class WoWorkOrderService {
  public resourceUrl = SERVER_API_URL + 'api/wo-work-orders';

  constructor(protected http: HttpClient) {}

  create(woWorkOrder: IWoWorkOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(woWorkOrder);
    return this.http
      .post<IWoWorkOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(woWorkOrder: IWoWorkOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(woWorkOrder);
    return this.http
      .put<IWoWorkOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWoWorkOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWoWorkOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(woWorkOrder: IWoWorkOrder): IWoWorkOrder {
    const copy: IWoWorkOrder = Object.assign({}, woWorkOrder, {
      dateCreated: woWorkOrder.dateCreated != null && woWorkOrder.dateCreated.isValid() ? woWorkOrder.dateCreated.toJSON() : null,
      shippingDate:
        woWorkOrder.shippingDate != null && woWorkOrder.shippingDate.isValid() ? woWorkOrder.shippingDate.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreated = res.body.dateCreated != null ? moment(res.body.dateCreated) : null;
      res.body.shippingDate = res.body.shippingDate != null ? moment(res.body.shippingDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((woWorkOrder: IWoWorkOrder) => {
        woWorkOrder.dateCreated = woWorkOrder.dateCreated != null ? moment(woWorkOrder.dateCreated) : null;
        woWorkOrder.shippingDate = woWorkOrder.shippingDate != null ? moment(woWorkOrder.shippingDate) : null;
      });
    }
    return res;
  }
}
