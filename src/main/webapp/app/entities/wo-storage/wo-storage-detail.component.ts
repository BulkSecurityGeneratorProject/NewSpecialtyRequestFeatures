import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoStorage } from 'app/shared/model/wo-storage.model';

@Component({
  selector: 'jhi-wo-storage-detail',
  templateUrl: './wo-storage-detail.component.html'
})
export class WoStorageDetailComponent implements OnInit {
  woStorage: IWoStorage;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woStorage }) => {
      this.woStorage = woStorage;
    });
  }

  previousState() {
    window.history.back();
  }
}
