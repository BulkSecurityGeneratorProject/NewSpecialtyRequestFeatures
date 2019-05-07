/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoStorageUpdateComponent } from 'app/entities/wo-storage/wo-storage-update.component';
import { WoStorageService } from 'app/entities/wo-storage/wo-storage.service';
import { WoStorage } from 'app/shared/model/wo-storage.model';

describe('Component Tests', () => {
  describe('WoStorage Management Update Component', () => {
    let comp: WoStorageUpdateComponent;
    let fixture: ComponentFixture<WoStorageUpdateComponent>;
    let service: WoStorageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoStorageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoStorageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoStorageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoStorageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoStorage(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoStorage();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
