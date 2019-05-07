import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoShippingServices, WoShippingServices } from 'app/shared/model/wo-shipping-services.model';
import { WoShippingServicesService } from './wo-shipping-services.service';
import { IWoShippingInfo } from 'app/shared/model/wo-shipping-info.model';
import { WoShippingInfoService } from 'app/entities/wo-shipping-info';

@Component({
  selector: 'jhi-wo-shipping-services-update',
  templateUrl: './wo-shipping-services-update.component.html'
})
export class WoShippingServicesUpdateComponent implements OnInit {
  woShippingServices: IWoShippingServices;
  isSaving: boolean;

  woshippinginfos: IWoShippingInfo[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected woShippingServicesService: WoShippingServicesService,
    protected woShippingInfoService: WoShippingInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woShippingServices }) => {
      this.updateForm(woShippingServices);
      this.woShippingServices = woShippingServices;
    });
    this.woShippingInfoService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoShippingInfo[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoShippingInfo[]>) => response.body)
      )
      .subscribe((res: IWoShippingInfo[]) => (this.woshippinginfos = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(woShippingServices: IWoShippingServices) {
    this.editForm.patchValue({
      id: woShippingServices.id,
      name: woShippingServices.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woShippingServices = this.createFromForm();
    if (woShippingServices.id !== undefined) {
      this.subscribeToSaveResponse(this.woShippingServicesService.update(woShippingServices));
    } else {
      this.subscribeToSaveResponse(this.woShippingServicesService.create(woShippingServices));
    }
  }

  private createFromForm(): IWoShippingServices {
    const entity = {
      ...new WoShippingServices(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoShippingServices>>) {
    result.subscribe((res: HttpResponse<IWoShippingServices>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackWoShippingInfoById(index: number, item: IWoShippingInfo) {
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
