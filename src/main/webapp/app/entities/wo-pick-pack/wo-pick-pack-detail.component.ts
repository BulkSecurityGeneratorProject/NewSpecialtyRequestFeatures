import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoPickPack } from 'app/shared/model/wo-pick-pack.model';

@Component({
  selector: 'jhi-wo-pick-pack-detail',
  templateUrl: './wo-pick-pack-detail.component.html'
})
export class WoPickPackDetailComponent implements OnInit {
  woPickPack: IWoPickPack;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woPickPack }) => {
      this.woPickPack = woPickPack;
    });
  }

  previousState() {
    window.history.back();
  }
}
