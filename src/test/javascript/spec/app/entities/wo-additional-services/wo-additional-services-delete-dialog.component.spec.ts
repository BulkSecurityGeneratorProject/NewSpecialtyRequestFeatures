/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoAdditionalServicesDeleteDialogComponent } from 'app/entities/wo-additional-services/wo-additional-services-delete-dialog.component';
import { WoAdditionalServicesService } from 'app/entities/wo-additional-services/wo-additional-services.service';

describe('Component Tests', () => {
  describe('WoAdditionalServices Management Delete Component', () => {
    let comp: WoAdditionalServicesDeleteDialogComponent;
    let fixture: ComponentFixture<WoAdditionalServicesDeleteDialogComponent>;
    let service: WoAdditionalServicesService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoAdditionalServicesDeleteDialogComponent]
      })
        .overrideTemplate(WoAdditionalServicesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoAdditionalServicesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoAdditionalServicesService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
