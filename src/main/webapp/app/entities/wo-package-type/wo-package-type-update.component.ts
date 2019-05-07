import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoPackageType, WoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from './wo-package-type.service';
import { IWoStorage } from 'app/shared/model/wo-storage.model';
import { WoStorageService } from 'app/entities/wo-storage';

@Component({
  selector: 'jhi-wo-package-type-update',
  templateUrl: './wo-package-type-update.component.html'
})
export class WoPackageTypeUpdateComponent implements OnInit {
  woPackageType: IWoPackageType;
  isSaving: boolean;

  wostorages: IWoStorage[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected woPackageTypeService: WoPackageTypeService,
    protected woStorageService: WoStorageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woPackageType }) => {
      this.updateForm(woPackageType);
      this.woPackageType = woPackageType;
    });
    this.woStorageService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoStorage[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoStorage[]>) => response.body)
      )
      .subscribe((res: IWoStorage[]) => (this.wostorages = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(woPackageType: IWoPackageType) {
    this.editForm.patchValue({
      id: woPackageType.id,
      name: woPackageType.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woPackageType = this.createFromForm();
    if (woPackageType.id !== undefined) {
      this.subscribeToSaveResponse(this.woPackageTypeService.update(woPackageType));
    } else {
      this.subscribeToSaveResponse(this.woPackageTypeService.create(woPackageType));
    }
  }

  private createFromForm(): IWoPackageType {
    const entity = {
      ...new WoPackageType(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoPackageType>>) {
    result.subscribe((res: HttpResponse<IWoPackageType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackWoStorageById(index: number, item: IWoStorage) {
    return item.id;
  }
}
