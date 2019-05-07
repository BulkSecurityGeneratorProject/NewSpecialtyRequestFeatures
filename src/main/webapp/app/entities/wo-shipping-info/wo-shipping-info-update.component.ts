import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoShippingInfo, WoShippingInfo } from 'app/shared/model/wo-shipping-info.model';
import { WoShippingInfoService } from './wo-shipping-info.service';
import { IWoShippingServices } from 'app/shared/model/wo-shipping-services.model';
import { WoShippingServicesService } from 'app/entities/wo-shipping-services';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from 'app/entities/wo-work-order';

@Component({
  selector: 'jhi-wo-shipping-info-update',
  templateUrl: './wo-shipping-info-update.component.html'
})
export class WoShippingInfoUpdateComponent implements OnInit {
  woShippingInfo: IWoShippingInfo;
  isSaving: boolean;

  woshippingservices: IWoShippingServices[];

  woworkorders: IWoWorkOrder[];

  editForm = this.fb.group({
    id: [],
    monthlyShipVolume: [null, [Validators.max(20)]],
    isCustomsBrokerage: [],
    woShippingServices: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected woShippingInfoService: WoShippingInfoService,
    protected woShippingServicesService: WoShippingServicesService,
    protected woWorkOrderService: WoWorkOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woShippingInfo }) => {
      this.updateForm(woShippingInfo);
      this.woShippingInfo = woShippingInfo;
    });
    this.woShippingServicesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoShippingServices[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoShippingServices[]>) => response.body)
      )
      .subscribe((res: IWoShippingServices[]) => (this.woshippingservices = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.woWorkOrderService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoWorkOrder[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoWorkOrder[]>) => response.body)
      )
      .subscribe((res: IWoWorkOrder[]) => (this.woworkorders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(woShippingInfo: IWoShippingInfo) {
    this.editForm.patchValue({
      id: woShippingInfo.id,
      monthlyShipVolume: woShippingInfo.monthlyShipVolume,
      isCustomsBrokerage: woShippingInfo.isCustomsBrokerage,
      woShippingServices: woShippingInfo.woShippingServices
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woShippingInfo = this.createFromForm();
    if (woShippingInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.woShippingInfoService.update(woShippingInfo));
    } else {
      this.subscribeToSaveResponse(this.woShippingInfoService.create(woShippingInfo));
    }
  }

  private createFromForm(): IWoShippingInfo {
    const entity = {
      ...new WoShippingInfo(),
      id: this.editForm.get(['id']).value,
      monthlyShipVolume: this.editForm.get(['monthlyShipVolume']).value,
      isCustomsBrokerage: this.editForm.get(['isCustomsBrokerage']).value,
      woShippingServices: this.editForm.get(['woShippingServices']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoShippingInfo>>) {
    result.subscribe((res: HttpResponse<IWoShippingInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackWoShippingServicesById(index: number, item: IWoShippingServices) {
    return item.id;
  }

  trackWoWorkOrderById(index: number, item: IWoWorkOrder) {
    return item.id;
  }

  getSelected(selectedVals: Array<any>, option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
