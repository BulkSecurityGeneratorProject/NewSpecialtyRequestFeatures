import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';

type EntityResponseType = HttpResponse<IWoCustomsBrokerage>;
type EntityArrayResponseType = HttpResponse<IWoCustomsBrokerage[]>;

@Injectable({ providedIn: 'root' })
export class WoCustomsBrokerageService {
  public resourceUrl = SERVER_API_URL + 'api/wo-customs-brokerages';

  constructor(protected http: HttpClient) {}

  create(woCustomsBrokerage: IWoCustomsBrokerage): Observable<EntityResponseType> {
    return this.http.post<IWoCustomsBrokerage>(this.resourceUrl, woCustomsBrokerage, { observe: 'response' });
  }

  update(woCustomsBrokerage: IWoCustomsBrokerage): Observable<EntityResponseType> {
    return this.http.put<IWoCustomsBrokerage>(this.resourceUrl, woCustomsBrokerage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoCustomsBrokerage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoCustomsBrokerage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
