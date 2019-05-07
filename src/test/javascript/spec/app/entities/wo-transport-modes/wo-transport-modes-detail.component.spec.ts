/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewSpecialtyRequestFeaturesTestModule } from '../../../test.module';
import { WoTransportModesDetailComponent } from 'app/entities/wo-transport-modes/wo-transport-modes-detail.component';
import { WoTransportModes } from 'app/shared/model/wo-transport-modes.model';

describe('Component Tests', () => {
  describe('WoTransportModes Management Detail Component', () => {
    let comp: WoTransportModesDetailComponent;
    let fixture: ComponentFixture<WoTransportModesDetailComponent>;
    const route = ({ data: of({ woTransportModes: new WoTransportModes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewSpecialtyRequestFeaturesTestModule],
        declarations: [WoTransportModesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoTransportModesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoTransportModesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woTransportModes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
