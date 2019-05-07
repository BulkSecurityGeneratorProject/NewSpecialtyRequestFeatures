import { NgModule } from '@angular/core';

import { NewSpecialtyRequestFeaturesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [NewSpecialtyRequestFeaturesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class NewSpecialtyRequestFeaturesSharedCommonModule {}
