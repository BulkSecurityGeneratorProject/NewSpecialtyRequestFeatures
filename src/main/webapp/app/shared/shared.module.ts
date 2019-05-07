import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {
  NewSpecialtyRequestFeaturesSharedLibsModule,
  NewSpecialtyRequestFeaturesSharedCommonModule,
  JhiLoginModalComponent,
  HasAnyAuthorityDirective
} from './';

@NgModule({
  imports: [NewSpecialtyRequestFeaturesSharedLibsModule, NewSpecialtyRequestFeaturesSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [NewSpecialtyRequestFeaturesSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesSharedModule {
  static forRoot() {
    return {
      ngModule: NewSpecialtyRequestFeaturesSharedModule
    };
  }
}
