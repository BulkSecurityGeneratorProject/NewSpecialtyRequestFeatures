/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoPackageTypeComponent } from 'app/entities/wo-package-type/wo-package-type.component';
import { WoPackageTypeService } from 'app/entities/wo-package-type/wo-package-type.service';
import { WoPackageType } from 'app/shared/model/wo-package-type.model';

describe('Component Tests', () => {
  describe('WoPackageType Management Component', () => {
    let comp: WoPackageTypeComponent;
    let fixture: ComponentFixture<WoPackageTypeComponent>;
    let service: WoPackageTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoPackageTypeComponent],
        providers: []
      })
        .overrideTemplate(WoPackageTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoPackageTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoPackageTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoPackageType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woPackageTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
