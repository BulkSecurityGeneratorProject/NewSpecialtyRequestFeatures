/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoPackageTypeDeleteDialogComponent } from 'app/entities/wo-package-type/wo-package-type-delete-dialog.component';
import { WoPackageTypeService } from 'app/entities/wo-package-type/wo-package-type.service';

describe('Component Tests', () => {
  describe('WoPackageType Management Delete Component', () => {
    let comp: WoPackageTypeDeleteDialogComponent;
    let fixture: ComponentFixture<WoPackageTypeDeleteDialogComponent>;
    let service: WoPackageTypeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoPackageTypeDeleteDialogComponent]
      })
        .overrideTemplate(WoPackageTypeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoPackageTypeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoPackageTypeService);
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
