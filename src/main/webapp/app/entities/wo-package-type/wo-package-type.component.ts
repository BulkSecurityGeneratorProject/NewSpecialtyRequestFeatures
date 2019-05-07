import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoPackageType } from 'app/shared/model/wo-package-type.model';
import { AccountService } from 'app/core';
import { WoPackageTypeService } from './wo-package-type.service';

@Component({
  selector: 'jhi-wo-package-type',
  templateUrl: './wo-package-type.component.html'
})
export class WoPackageTypeComponent implements OnInit, OnDestroy {
  woPackageTypes: IWoPackageType[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woPackageTypeService: WoPackageTypeService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woPackageTypeService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoPackageType[]>) => res.ok),
        map((res: HttpResponse<IWoPackageType[]>) => res.body)
      )
      .subscribe(
        (res: IWoPackageType[]) => {
          this.woPackageTypes = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoPackageTypes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoPackageType) {
    return item.id;
  }

  registerChangeInWoPackageTypes() {
    this.eventSubscriber = this.eventManager.subscribe('woPackageTypeListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
