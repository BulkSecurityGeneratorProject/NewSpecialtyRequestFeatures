import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NewSpecialtyRequestFeaturesSharedModule } from 'app/shared';
import {
  WoStorageComponent,
  WoStorageDetailComponent,
  WoStorageUpdateComponent,
  WoStorageDeletePopupComponent,
  WoStorageDeleteDialogComponent,
  woStorageRoute,
  woStoragePopupRoute
} from './';

const ENTITY_STATES = [...woStorageRoute, ...woStoragePopupRoute];

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoStorageComponent,
    WoStorageDetailComponent,
    WoStorageUpdateComponent,
    WoStorageDeleteDialogComponent,
    WoStorageDeletePopupComponent
  ],
  entryComponents: [WoStorageComponent, WoStorageUpdateComponent, WoStorageDeleteDialogComponent, WoStorageDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesWoStorageModule {}
