/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoShippingServicesDetailComponent } from 'app/entities/wo-shipping-services/wo-shipping-services-detail.component';
import { WoShippingServices } from 'app/shared/model/wo-shipping-services.model';

describe('Component Tests', () => {
  describe('WoShippingServices Management Detail Component', () => {
    let comp: WoShippingServicesDetailComponent;
    let fixture: ComponentFixture<WoShippingServicesDetailComponent>;
    const route = ({ data: of({ woShippingServices: new WoShippingServices(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoShippingServicesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoShippingServicesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoShippingServicesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woShippingServices).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
