import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoPickPack, WoPickPack } from 'app/shared/model/wo-pick-pack.model';
import { WoPickPackService } from './wo-pick-pack.service';
import { IWoAdditionalServices } from 'app/shared/model/wo-additional-services.model';
import { WoAdditionalServicesService } from 'app/entities/wo-additional-services';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from 'app/entities/wo-work-order';

@Component({
  selector: 'jhi-wo-pick-pack-update',
  templateUrl: './wo-pick-pack-update.component.html'
})
export class WoPickPackUpdateComponent implements OnInit {
  woPickPack: IWoPickPack;
  isSaving: boolean;

  woadditionalservices: IWoAdditionalServices[];

  woworkorders: IWoWorkOrder[];

  editForm = this.fb.group({
    id: [],
    sku: [],
    avgOrders: [],
    shipmentWeight: [],
    shipmentSize: [null, [Validators.max(20)]],
    monthlyVolume: [null, [Validators.max(20)]],
    isPromotionalInserts: [],
    description: [null, [Validators.maxLength(255)]],
    woAdditionalServices: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected woPickPackService: WoPickPackService,
    protected woAdditionalServicesService: WoAdditionalServicesService,
    protected woWorkOrderService: WoWorkOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woPickPack }) => {
      this.updateForm(woPickPack);
      this.woPickPack = woPickPack;
    });
    this.woAdditionalServicesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoAdditionalServices[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoAdditionalServices[]>) => response.body)
      )
      .subscribe(
        (res: IWoAdditionalServices[]) => (this.woadditionalservices = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.woWorkOrderService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoWorkOrder[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoWorkOrder[]>) => response.body)
      )
      .subscribe((res: IWoWorkOrder[]) => (this.woworkorders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(woPickPack: IWoPickPack) {
    this.editForm.patchValue({
      id: woPickPack.id,
      sku: woPickPack.sku,
      avgOrders: woPickPack.avgOrders,
      shipmentWeight: woPickPack.shipmentWeight,
      shipmentSize: woPickPack.shipmentSize,
      monthlyVolume: woPickPack.monthlyVolume,
      isPromotionalInserts: woPickPack.isPromotionalInserts,
      description: woPickPack.description,
      woAdditionalServices: woPickPack.woAdditionalServices
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woPickPack = this.createFromForm();
    if (woPickPack.id !== undefined) {
      this.subscribeToSaveResponse(this.woPickPackService.update(woPickPack));
    } else {
      this.subscribeToSaveResponse(this.woPickPackService.create(woPickPack));
    }
  }

  private createFromForm(): IWoPickPack {
    const entity = {
      ...new WoPickPack(),
      id: this.editForm.get(['id']).value,
      sku: this.editForm.get(['sku']).value,
      avgOrders: this.editForm.get(['avgOrders']).value,
      shipmentWeight: this.editForm.get(['shipmentWeight']).value,
      shipmentSize: this.editForm.get(['shipmentSize']).value,
      monthlyVolume: this.editForm.get(['monthlyVolume']).value,
      isPromotionalInserts: this.editForm.get(['isPromotionalInserts']).value,
      description: this.editForm.get(['description']).value,
      woAdditionalServices: this.editForm.get(['woAdditionalServices']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoPickPack>>) {
    result.subscribe((res: HttpResponse<IWoPickPack>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackWoAdditionalServicesById(index: number, item: IWoAdditionalServices) {
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
