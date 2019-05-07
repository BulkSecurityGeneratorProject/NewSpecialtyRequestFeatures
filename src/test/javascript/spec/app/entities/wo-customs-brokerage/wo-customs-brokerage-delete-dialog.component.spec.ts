/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoCustomsBrokerageDeleteDialogComponent } from 'app/entities/wo-customs-brokerage/wo-customs-brokerage-delete-dialog.component';
import { WoCustomsBrokerageService } from 'app/entities/wo-customs-brokerage/wo-customs-brokerage.service';

describe('Component Tests', () => {
  describe('WoCustomsBrokerage Management Delete Component', () => {
    let comp: WoCustomsBrokerageDeleteDialogComponent;
    let fixture: ComponentFixture<WoCustomsBrokerageDeleteDialogComponent>;
    let service: WoCustomsBrokerageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoCustomsBrokerageDeleteDialogComponent]
      })
        .overrideTemplate(WoCustomsBrokerageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoCustomsBrokerageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoCustomsBrokerageService);
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
