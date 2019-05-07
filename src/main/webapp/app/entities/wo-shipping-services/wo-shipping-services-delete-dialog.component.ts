import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoShippingServices } from 'app/shared/model/wo-shipping-services.model';
import { WoShippingServicesService } from './wo-shipping-services.service';

@Component({
  selector: 'jhi-wo-shipping-services-delete-dialog',
  templateUrl: './wo-shipping-services-delete-dialog.component.html'
})
export class WoShippingServicesDeleteDialogComponent {
  woShippingServices: IWoShippingServices;

  constructor(
    protected woShippingServicesService: WoShippingServicesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woShippingServicesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woShippingServicesListModification',
        content: 'Deleted an woShippingServices'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-shipping-services-delete-popup',
  template: ''
})
export class WoShippingServicesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woShippingServices }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoShippingServicesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woShippingServices = woShippingServices;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-shipping-services', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-shipping-services', { outlets: { popup: null } }]);
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
