import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoShippingInfo } from 'app/shared/model/wo-shipping-info.model';
import { WoShippingInfoService } from './wo-shipping-info.service';
import { WoShippingInfoComponent } from './wo-shipping-info.component';
import { WoShippingInfoDetailComponent } from './wo-shipping-info-detail.component';
import { WoShippingInfoUpdateComponent } from './wo-shipping-info-update.component';
import { WoShippingInfoDeletePopupComponent } from './wo-shipping-info-delete-dialog.component';
import { IWoShippingInfo } from 'app/shared/model/wo-shipping-info.model';

@Injectable({ providedIn: 'root' })
export class WoShippingInfoResolve implements Resolve<IWoShippingInfo> {
  constructor(private service: WoShippingInfoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoShippingInfo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoShippingInfo>) => response.ok),
        map((woShippingInfo: HttpResponse<WoShippingInfo>) => woShippingInfo.body)
      );
    }
    return of(new WoShippingInfo());
  }
}

export const woShippingInfoRoute: Routes = [
  {
    path: '',
    component: WoShippingInfoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingInfos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoShippingInfoDetailComponent,
    resolve: {
      woShippingInfo: WoShippingInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingInfos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoShippingInfoUpdateComponent,
    resolve: {
      woShippingInfo: WoShippingInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingInfos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoShippingInfoUpdateComponent,
    resolve: {
      woShippingInfo: WoShippingInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingInfos'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woShippingInfoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoShippingInfoDeletePopupComponent,
    resolve: {
      woShippingInfo: WoShippingInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingInfos'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
