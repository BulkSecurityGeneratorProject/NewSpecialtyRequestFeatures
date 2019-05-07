import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NewSpecialtyRequestFeaturesSharedModule } from 'app/shared';
import {
  WoPickPackComponent,
  WoPickPackDetailComponent,
  WoPickPackUpdateComponent,
  WoPickPackDeletePopupComponent,
  WoPickPackDeleteDialogComponent,
  woPickPackRoute,
  woPickPackPopupRoute
} from './';

const ENTITY_STATES = [...woPickPackRoute, ...woPickPackPopupRoute];

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoPickPackComponent,
    WoPickPackDetailComponent,
    WoPickPackUpdateComponent,
    WoPickPackDeleteDialogComponent,
    WoPickPackDeletePopupComponent
  ],
  entryComponents: [WoPickPackComponent, WoPickPackUpdateComponent, WoPickPackDeleteDialogComponent, WoPickPackDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesWoPickPackModule {}
