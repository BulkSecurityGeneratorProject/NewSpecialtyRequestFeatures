import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoStorage } from 'app/shared/model/wo-storage.model';
import { WoStorageService } from './wo-storage.service';
import { WoStorageComponent } from './wo-storage.component';
import { WoStorageDetailComponent } from './wo-storage-detail.component';
import { WoStorageUpdateComponent } from './wo-storage-update.component';
import { WoStorageDeletePopupComponent } from './wo-storage-delete-dialog.component';
import { IWoStorage } from 'app/shared/model/wo-storage.model';

@Injectable({ providedIn: 'root' })
export class WoStorageResolve implements Resolve<IWoStorage> {
  constructor(private service: WoStorageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoStorage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoStorage>) => response.ok),
        map((woStorage: HttpResponse<WoStorage>) => woStorage.body)
      );
    }
    return of(new WoStorage());
  }
}

export const woStorageRoute: Routes = [
  {
    path: '',
    component: WoStorageComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoStorages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoStorageDetailComponent,
    resolve: {
      woStorage: WoStorageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoStorages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoStorageUpdateComponent,
    resolve: {
      woStorage: WoStorageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoStorages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoStorageUpdateComponent,
    resolve: {
      woStorage: WoStorageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoStorages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woStoragePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoStorageDeletePopupComponent,
    resolve: {
      woStorage: WoStorageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoStorages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
