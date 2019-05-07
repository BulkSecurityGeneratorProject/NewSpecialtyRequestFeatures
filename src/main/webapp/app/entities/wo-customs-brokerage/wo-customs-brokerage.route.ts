import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';
import { WoCustomsBrokerageService } from './wo-customs-brokerage.service';
import { WoCustomsBrokerageComponent } from './wo-customs-brokerage.component';
import { WoCustomsBrokerageDetailComponent } from './wo-customs-brokerage-detail.component';
import { WoCustomsBrokerageUpdateComponent } from './wo-customs-brokerage-update.component';
import { WoCustomsBrokerageDeletePopupComponent } from './wo-customs-brokerage-delete-dialog.component';
import { IWoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';

@Injectable({ providedIn: 'root' })
export class WoCustomsBrokerageResolve implements Resolve<IWoCustomsBrokerage> {
  constructor(private service: WoCustomsBrokerageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoCustomsBrokerage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoCustomsBrokerage>) => response.ok),
        map((woCustomsBrokerage: HttpResponse<WoCustomsBrokerage>) => woCustomsBrokerage.body)
      );
    }
    return of(new WoCustomsBrokerage());
  }
}

export const woCustomsBrokerageRoute: Routes = [
  {
    path: '',
    component: WoCustomsBrokerageComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoCustomsBrokerages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoCustomsBrokerageDetailComponent,
    resolve: {
      woCustomsBrokerage: WoCustomsBrokerageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoCustomsBrokerages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoCustomsBrokerageUpdateComponent,
    resolve: {
      woCustomsBrokerage: WoCustomsBrokerageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoCustomsBrokerages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoCustomsBrokerageUpdateComponent,
    resolve: {
      woCustomsBrokerage: WoCustomsBrokerageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoCustomsBrokerages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woCustomsBrokeragePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoCustomsBrokerageDeletePopupComponent,
    resolve: {
      woCustomsBrokerage: WoCustomsBrokerageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoCustomsBrokerages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
