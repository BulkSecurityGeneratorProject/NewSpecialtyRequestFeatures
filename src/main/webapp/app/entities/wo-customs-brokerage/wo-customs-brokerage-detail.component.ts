import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';

@Component({
  selector: 'jhi-wo-customs-brokerage-detail',
  templateUrl: './wo-customs-brokerage-detail.component.html'
})
export class WoCustomsBrokerageDetailComponent implements OnInit {
  woCustomsBrokerage: IWoCustomsBrokerage;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woCustomsBrokerage }) => {
      this.woCustomsBrokerage = woCustomsBrokerage;
    });
  }

  previousState() {
    window.history.back();
  }
}
