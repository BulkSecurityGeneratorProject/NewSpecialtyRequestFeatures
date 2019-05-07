/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoWorkOrderComponent } from 'app/entities/wo-work-order/wo-work-order.component';
import { WoWorkOrderService } from 'app/entities/wo-work-order/wo-work-order.service';
import { WoWorkOrder } from 'app/shared/model/wo-work-order.model';

describe('Component Tests', () => {
  describe('WoWorkOrder Management Component', () => {
    let comp: WoWorkOrderComponent;
    let fixture: ComponentFixture<WoWorkOrderComponent>;
    let service: WoWorkOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoWorkOrderComponent],
        providers: []
      })
        .overrideTemplate(WoWorkOrderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoWorkOrderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoWorkOrderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoWorkOrder(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woWorkOrders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
