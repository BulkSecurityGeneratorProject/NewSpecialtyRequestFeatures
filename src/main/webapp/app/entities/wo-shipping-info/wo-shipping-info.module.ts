import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NewSpecialtyRequestFeaturesSharedModule } from 'app/shared';
import {
  WoShippingInfoComponent,
  WoShippingInfoDetailComponent,
  WoShippingInfoUpdateComponent,
  WoShippingInfoDeletePopupComponent,
  WoShippingInfoDeleteDialogComponent,
  woShippingInfoRoute,
  woShippingInfoPopupRoute
} from './';

const ENTITY_STATES = [...woShippingInfoRoute, ...woShippingInfoPopupRoute];

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoShippingInfoComponent,
    WoShippingInfoDetailComponent,
    WoShippingInfoUpdateComponent,
    WoShippingInfoDeleteDialogComponent,
    WoShippingInfoDeletePopupComponent
  ],
  entryComponents: [
    WoShippingInfoComponent,
    WoShippingInfoUpdateComponent,
    WoShippingInfoDeleteDialogComponent,
    WoShippingInfoDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesWoShippingInfoModule {}
