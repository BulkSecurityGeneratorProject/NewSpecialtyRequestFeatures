import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoShippingInfo } from 'app/shared/model/wo-shipping-info.model';
import { AccountService } from 'app/core';
import { WoShippingInfoService } from './wo-shipping-info.service';

@Component({
  selector: 'jhi-wo-shipping-info',
  templateUrl: './wo-shipping-info.component.html'
})
export class WoShippingInfoComponent implements OnInit, OnDestroy {
  woShippingInfos: IWoShippingInfo[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woShippingInfoService: WoShippingInfoService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woShippingInfoService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoShippingInfo[]>) => res.ok),
        map((res: HttpResponse<IWoShippingInfo[]>) => res.body)
      )
      .subscribe(
        (res: IWoShippingInfo[]) => {
          this.woShippingInfos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoShippingInfos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoShippingInfo) {
    return item.id;
  }

  registerChangeInWoShippingInfos() {
    this.eventSubscriber = this.eventManager.subscribe('woShippingInfoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
