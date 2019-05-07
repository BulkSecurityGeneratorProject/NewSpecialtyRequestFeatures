import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoPickPack } from 'app/shared/model/wo-pick-pack.model';
import { WoPickPackService } from './wo-pick-pack.service';
import { WoPickPackComponent } from './wo-pick-pack.component';
import { WoPickPackDetailComponent } from './wo-pick-pack-detail.component';
import { WoPickPackUpdateComponent } from './wo-pick-pack-update.component';
import { WoPickPackDeletePopupComponent } from './wo-pick-pack-delete-dialog.component';
import { IWoPickPack } from 'app/shared/model/wo-pick-pack.model';

@Injectable({ providedIn: 'root' })
export class WoPickPackResolve implements Resolve<IWoPickPack> {
  constructor(private service: WoPickPackService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoPickPack> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoPickPack>) => response.ok),
        map((woPickPack: HttpResponse<WoPickPack>) => woPickPack.body)
      );
    }
    return of(new WoPickPack());
  }
}

export const woPickPackRoute: Routes = [
  {
    path: '',
    component: WoPickPackComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPickPacks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoPickPackDetailComponent,
    resolve: {
      woPickPack: WoPickPackResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPickPacks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoPickPackUpdateComponent,
    resolve: {
      woPickPack: WoPickPackResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPickPacks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoPickPackUpdateComponent,
    resolve: {
      woPickPack: WoPickPackResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPickPacks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woPickPackPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoPickPackDeletePopupComponent,
    resolve: {
      woPickPack: WoPickPackResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPickPacks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
