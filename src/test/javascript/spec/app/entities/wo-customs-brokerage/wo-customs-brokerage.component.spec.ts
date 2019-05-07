/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoCustomsBrokerageComponent } from 'app/entities/wo-customs-brokerage/wo-customs-brokerage.component';
import { WoCustomsBrokerageService } from 'app/entities/wo-customs-brokerage/wo-customs-brokerage.service';
import { WoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';

describe('Component Tests', () => {
  describe('WoCustomsBrokerage Management Component', () => {
    let comp: WoCustomsBrokerageComponent;
    let fixture: ComponentFixture<WoCustomsBrokerageComponent>;
    let service: WoCustomsBrokerageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoCustomsBrokerageComponent],
        providers: []
      })
        .overrideTemplate(WoCustomsBrokerageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoCustomsBrokerageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoCustomsBrokerageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoCustomsBrokerage(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woCustomsBrokerages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
