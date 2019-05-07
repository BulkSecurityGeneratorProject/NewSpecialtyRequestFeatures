import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';
import { WoCustomsBrokerageService } from './wo-customs-brokerage.service';

@Component({
  selector: 'jhi-wo-customs-brokerage-delete-dialog',
  templateUrl: './wo-customs-brokerage-delete-dialog.component.html'
})
export class WoCustomsBrokerageDeleteDialogComponent {
  woCustomsBrokerage: IWoCustomsBrokerage;

  constructor(
    protected woCustomsBrokerageService: WoCustomsBrokerageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woCustomsBrokerageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woCustomsBrokerageListModification',
        content: 'Deleted an woCustomsBrokerage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-customs-brokerage-delete-popup',
  template: ''
})
export class WoCustomsBrokerageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woCustomsBrokerage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoCustomsBrokerageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woCustomsBrokerage = woCustomsBrokerage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-customs-brokerage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-customs-brokerage', { outlets: { popup: null } }]);
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
