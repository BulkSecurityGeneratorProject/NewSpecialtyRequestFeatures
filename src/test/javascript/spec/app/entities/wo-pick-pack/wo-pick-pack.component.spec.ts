/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoPickPackComponent } from 'app/entities/wo-pick-pack/wo-pick-pack.component';
import { WoPickPackService } from 'app/entities/wo-pick-pack/wo-pick-pack.service';
import { WoPickPack } from 'app/shared/model/wo-pick-pack.model';

describe('Component Tests', () => {
  describe('WoPickPack Management Component', () => {
    let comp: WoPickPackComponent;
    let fixture: ComponentFixture<WoPickPackComponent>;
    let service: WoPickPackService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoPickPackComponent],
        providers: []
      })
        .overrideTemplate(WoPickPackComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoPickPackComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoPickPackService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoPickPack(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woPickPacks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
