import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NewSpecialtyRequestFeaturesSharedModule } from 'app/shared';
import {
  WoWorkOrderComponent,
  WoWorkOrderDetailComponent,
  WoWorkOrderUpdateComponent,
  WoWorkOrderDeletePopupComponent,
  WoWorkOrderDeleteDialogComponent,
  woWorkOrderRoute,
  woWorkOrderPopupRoute
} from './';

const ENTITY_STATES = [...woWorkOrderRoute, ...woWorkOrderPopupRoute];

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoWorkOrderComponent,
    WoWorkOrderDetailComponent,
    WoWorkOrderUpdateComponent,
    WoWorkOrderDeleteDialogComponent,
    WoWorkOrderDeletePopupComponent
  ],
  entryComponents: [WoWorkOrderComponent, WoWorkOrderUpdateComponent, WoWorkOrderDeleteDialogComponent, WoWorkOrderDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesWoWorkOrderModule {}
