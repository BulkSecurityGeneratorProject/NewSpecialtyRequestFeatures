import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoStorage } from 'app/shared/model/wo-storage.model';
import { AccountService } from 'app/core';
import { WoStorageService } from './wo-storage.service';

@Component({
  selector: 'jhi-wo-storage',
  templateUrl: './wo-storage.component.html'
})
export class WoStorageComponent implements OnInit, OnDestroy {
  woStorages: IWoStorage[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woStorageService: WoStorageService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woStorageService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoStorage[]>) => res.ok),
        map((res: HttpResponse<IWoStorage[]>) => res.body)
      )
      .subscribe(
        (res: IWoStorage[]) => {
          this.woStorages = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoStorages();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoStorage) {
    return item.id;
  }

  registerChangeInWoStorages() {
    this.eventSubscriber = this.eventManager.subscribe('woStorageListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
