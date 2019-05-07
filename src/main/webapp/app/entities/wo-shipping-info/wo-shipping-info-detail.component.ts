import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoShippingInfo } from 'app/shared/model/wo-shipping-info.model';

@Component({
  selector: 'jhi-wo-shipping-info-detail',
  templateUrl: './wo-shipping-info-detail.component.html'
})
export class WoShippingInfoDetailComponent implements OnInit {
  woShippingInfo: IWoShippingInfo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woShippingInfo }) => {
      this.woShippingInfo = woShippingInfo;
    });
  }

  previousState() {
    window.history.back();
  }
}
