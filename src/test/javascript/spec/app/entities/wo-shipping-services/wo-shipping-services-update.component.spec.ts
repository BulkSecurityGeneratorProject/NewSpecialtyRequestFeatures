/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoShippingServicesUpdateComponent } from 'app/entities/wo-shipping-services/wo-shipping-services-update.component';
import { WoShippingServicesService } from 'app/entities/wo-shipping-services/wo-shipping-services.service';
import { WoShippingServices } from 'app/shared/model/wo-shipping-services.model';

describe('Component Tests', () => {
  describe('WoShippingServices Management Update Component', () => {
    let comp: WoShippingServicesUpdateComponent;
    let fixture: ComponentFixture<WoShippingServicesUpdateComponent>;
    let service: WoShippingServicesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoShippingServicesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoShippingServicesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoShippingServicesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoShippingServicesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoShippingServices(123);
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
        const entity = new WoShippingServices();
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
