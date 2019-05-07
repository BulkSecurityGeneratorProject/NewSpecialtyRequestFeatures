import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoTransportModes } from 'app/shared/model/wo-transport-modes.model';
import { WoTransportModesService } from './wo-transport-modes.service';

@Component({
  selector: 'jhi-wo-transport-modes-delete-dialog',
  templateUrl: './wo-transport-modes-delete-dialog.component.html'
})
export class WoTransportModesDeleteDialogComponent {
  woTransportModes: IWoTransportModes;

  constructor(
    protected woTransportModesService: WoTransportModesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woTransportModesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woTransportModesListModification',
        content: 'Deleted an woTransportModes'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-transport-modes-delete-popup',
  template: ''
})
export class WoTransportModesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woTransportModes }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoTransportModesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woTransportModes = woTransportModes;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-transport-modes', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-transport-modes', { outlets: { popup: null } }]);
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
