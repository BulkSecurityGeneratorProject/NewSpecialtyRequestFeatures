import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NewSpecialtyRequestFeaturesSharedModule } from 'app/shared';
import {
  WoTransportModesComponent,
  WoTransportModesDetailComponent,
  WoTransportModesUpdateComponent,
  WoTransportModesDeletePopupComponent,
  WoTransportModesDeleteDialogComponent,
  woTransportModesRoute,
  woTransportModesPopupRoute
} from './';

const ENTITY_STATES = [...woTransportModesRoute, ...woTransportModesPopupRoute];

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoTransportModesComponent,
    WoTransportModesDetailComponent,
    WoTransportModesUpdateComponent,
    WoTransportModesDeleteDialogComponent,
    WoTransportModesDeletePopupComponent
  ],
  entryComponents: [
    WoTransportModesComponent,
    WoTransportModesUpdateComponent,
    WoTransportModesDeleteDialogComponent,
    WoTransportModesDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesWoTransportModesModule {}
