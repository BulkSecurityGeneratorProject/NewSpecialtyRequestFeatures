import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'wo-work-order',
        loadChildren: './wo-work-order/wo-work-order.module#NewSpecialtyRequestFeaturesWoWorkOrderModule'
      },
      {
        path: 'wo-transport-modes',
        loadChildren: './wo-transport-modes/wo-transport-modes.module#NewSpecialtyRequestFeaturesWoTransportModesModule'
      },
      {
        path: 'wo-customs-brokerage',
        loadChildren: './wo-customs-brokerage/wo-customs-brokerage.module#NewSpecialtyRequestFeaturesWoCustomsBrokerageModule'
      },
      {
        path: 'wo-storage',
        loadChildren: './wo-storage/wo-storage.module#NewSpecialtyRequestFeaturesWoStorageModule'
      },
      {
        path: 'wo-pick-pack',
        loadChildren: './wo-pick-pack/wo-pick-pack.module#NewSpecialtyRequestFeaturesWoPickPackModule'
      },
      {
        path: 'wo-additional-services',
        loadChildren: './wo-additional-services/wo-additional-services.module#NewSpecialtyRequestFeaturesWoAdditionalServicesModule'
      },
      {
        path: 'wo-package-type',
        loadChildren: './wo-package-type/wo-package-type.module#NewSpecialtyRequestFeaturesWoPackageTypeModule'
      },
      {
        path: 'wo-shipping-info',
        loadChildren: './wo-shipping-info/wo-shipping-info.module#NewSpecialtyRequestFeaturesWoShippingInfoModule'
      },
      {
        path: 'wo-shipping-services',
        loadChildren: './wo-shipping-services/wo-shipping-services.module#NewSpecialtyRequestFeaturesWoShippingServicesModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewSpecialtyRequestFeaturesEntityModule {}
