/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoAdditionalServicesUpdateComponent } from 'app/entities/wo-additional-services/wo-additional-services-update.component';
import { WoAdditionalServicesService } from 'app/entities/wo-additional-services/wo-additional-services.service';
import { WoAdditionalServices } from 'app/shared/model/wo-additional-services.model';

describe('Component Tests', () => {
  describe('WoAdditionalServices Management Update Component', () => {
    let comp: WoAdditionalServicesUpdateComponent;
    let fixture: ComponentFixture<WoAdditionalServicesUpdateComponent>;
    let service: WoAdditionalServicesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoAdditionalServicesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoAdditionalServicesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoAdditionalServicesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoAdditionalServicesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoAdditionalServices(123);
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
        const entity = new WoAdditionalServices();
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
