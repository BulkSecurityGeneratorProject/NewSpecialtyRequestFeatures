/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoPackageTypeDetailComponent } from 'app/entities/wo-package-type/wo-package-type-detail.component';
import { WoPackageType } from 'app/shared/model/wo-package-type.model';

describe('Component Tests', () => {
  describe('WoPackageType Management Detail Component', () => {
    let comp: WoPackageTypeDetailComponent;
    let fixture: ComponentFixture<WoPackageTypeDetailComponent>;
    const route = ({ data: of({ woPackageType: new WoPackageType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoPackageTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoPackageTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoPackageTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woPackageType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
