import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoAdditionalServices, WoAdditionalServices } from 'app/shared/model/wo-additional-services.model';
import { WoAdditionalServicesService } from './wo-additional-services.service';
import { IWoPickPack } from 'app/shared/model/wo-pick-pack.model';
import { WoPickPackService } from 'app/entities/wo-pick-pack';

@Component({
  selector: 'jhi-wo-additional-services-update',
  templateUrl: './wo-additional-services-update.component.html'
})
export class WoAdditionalServicesUpdateComponent implements OnInit {
  woAdditionalServices: IWoAdditionalServices;
  isSaving: boolean;

  wopickpacks: IWoPickPack[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected woAdditionalServicesService: WoAdditionalServicesService,
    protected woPickPackService: WoPickPackService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woAdditionalServices }) => {
      this.updateForm(woAdditionalServices);
      this.woAdditionalServices = woAdditionalServices;
    });
    this.woPickPackService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoPickPack[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoPickPack[]>) => response.body)
      )
      .subscribe((res: IWoPickPack[]) => (this.wopickpacks = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(woAdditionalServices: IWoAdditionalServices) {
    this.editForm.patchValue({
      id: woAdditionalServices.id,
      name: woAdditionalServices.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woAdditionalServices = this.createFromForm();
    if (woAdditionalServices.id !== undefined) {
      this.subscribeToSaveResponse(this.woAdditionalServicesService.update(woAdditionalServices));
    } else {
      this.subscribeToSaveResponse(this.woAdditionalServicesService.create(woAdditionalServices));
    }
  }

  private createFromForm(): IWoAdditionalServices {
    const entity = {
      ...new WoAdditionalServices(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoAdditionalServices>>) {
    result.subscribe((res: HttpResponse<IWoAdditionalServices>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackWoPickPackById(index: number, item: IWoPickPack) {
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
