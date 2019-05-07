/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoStorageDetailComponent } from 'app/entities/wo-storage/wo-storage-detail.component';
import { WoStorage } from 'app/shared/model/wo-storage.model';

describe('Component Tests', () => {
  describe('WoStorage Management Detail Component', () => {
    let comp: WoStorageDetailComponent;
    let fixture: ComponentFixture<WoStorageDetailComponent>;
    const route = ({ data: of({ woStorage: new WoStorage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoStorageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoStorageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoStorageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woStorage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
