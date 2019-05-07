/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoPickPackDeleteDialogComponent } from 'app/entities/wo-pick-pack/wo-pick-pack-delete-dialog.component';
import { WoPickPackService } from 'app/entities/wo-pick-pack/wo-pick-pack.service';

describe('Component Tests', () => {
  describe('WoPickPack Management Delete Component', () => {
    let comp: WoPickPackDeleteDialogComponent;
    let fixture: ComponentFixture<WoPickPackDeleteDialogComponent>;
    let service: WoPickPackService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoPickPackDeleteDialogComponent]
      })
        .overrideTemplate(WoPickPackDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoPickPackDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoPickPackService);
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
