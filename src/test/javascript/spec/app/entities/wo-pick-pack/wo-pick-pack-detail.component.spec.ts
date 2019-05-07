/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoPickPackDetailComponent } from 'app/entities/wo-pick-pack/wo-pick-pack-detail.component';
import { WoPickPack } from 'app/shared/model/wo-pick-pack.model';

describe('Component Tests', () => {
  describe('WoPickPack Management Detail Component', () => {
    let comp: WoPickPackDetailComponent;
    let fixture: ComponentFixture<WoPickPackDetailComponent>;
    const route = ({ data: of({ woPickPack: new WoPickPack(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoPickPackDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoPickPackDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoPickPackDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woPickPack).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
