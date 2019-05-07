import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from './wo-package-type.service';
import { WoPackageTypeComponent } from './wo-package-type.component';
import { WoPackageTypeDetailComponent } from './wo-package-type-detail.component';
import { WoPackageTypeUpdateComponent } from './wo-package-type-update.component';
import { WoPackageTypeDeletePopupComponent } from './wo-package-type-delete-dialog.component';
import { IWoPackageType } from 'app/shared/model/wo-package-type.model';

@Injectable({ providedIn: 'root' })
export class WoPackageTypeResolve implements Resolve<IWoPackageType> {
  constructor(private service: WoPackageTypeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoPackageType> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoPackageType>) => response.ok),
        map((woPackageType: HttpResponse<WoPackageType>) => woPackageType.body)
      );
    }
    return of(new WoPackageType());
  }
}

export const woPackageTypeRoute: Routes = [
  {
    path: '',
    component: WoPackageTypeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPackageTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoPackageTypeDetailComponent,
    resolve: {
      woPackageType: WoPackageTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPackageTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoPackageTypeUpdateComponent,
    resolve: {
      woPackageType: WoPackageTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPackageTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoPackageTypeUpdateComponent,
    resolve: {
      woPackageType: WoPackageTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPackageTypes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woPackageTypePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoPackageTypeDeletePopupComponent,
    resolve: {
      woPackageType: WoPackageTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPackageTypes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
