import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NewSpecialtyRequestFeaturesSharedModule } from 'app/shared';
import {
  WoShippingServicesComponent,
  WoShippingServicesDetailComponent,
  WoShippingServicesUpdateComponent,
  WoShippingServicesDeletePopupComponent,
  WoShippingServicesDeleteDialogComponent,
  woShippingServicesRoute,
  woShippingServicesPopupRoute
} from './';

const ENTITY_STATES = [...woShippingServicesRoute, ...woShippingServicesPopupRoute];

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoShippingServicesComponent,
    WoShippingServicesDetailComponent,
    WoShippingServicesUpdateComponent,
    WoShippingServicesDeleteDialogComponent,
    WoShippingServicesDeletePopupComponent
  ],
  entryComponents: [
    WoShippingServicesComponent,
    WoShippingServicesUpdateComponent,
    WoShippingServicesDeleteDialogComponent,
    WoShippingServicesDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesWoShippingServicesModule {}
