import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoShippingServices } from 'app/shared/model/wo-shipping-services.model';
import { WoShippingServicesService } from './wo-shipping-services.service';
import { WoShippingServicesComponent } from './wo-shipping-services.component';
import { WoShippingServicesDetailComponent } from './wo-shipping-services-detail.component';
import { WoShippingServicesUpdateComponent } from './wo-shipping-services-update.component';
import { WoShippingServicesDeletePopupComponent } from './wo-shipping-services-delete-dialog.component';
import { IWoShippingServices } from 'app/shared/model/wo-shipping-services.model';

@Injectable({ providedIn: 'root' })
export class WoShippingServicesResolve implements Resolve<IWoShippingServices> {
  constructor(private service: WoShippingServicesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoShippingServices> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoShippingServices>) => response.ok),
        map((woShippingServices: HttpResponse<WoShippingServices>) => woShippingServices.body)
      );
    }
    return of(new WoShippingServices());
  }
}

export const woShippingServicesRoute: Routes = [
  {
    path: '',
    component: WoShippingServicesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoShippingServicesDetailComponent,
    resolve: {
      woShippingServices: WoShippingServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoShippingServicesUpdateComponent,
    resolve: {
      woShippingServices: WoShippingServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoShippingServicesUpdateComponent,
    resolve: {
      woShippingServices: WoShippingServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingServices'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woShippingServicesPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoShippingServicesDeletePopupComponent,
    resolve: {
      woShippingServices: WoShippingServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoShippingServices'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
