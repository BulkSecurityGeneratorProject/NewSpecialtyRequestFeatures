import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IWoPackageType } from 'app/shared/model/wo-package-type.model';

type EntityResponseType = HttpResponse<IWoPackageType>;
type EntityArrayResponseType = HttpResponse<IWoPackageType[]>;

@Injectable({ providedIn: 'root' })
export class WoPackageTypeService {
  public resourceUrl = SERVER_API_URL + 'api/wo-package-types';

  constructor(protected http: HttpClient) {}

  create(woPackageType: IWoPackageType): Observable<EntityResponseType> {
    return this.http.post<IWoPackageType>(this.resourceUrl, woPackageType, { observe: 'response' });
  }

  update(woPackageType: IWoPackageType): Observable<EntityResponseType> {
    return this.http.put<IWoPackageType>(this.resourceUrl, woPackageType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoPackageType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoPackageType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
