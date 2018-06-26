/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { FeatureIncludedDetailComponent } from 'app/entities/feature-included/feature-included-detail.component';
import { FeatureIncluded } from 'app/shared/model/feature-included.model';

describe('Component Tests', () => {
    describe('FeatureIncluded Management Detail Component', () => {
        let comp: FeatureIncludedDetailComponent;
        let fixture: ComponentFixture<FeatureIncludedDetailComponent>;
        const route = ({ data: of({ featureIncluded: new FeatureIncluded(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [FeatureIncludedDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FeatureIncludedDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FeatureIncludedDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.featureIncluded).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
