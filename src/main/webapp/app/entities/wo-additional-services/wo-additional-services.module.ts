import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NewSpecialtyRequestFeaturesSharedModule } from 'app/shared';
import {
  WoAdditionalServicesComponent,
  WoAdditionalServicesDetailComponent,
  WoAdditionalServicesUpdateComponent,
  WoAdditionalServicesDeletePopupComponent,
  WoAdditionalServicesDeleteDialogComponent,
  woAdditionalServicesRoute,
  woAdditionalServicesPopupRoute
} from './';

const ENTITY_STATES = [...woAdditionalServicesRoute, ...woAdditionalServicesPopupRoute];

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoAdditionalServicesComponent,
    WoAdditionalServicesDetailComponent,
    WoAdditionalServicesUpdateComponent,
    WoAdditionalServicesDeleteDialogComponent,
    WoAdditionalServicesDeletePopupComponent
  ],
  entryComponents: [
    WoAdditionalServicesComponent,
    WoAdditionalServicesUpdateComponent,
    WoAdditionalServicesDeleteDialogComponent,
    WoAdditionalServicesDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesWoAdditionalServicesModule {}
