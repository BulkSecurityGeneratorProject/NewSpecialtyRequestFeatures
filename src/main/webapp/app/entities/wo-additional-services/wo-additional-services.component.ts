import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoAdditionalServices } from 'app/shared/model/wo-additional-services.model';
import { AccountService } from 'app/core';
import { WoAdditionalServicesService } from './wo-additional-services.service';

@Component({
  selector: 'jhi-wo-additional-services',
  templateUrl: './wo-additional-services.component.html'
})
export class WoAdditionalServicesComponent implements OnInit, OnDestroy {
  woAdditionalServices: IWoAdditionalServices[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woAdditionalServicesService: WoAdditionalServicesService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woAdditionalServicesService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoAdditionalServices[]>) => res.ok),
        map((res: HttpResponse<IWoAdditionalServices[]>) => res.body)
      )
      .subscribe(
        (res: IWoAdditionalServices[]) => {
          this.woAdditionalServices = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoAdditionalServices();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoAdditionalServices) {
    return item.id;
  }

  registerChangeInWoAdditionalServices() {
    this.eventSubscriber = this.eventManager.subscribe('woAdditionalServicesListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
