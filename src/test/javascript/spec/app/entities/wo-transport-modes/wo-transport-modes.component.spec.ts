/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoTransportModesComponent } from 'app/entities/wo-transport-modes/wo-transport-modes.component';
import { WoTransportModesService } from 'app/entities/wo-transport-modes/wo-transport-modes.service';
import { WoTransportModes } from 'app/shared/model/wo-transport-modes.model';

describe('Component Tests', () => {
  describe('WoTransportModes Management Component', () => {
    let comp: WoTransportModesComponent;
    let fixture: ComponentFixture<WoTransportModesComponent>;
    let service: WoTransportModesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoTransportModesComponent],
        providers: []
      })
        .overrideTemplate(WoTransportModesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoTransportModesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoTransportModesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoTransportModes(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woTransportModes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
