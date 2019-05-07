/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoStorageComponent } from 'app/entities/wo-storage/wo-storage.component';
import { WoStorageService } from 'app/entities/wo-storage/wo-storage.service';
import { WoStorage } from 'app/shared/model/wo-storage.model';

describe('Component Tests', () => {
  describe('WoStorage Management Component', () => {
    let comp: WoStorageComponent;
    let fixture: ComponentFixture<WoStorageComponent>;
    let service: WoStorageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoStorageComponent],
        providers: []
      })
        .overrideTemplate(WoStorageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoStorageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoStorageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoStorage(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woStorages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
