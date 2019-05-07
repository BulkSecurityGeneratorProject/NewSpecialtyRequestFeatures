import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoShippingServices } from 'app/shared/model/wo-shipping-services.model';

@Component({
  selector: 'jhi-wo-shipping-services-detail',
  templateUrl: './wo-shipping-services-detail.component.html'
})
export class WoShippingServicesDetailComponent implements OnInit {
  woShippingServices: IWoShippingServices;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woShippingServices }) => {
      this.woShippingServices = woShippingServices;
    });
  }

  previousState() {
    window.history.back();
  }
}
