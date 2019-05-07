import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoTransportModes } from 'app/shared/model/wo-transport-modes.model';
import { AccountService } from 'app/core';
import { WoTransportModesService } from './wo-transport-modes.service';

@Component({
  selector: 'jhi-wo-transport-modes',
  templateUrl: './wo-transport-modes.component.html'
})
export class WoTransportModesComponent implements OnInit, OnDestroy {
  woTransportModes: IWoTransportModes[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woTransportModesService: WoTransportModesService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woTransportModesService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoTransportModes[]>) => res.ok),
        map((res: HttpResponse<IWoTransportModes[]>) => res.body)
      )
      .subscribe(
        (res: IWoTransportModes[]) => {
          this.woTransportModes = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoTransportModes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoTransportModes) {
    return item.id;
  }

  registerChangeInWoTransportModes() {
    this.eventSubscriber = this.eventManager.subscribe('woTransportModesListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
