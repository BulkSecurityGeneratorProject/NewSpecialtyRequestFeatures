import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';

@Component({
  selector: 'jhi-wo-work-order-detail',
  templateUrl: './wo-work-order-detail.component.html'
})
export class WoWorkOrderDetailComponent implements OnInit {
  woWorkOrder: IWoWorkOrder;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woWorkOrder }) => {
      this.woWorkOrder = woWorkOrder;
    });
  }

  previousState() {
    window.history.back();
  }
}
