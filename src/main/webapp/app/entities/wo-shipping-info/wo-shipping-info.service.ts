import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoShippingInfo } from 'app/shared/model/wo-shipping-info.model';

type EntityResponseType = HttpResponse<IWoShippingInfo>;
type EntityArrayResponseType = HttpResponse<IWoShippingInfo[]>;

@Injectable({ providedIn: 'root' })
export class WoShippingInfoService {
  public resourceUrl = SERVER_API_URL + 'api/wo-shipping-infos';

  constructor(protected http: HttpClient) {}

  create(woShippingInfo: IWoShippingInfo): Observable<EntityResponseType> {
    return this.http.post<IWoShippingInfo>(this.resourceUrl, woShippingInfo, { observe: 'response' });
  }

  update(woShippingInfo: IWoShippingInfo): Observable<EntityResponseType> {
    return this.http.put<IWoShippingInfo>(this.resourceUrl, woShippingInfo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoShippingInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoShippingInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
