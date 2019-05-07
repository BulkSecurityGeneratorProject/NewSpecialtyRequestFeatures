/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoCustomsBrokerageDetailComponent } from 'app/entities/wo-customs-brokerage/wo-customs-brokerage-detail.component';
import { WoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';

describe('Component Tests', () => {
  describe('WoCustomsBrokerage Management Detail Component', () => {
    let comp: WoCustomsBrokerageDetailComponent;
    let fixture: ComponentFixture<WoCustomsBrokerageDetailComponent>;
    const route = ({ data: of({ woCustomsBrokerage: new WoCustomsBrokerage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoCustomsBrokerageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoCustomsBrokerageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoCustomsBrokerageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woCustomsBrokerage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
