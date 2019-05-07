/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoShippingInfoComponent } from 'app/entities/wo-shipping-info/wo-shipping-info.component';
import { WoShippingInfoService } from 'app/entities/wo-shipping-info/wo-shipping-info.service';
import { WoShippingInfo } from 'app/shared/model/wo-shipping-info.model';

describe('Component Tests', () => {
  describe('WoShippingInfo Management Component', () => {
    let comp: WoShippingInfoComponent;
    let fixture: ComponentFixture<WoShippingInfoComponent>;
    let service: WoShippingInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoShippingInfoComponent],
        providers: []
      })
        .overrideTemplate(WoShippingInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoShippingInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoShippingInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoShippingInfo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woShippingInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
