import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoAdditionalServices } from 'app/shared/model/wo-additional-services.model';
import { WoAdditionalServicesService } from './wo-additional-services.service';

@Component({
  selector: 'jhi-wo-additional-services-delete-dialog',
  templateUrl: './wo-additional-services-delete-dialog.component.html'
})
export class WoAdditionalServicesDeleteDialogComponent {
  woAdditionalServices: IWoAdditionalServices;

  constructor(
    protected woAdditionalServicesService: WoAdditionalServicesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woAdditionalServicesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woAdditionalServicesListModification',
        content: 'Deleted an woAdditionalServices'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-additional-services-delete-popup',
  template: ''
})
export class WoAdditionalServicesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woAdditionalServices }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoAdditionalServicesDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.woAdditionalServices = woAdditionalServices;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-additional-services', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-additional-services', { outlets: { popup: null } }]);
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
