import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoStorage } from 'app/shared/model/wo-storage.model';

type EntityResponseType = HttpResponse<IWoStorage>;
type EntityArrayResponseType = HttpResponse<IWoStorage[]>;

@Injectable({ providedIn: 'root' })
export class WoStorageService {
  public resourceUrl = SERVER_API_URL + 'api/wo-storages';

  constructor(protected http: HttpClient) {}

  create(woStorage: IWoStorage): Observable<EntityResponseType> {
    return this.http.post<IWoStorage>(this.resourceUrl, woStorage, { observe: 'response' });
  }

  update(woStorage: IWoStorage): Observable<EntityResponseType> {
    return this.http.put<IWoStorage>(this.resourceUrl, woStorage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoStorage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoStorage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
