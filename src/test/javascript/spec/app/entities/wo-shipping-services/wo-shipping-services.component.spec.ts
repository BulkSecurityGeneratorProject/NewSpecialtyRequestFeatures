/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoShippingServicesComponent } from 'app/entities/wo-shipping-services/wo-shipping-services.component';
import { WoShippingServicesService } from 'app/entities/wo-shipping-services/wo-shipping-services.service';
import { WoShippingServices } from 'app/shared/model/wo-shipping-services.model';

describe('Component Tests', () => {
  describe('WoShippingServices Management Component', () => {
    let comp: WoShippingServicesComponent;
    let fixture: ComponentFixture<WoShippingServicesComponent>;
    let service: WoShippingServicesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoShippingServicesComponent],
        providers: []
      })
        .overrideTemplate(WoShippingServicesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoShippingServicesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoShippingServicesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoShippingServices(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woShippingServices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
