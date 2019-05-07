/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoCustomsBrokerageUpdateComponent } from 'app/entities/wo-customs-brokerage/wo-customs-brokerage-update.component';
import { WoCustomsBrokerageService } from 'app/entities/wo-customs-brokerage/wo-customs-brokerage.service';
import { WoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';

describe('Component Tests', () => {
  describe('WoCustomsBrokerage Management Update Component', () => {
    let comp: WoCustomsBrokerageUpdateComponent;
    let fixture: ComponentFixture<WoCustomsBrokerageUpdateComponent>;
    let service: WoCustomsBrokerageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoCustomsBrokerageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoCustomsBrokerageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoCustomsBrokerageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoCustomsBrokerageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoCustomsBrokerage(123);
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
        const entity = new WoCustomsBrokerage();
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
