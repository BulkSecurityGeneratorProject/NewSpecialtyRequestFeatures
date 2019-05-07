import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoAdditionalServices } from 'app/shared/model/wo-additional-services.model';

@Component({
  selector: 'jhi-wo-additional-services-detail',
  templateUrl: './wo-additional-services-detail.component.html'
})
export class WoAdditionalServicesDetailComponent implements OnInit {
  woAdditionalServices: IWoAdditionalServices;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woAdditionalServices }) => {
      this.woAdditionalServices = woAdditionalServices;
    });
  }

  previousState() {
    window.history.back();
  }
}
