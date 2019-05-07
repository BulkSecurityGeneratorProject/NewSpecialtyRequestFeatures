import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';
import { AccountService } from 'app/core';
import { WoCustomsBrokerageService } from './wo-customs-brokerage.service';

@Component({
  selector: 'jhi-wo-customs-brokerage',
  templateUrl: './wo-customs-brokerage.component.html'
})
export class WoCustomsBrokerageComponent implements OnInit, OnDestroy {
  woCustomsBrokerages: IWoCustomsBrokerage[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woCustomsBrokerageService: WoCustomsBrokerageService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woCustomsBrokerageService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoCustomsBrokerage[]>) => res.ok),
        map((res: HttpResponse<IWoCustomsBrokerage[]>) => res.body)
      )
      .subscribe(
        (res: IWoCustomsBrokerage[]) => {
          this.woCustomsBrokerages = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoCustomsBrokerages();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoCustomsBrokerage) {
    return item.id;
  }

  registerChangeInWoCustomsBrokerages() {
    this.eventSubscriber = this.eventManager.subscribe('woCustomsBrokerageListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
