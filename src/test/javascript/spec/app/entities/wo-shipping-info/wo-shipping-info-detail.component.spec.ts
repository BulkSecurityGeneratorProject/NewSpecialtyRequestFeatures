/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoShippingInfoDetailComponent } from 'app/entities/wo-shipping-info/wo-shipping-info-detail.component';
import { WoShippingInfo } from 'app/shared/model/wo-shipping-info.model';

describe('Component Tests', () => {
  describe('WoShippingInfo Management Detail Component', () => {
    let comp: WoShippingInfoDetailComponent;
    let fixture: ComponentFixture<WoShippingInfoDetailComponent>;
    const route = ({ data: of({ woShippingInfo: new WoShippingInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoShippingInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoShippingInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoShippingInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woShippingInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
