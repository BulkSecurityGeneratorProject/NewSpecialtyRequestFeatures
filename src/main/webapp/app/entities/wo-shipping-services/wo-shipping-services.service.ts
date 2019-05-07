import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoShippingServices } from 'app/shared/model/wo-shipping-services.model';

type EntityResponseType = HttpResponse<IWoShippingServices>;
type EntityArrayResponseType = HttpResponse<IWoShippingServices[]>;

@Injectable({ providedIn: 'root' })
export class WoShippingServicesService {
  public resourceUrl = SERVER_API_URL + 'api/wo-shipping-services';

  constructor(protected http: HttpClient) {}

  create(woShippingServices: IWoShippingServices): Observable<EntityResponseType> {
    return this.http.post<IWoShippingServices>(this.resourceUrl, woShippingServices, { observe: 'response' });
  }

  update(woShippingServices: IWoShippingServices): Observable<EntityResponseType> {
    return this.http.put<IWoShippingServices>(this.resourceUrl, woShippingServices, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoShippingServices>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoShippingServices[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
