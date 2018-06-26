/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { FeatureExcludedDetailComponent } from 'app/entities/feature-excluded/feature-excluded-detail.component';
import { FeatureExcluded } from 'app/shared/model/feature-excluded.model';

describe('Component Tests', () => {
    describe('FeatureExcluded Management Detail Component', () => {
        let comp: FeatureExcludedDetailComponent;
        let fixture: ComponentFixture<FeatureExcludedDetailComponent>;
        const route = ({ data: of({ featureExcluded: new FeatureExcluded(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [FeatureExcludedDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FeatureExcludedDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FeatureExcludedDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.featureExcluded).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
