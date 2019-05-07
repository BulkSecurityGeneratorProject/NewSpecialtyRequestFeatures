import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoPickPack } from 'app/shared/model/wo-pick-pack.model';
import { AccountService } from 'app/core';
import { WoPickPackService } from './wo-pick-pack.service';

@Component({
  selector: 'jhi-wo-pick-pack',
  templateUrl: './wo-pick-pack.component.html'
})
export class WoPickPackComponent implements OnInit, OnDestroy {
  woPickPacks: IWoPickPack[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woPickPackService: WoPickPackService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woPickPackService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoPickPack[]>) => res.ok),
        map((res: HttpResponse<IWoPickPack[]>) => res.body)
      )
      .subscribe(
        (res: IWoPickPack[]) => {
          this.woPickPacks = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoPickPacks();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoPickPack) {
    return item.id;
  }

  registerChangeInWoPickPacks() {
    this.eventSubscriber = this.eventManager.subscribe('woPickPackListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
