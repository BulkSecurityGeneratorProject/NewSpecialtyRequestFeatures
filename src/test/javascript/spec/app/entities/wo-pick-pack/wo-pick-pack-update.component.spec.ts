/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoPickPackUpdateComponent } from 'app/entities/wo-pick-pack/wo-pick-pack-update.component';
import { WoPickPackService } from 'app/entities/wo-pick-pack/wo-pick-pack.service';
import { WoPickPack } from 'app/shared/model/wo-pick-pack.model';

describe('Component Tests', () => {
  describe('WoPickPack Management Update Component', () => {
    let comp: WoPickPackUpdateComponent;
    let fixture: ComponentFixture<WoPickPackUpdateComponent>;
    let service: WoPickPackService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoPickPackUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoPickPackUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoPickPackUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoPickPackService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoPickPack(123);
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
        const entity = new WoPickPack();
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
