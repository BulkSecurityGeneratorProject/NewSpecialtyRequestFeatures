import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoPackageType } from 'app/shared/model/wo-package-type.model';

@Component({
  selector: 'jhi-wo-package-type-detail',
  templateUrl: './wo-package-type-detail.component.html'
})
export class WoPackageTypeDetailComponent implements OnInit {
  woPackageType: IWoPackageType;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woPackageType }) => {
      this.woPackageType = woPackageType;
    });
  }

  previousState() {
    window.history.back();
  }
}
