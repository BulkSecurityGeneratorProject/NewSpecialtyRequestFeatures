import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoPickPack } from 'app/shared/model/wo-pick-pack.model';
import { WoPickPackService } from './wo-pick-pack.service';

@Component({
  selector: 'jhi-wo-pick-pack-delete-dialog',
  templateUrl: './wo-pick-pack-delete-dialog.component.html'
})
export class WoPickPackDeleteDialogComponent {
  woPickPack: IWoPickPack;

  constructor(
    protected woPickPackService: WoPickPackService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woPickPackService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woPickPackListModification',
        content: 'Deleted an woPickPack'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-pick-pack-delete-popup',
  template: ''
})
export class WoPickPackDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woPickPack }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoPickPackDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woPickPack = woPickPack;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-pick-pack', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-pick-pack', { outlets: { popup: null } }]);
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
