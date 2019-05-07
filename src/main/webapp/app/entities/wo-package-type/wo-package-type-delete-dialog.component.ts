import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from './wo-package-type.service';

@Component({
  selector: 'jhi-wo-package-type-delete-dialog',
  templateUrl: './wo-package-type-delete-dialog.component.html'
})
export class WoPackageTypeDeleteDialogComponent {
  woPackageType: IWoPackageType;

  constructor(
    protected woPackageTypeService: WoPackageTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woPackageTypeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woPackageTypeListModification',
        content: 'Deleted an woPackageType'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-package-type-delete-popup',
  template: ''
})
export class WoPackageTypeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woPackageType }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoPackageTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woPackageType = woPackageType;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-package-type', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-package-type', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
