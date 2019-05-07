/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoWorkOrderDetailComponent } from 'app/entities/wo-work-order/wo-work-order-detail.component';
import { WoWorkOrder } from 'app/shared/model/wo-work-order.model';

describe('Component Tests', () => {
  describe('WoWorkOrder Management Detail Component', () => {
    let comp: WoWorkOrderDetailComponent;
    let fixture: ComponentFixture<WoWorkOrderDetailComponent>;
    const route = ({ data: of({ woWorkOrder: new WoWorkOrder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoWorkOrderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoWorkOrderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoWorkOrderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woWorkOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
