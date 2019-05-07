import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from './wo-work-order.service';

@Component({
  selector: 'jhi-wo-work-order-delete-dialog',
  templateUrl: './wo-work-order-delete-dialog.component.html'
})
export class WoWorkOrderDeleteDialogComponent {
  woWorkOrder: IWoWorkOrder;

  constructor(
    protected woWorkOrderService: WoWorkOrderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woWorkOrderService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woWorkOrderListModification',
        content: 'Deleted an woWorkOrder'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-work-order-delete-popup',
  template: ''
})
export class WoWorkOrderDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woWorkOrder }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoWorkOrderDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woWorkOrder = woWorkOrder;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-work-order', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-work-order', { outlets: { popup: null } }]);
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
