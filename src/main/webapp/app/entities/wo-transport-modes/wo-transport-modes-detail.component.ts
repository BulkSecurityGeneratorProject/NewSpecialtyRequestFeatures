import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoTransportModes } from 'app/shared/model/wo-transport-modes.model';

@Component({
  selector: 'jhi-wo-transport-modes-detail',
  templateUrl: './wo-transport-modes-detail.component.html'
})
export class WoTransportModesDetailComponent implements OnInit {
  woTransportModes: IWoTransportModes;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woTransportModes }) => {
      this.woTransportModes = woTransportModes;
    });
  }

  previousState() {
    window.history.back();
  }
}
