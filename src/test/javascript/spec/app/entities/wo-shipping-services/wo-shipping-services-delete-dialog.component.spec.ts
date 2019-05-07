/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoShippingServicesDeleteDialogComponent } from 'app/entities/wo-shipping-services/wo-shipping-services-delete-dialog.component';
import { WoShippingServicesService } from 'app/entities/wo-shipping-services/wo-shipping-services.service';

describe('Component Tests', () => {
  describe('WoShippingServices Management Delete Component', () => {
    let comp: WoShippingServicesDeleteDialogComponent;
    let fixture: ComponentFixture<WoShippingServicesDeleteDialogComponent>;
    let service: WoShippingServicesService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoShippingServicesDeleteDialogComponent]
      })
        .overrideTemplate(WoShippingServicesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoShippingServicesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoShippingServicesService);
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
