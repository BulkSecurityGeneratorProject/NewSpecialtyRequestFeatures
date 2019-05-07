import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoShippingInfo } from 'app/shared/model/wo-shipping-info.model';
import { WoShippingInfoService } from './wo-shipping-info.service';

@Component({
  selector: 'jhi-wo-shipping-info-delete-dialog',
  templateUrl: './wo-shipping-info-delete-dialog.component.html'
})
export class WoShippingInfoDeleteDialogComponent {
  woShippingInfo: IWoShippingInfo;

  constructor(
    protected woShippingInfoService: WoShippingInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woShippingInfoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woShippingInfoListModification',
        content: 'Deleted an woShippingInfo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-shipping-info-delete-popup',
  template: ''
})
export class WoShippingInfoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woShippingInfo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoShippingInfoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woShippingInfo = woShippingInfo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-shipping-info', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-shipping-info', { outlets: { popup: null } }]);
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
