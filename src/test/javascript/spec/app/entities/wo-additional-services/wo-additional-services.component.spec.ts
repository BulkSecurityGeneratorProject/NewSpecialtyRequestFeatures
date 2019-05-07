/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoAdditionalServicesComponent } from 'app/entities/wo-additional-services/wo-additional-services.component';
import { WoAdditionalServicesService } from 'app/entities/wo-additional-services/wo-additional-services.service';
import { WoAdditionalServices } from 'app/shared/model/wo-additional-services.model';

describe('Component Tests', () => {
  describe('WoAdditionalServices Management Component', () => {
    let comp: WoAdditionalServicesComponent;
    let fixture: ComponentFixture<WoAdditionalServicesComponent>;
    let service: WoAdditionalServicesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoAdditionalServicesComponent],
        providers: []
      })
        .overrideTemplate(WoAdditionalServicesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoAdditionalServicesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoAdditionalServicesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoAdditionalServices(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woAdditionalServices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
