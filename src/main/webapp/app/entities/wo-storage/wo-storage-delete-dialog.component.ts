import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoStorage } from 'app/shared/model/wo-storage.model';
import { WoStorageService } from './wo-storage.service';

@Component({
  selector: 'jhi-wo-storage-delete-dialog',
  templateUrl: './wo-storage-delete-dialog.component.html'
})
export class WoStorageDeleteDialogComponent {
  woStorage: IWoStorage;

  constructor(protected woStorageService: WoStorageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woStorageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woStorageListModification',
        content: 'Deleted an woStorage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-storage-delete-popup',
  template: ''
})
export class WoStorageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woStorage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoStorageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woStorage = woStorage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-storage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-storage', { outlets: { popup: null } }]);
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
