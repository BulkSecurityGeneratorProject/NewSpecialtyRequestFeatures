import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { AccountService } from 'app/core';
import { WoWorkOrderService } from './wo-work-order.service';

@Component({
  selector: 'jhi-wo-work-order',
  templateUrl: './wo-work-order.component.html'
})
export class WoWorkOrderComponent implements OnInit, OnDestroy {
  woWorkOrders: IWoWorkOrder[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woWorkOrderService: WoWorkOrderService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woWorkOrderService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoWorkOrder[]>) => res.ok),
        map((res: HttpResponse<IWoWorkOrder[]>) => res.body)
      )
      .subscribe(
        (res: IWoWorkOrder[]) => {
          this.woWorkOrders = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoWorkOrders();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoWorkOrder) {
    return item.id;
  }

  registerChangeInWoWorkOrders() {
    this.eventSubscriber = this.eventManager.subscribe('woWorkOrderListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
