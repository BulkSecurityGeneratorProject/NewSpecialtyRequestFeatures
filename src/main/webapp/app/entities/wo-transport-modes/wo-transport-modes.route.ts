import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoTransportModes } from 'app/shared/model/wo-transport-modes.model';
import { WoTransportModesService } from './wo-transport-modes.service';
import { WoTransportModesComponent } from './wo-transport-modes.component';
import { WoTransportModesDetailComponent } from './wo-transport-modes-detail.component';
import { WoTransportModesUpdateComponent } from './wo-transport-modes-update.component';
import { WoTransportModesDeletePopupComponent } from './wo-transport-modes-delete-dialog.component';
import { IWoTransportModes } from 'app/shared/model/wo-transport-modes.model';

@Injectable({ providedIn: 'root' })
export class WoTransportModesResolve implements Resolve<IWoTransportModes> {
  constructor(private service: WoTransportModesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoTransportModes> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoTransportModes>) => response.ok),
        map((woTransportModes: HttpResponse<WoTransportModes>) => woTransportModes.body)
      );
    }
    return of(new WoTransportModes());
  }
}

export const woTransportModesRoute: Routes = [
  {
    path: '',
    component: WoTransportModesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoTransportModes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoTransportModesDetailComponent,
    resolve: {
      woTransportModes: WoTransportModesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoTransportModes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoTransportModesUpdateComponent,
    resolve: {
      woTransportModes: WoTransportModesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoTransportModes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoTransportModesUpdateComponent,
    resolve: {
      woTransportModes: WoTransportModesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoTransportModes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woTransportModesPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoTransportModesDeletePopupComponent,
    resolve: {
      woTransportModes: WoTransportModesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoTransportModes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
