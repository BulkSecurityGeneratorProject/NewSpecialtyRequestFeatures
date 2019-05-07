/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoShippingInfoUpdateComponent } from 'app/entities/wo-shipping-info/wo-shipping-info-update.component';
import { WoShippingInfoService } from 'app/entities/wo-shipping-info/wo-shipping-info.service';
import { WoShippingInfo } from 'app/shared/model/wo-shipping-info.model';

describe('Component Tests', () => {
  describe('WoShippingInfo Management Update Component', () => {
    let comp: WoShippingInfoUpdateComponent;
    let fixture: ComponentFixture<WoShippingInfoUpdateComponent>;
    let service: WoShippingInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoShippingInfoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoShippingInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoShippingInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoShippingInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoShippingInfo(123);
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
        const entity = new WoShippingInfo();
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
