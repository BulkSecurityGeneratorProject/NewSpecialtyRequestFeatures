import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from './wo-work-order.service';
import { WoWorkOrderComponent } from './wo-work-order.component';
import { WoWorkOrderDetailComponent } from './wo-work-order-detail.component';
import { WoWorkOrderUpdateComponent } from './wo-work-order-update.component';
import { WoWorkOrderDeletePopupComponent } from './wo-work-order-delete-dialog.component';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';

@Injectable({ providedIn: 'root' })
export class WoWorkOrderResolve implements Resolve<IWoWorkOrder> {
  constructor(private service: WoWorkOrderService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoWorkOrder> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoWorkOrder>) => response.ok),
        map((woWorkOrder: HttpResponse<WoWorkOrder>) => woWorkOrder.body)
      );
    }
    return of(new WoWorkOrder());
  }
}

export const woWorkOrderRoute: Routes = [
  {
    path: '',
    component: WoWorkOrderComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoWorkOrders'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoWorkOrderDetailComponent,
    resolve: {
      woWorkOrder: WoWorkOrderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoWorkOrders'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoWorkOrderUpdateComponent,
    resolve: {
      woWorkOrder: WoWorkOrderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoWorkOrders'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoWorkOrderUpdateComponent,
    resolve: {
      woWorkOrder: WoWorkOrderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoWorkOrders'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woWorkOrderPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoWorkOrderDeletePopupComponent,
    resolve: {
      woWorkOrder: WoWorkOrderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoWorkOrders'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
