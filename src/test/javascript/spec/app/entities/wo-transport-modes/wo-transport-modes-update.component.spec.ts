/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoTransportModesUpdateComponent } from 'app/entities/wo-transport-modes/wo-transport-modes-update.component';
import { WoTransportModesService } from 'app/entities/wo-transport-modes/wo-transport-modes.service';
import { WoTransportModes } from 'app/shared/model/wo-transport-modes.model';

describe('Component Tests', () => {
  describe('WoTransportModes Management Update Component', () => {
    let comp: WoTransportModesUpdateComponent;
    let fixture: ComponentFixture<WoTransportModesUpdateComponent>;
    let service: WoTransportModesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoTransportModesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoTransportModesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoTransportModesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoTransportModesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoTransportModes(123);
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
        const entity = new WoTransportModes();
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
