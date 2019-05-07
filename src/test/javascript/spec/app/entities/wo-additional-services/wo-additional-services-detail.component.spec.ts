/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoAdditionalServicesDetailComponent } from 'app/entities/wo-additional-services/wo-additional-services-detail.component';
import { WoAdditionalServices } from 'app/shared/model/wo-additional-services.model';

describe('Component Tests', () => {
  describe('WoAdditionalServices Management Detail Component', () => {
    let comp: WoAdditionalServicesDetailComponent;
    let fixture: ComponentFixture<WoAdditionalServicesDetailComponent>;
    const route = ({ data: of({ woAdditionalServices: new WoAdditionalServices(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoAdditionalServicesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoAdditionalServicesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoAdditionalServicesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woAdditionalServices).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
