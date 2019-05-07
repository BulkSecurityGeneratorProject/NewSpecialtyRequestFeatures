import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoPickPack } from 'app/shared/model/wo-pick-pack.model';

type EntityResponseType = HttpResponse<IWoPickPack>;
type EntityArrayResponseType = HttpResponse<IWoPickPack[]>;

@Injectable({ providedIn: 'root' })
export class WoPickPackService {
  public resourceUrl = SERVER_API_URL + 'api/wo-pick-packs';

  constructor(protected http: HttpClient) {}

  create(woPickPack: IWoPickPack): Observable<EntityResponseType> {
    return this.http.post<IWoPickPack>(this.resourceUrl, woPickPack, { observe: 'response' });
  }

  update(woPickPack: IWoPickPack): Observable<EntityResponseType> {
    return this.http.put<IWoPickPack>(this.resourceUrl, woPickPack, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoPickPack>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoPickPack[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
