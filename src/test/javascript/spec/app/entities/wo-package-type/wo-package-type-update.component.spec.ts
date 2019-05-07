/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoPackageTypeUpdateComponent } from 'app/entities/wo-package-type/wo-package-type-update.component';
import { WoPackageTypeService } from 'app/entities/wo-package-type/wo-package-type.service';
import { WoPackageType } from 'app/shared/model/wo-package-type.model';

describe('Component Tests', () => {
  describe('WoPackageType Management Update Component', () => {
    let comp: WoPackageTypeUpdateComponent;
    let fixture: ComponentFixture<WoPackageTypeUpdateComponent>;
    let service: WoPackageTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoPackageTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoPackageTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoPackageTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoPackageTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoPackageType(123);
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
        const entity = new WoPackageType();
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
