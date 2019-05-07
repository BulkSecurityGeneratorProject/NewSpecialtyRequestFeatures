import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoStorage, WoStorage } from 'app/shared/model/wo-storage.model';
import { WoStorageService } from './wo-storage.service';
import { IWoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from 'app/entities/wo-package-type';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from 'app/entities/wo-work-order';

@Component({
  selector: 'jhi-wo-storage-update',
  templateUrl: './wo-storage-update.component.html'
})
export class WoStorageUpdateComponent implements OnInit {
  woStorage: IWoStorage;
  isSaving: boolean;

  wopackagetypes: IWoPackageType[];

  woworkorders: IWoWorkOrder[];

  editForm = this.fb.group({
    id: [],
    quantity: [null, [Validators.max(11)]],
    spaceRequirement: [],
    productInfo: [null, [Validators.maxLength(255)]],
    woPackageTypeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected woStorageService: WoStorageService,
    protected woPackageTypeService: WoPackageTypeService,
    protected woWorkOrderService: WoWorkOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woStorage }) => {
      this.updateForm(woStorage);
      this.woStorage = woStorage;
    });
    this.woPackageTypeService
      .query({ filter: 'wostorage-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IWoPackageType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoPackageType[]>) => response.body)
      )
      .subscribe(
        (res: IWoPackageType[]) => {
          if (!this.woStorage.woPackageTypeId) {
            this.wopackagetypes = res;
          } else {
            this.woPackageTypeService
              .find(this.woStorage.woPackageTypeId)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IWoPackageType>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IWoPackageType>) => subResponse.body)
              )
              .subscribe(
                (subRes: IWoPackageType) => (this.wopackagetypes = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
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

  updateForm(woStorage: IWoStorage) {
    this.editForm.patchValue({
      id: woStorage.id,
      quantity: woStorage.quantity,
      spaceRequirement: woStorage.spaceRequirement,
      productInfo: woStorage.productInfo,
      woPackageTypeId: woStorage.woPackageTypeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woStorage = this.createFromForm();
    if (woStorage.id !== undefined) {
      this.subscribeToSaveResponse(this.woStorageService.update(woStorage));
    } else {
      this.subscribeToSaveResponse(this.woStorageService.create(woStorage));
    }
  }

  private createFromForm(): IWoStorage {
    const entity = {
      ...new WoStorage(),
      id: this.editForm.get(['id']).value,
      quantity: this.editForm.get(['quantity']).value,
      spaceRequirement: this.editForm.get(['spaceRequirement']).value,
      productInfo: this.editForm.get(['productInfo']).value,
      woPackageTypeId: this.editForm.get(['woPackageTypeId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoStorage>>) {
    result.subscribe((res: HttpResponse<IWoStorage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackWoPackageTypeById(index: number, item: IWoPackageType) {
    return item.id;
  }

  trackWoWorkOrderById(index: number, item: IWoWorkOrder) {
    return item.id;
  }
}
