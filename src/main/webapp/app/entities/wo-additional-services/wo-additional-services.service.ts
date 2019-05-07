import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoAdditionalServices } from 'app/shared/model/wo-additional-services.model';

type EntityResponseType = HttpResponse<IWoAdditionalServices>;
type EntityArrayResponseType = HttpResponse<IWoAdditionalServices[]>;

@Injectable({ providedIn: 'root' })
export class WoAdditionalServicesService {
  public resourceUrl = SERVER_API_URL + 'api/wo-additional-services';

  constructor(protected http: HttpClient) {}

  create(woAdditionalServices: IWoAdditionalServices): Observable<EntityResponseType> {
    return this.http.post<IWoAdditionalServices>(this.resourceUrl, woAdditionalServices, { observe: 'response' });
  }

  update(woAdditionalServices: IWoAdditionalServices): Observable<EntityResponseType> {
    return this.http.put<IWoAdditionalServices>(this.resourceUrl, woAdditionalServices, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoAdditionalServices>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoAdditionalServices[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
