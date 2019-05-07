import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoShippingServices } from 'app/shared/model/wo-shipping-services.model';
import { AccountService } from 'app/core';
import { WoShippingServicesService } from './wo-shipping-services.service';

@Component({
  selector: 'jhi-wo-shipping-services',
  templateUrl: './wo-shipping-services.component.html'
})
export class WoShippingServicesComponent implements OnInit, OnDestroy {
  woShippingServices: IWoShippingServices[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woShippingServicesService: WoShippingServicesService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woShippingServicesService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoShippingServices[]>) => res.ok),
        map((res: HttpResponse<IWoShippingServices[]>) => res.body)
      )
      .subscribe(
        (res: IWoShippingServices[]) => {
          this.woShippingServices = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoShippingServices();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoShippingServices) {
    return item.id;
  }

  registerChangeInWoShippingServices() {
    this.eventSubscriber = this.eventManager.subscribe('woShippingServicesListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
