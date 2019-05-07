import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoAdditionalServices } from 'app/shared/model/wo-additional-services.model';
import { WoAdditionalServicesService } from './wo-additional-services.service';
import { WoAdditionalServicesComponent } from './wo-additional-services.component';
import { WoAdditionalServicesDetailComponent } from './wo-additional-services-detail.component';
import { WoAdditionalServicesUpdateComponent } from './wo-additional-services-update.component';
import { WoAdditionalServicesDeletePopupComponent } from './wo-additional-services-delete-dialog.component';
import { IWoAdditionalServices } from 'app/shared/model/wo-additional-services.model';

@Injectable({ providedIn: 'root' })
export class WoAdditionalServicesResolve implements Resolve<IWoAdditionalServices> {
  constructor(private service: WoAdditionalServicesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoAdditionalServices> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoAdditionalServices>) => response.ok),
        map((woAdditionalServices: HttpResponse<WoAdditionalServices>) => woAdditionalServices.body)
      );
    }
    return of(new WoAdditionalServices());
  }
}

export const woAdditionalServicesRoute: Routes = [
  {
    path: '',
    component: WoAdditionalServicesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoAdditionalServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoAdditionalServicesDetailComponent,
    resolve: {
      woAdditionalServices: WoAdditionalServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoAdditionalServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoAdditionalServicesUpdateComponent,
    resolve: {
      woAdditionalServices: WoAdditionalServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoAdditionalServices'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoAdditionalServicesUpdateComponent,
    resolve: {
      woAdditionalServices: WoAdditionalServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoAdditionalServices'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woAdditionalServicesPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoAdditionalServicesDeletePopupComponent,
    resolve: {
      woAdditionalServices: WoAdditionalServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoAdditionalServices'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
