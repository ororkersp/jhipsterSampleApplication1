/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { FeatureExcludedComponent } from 'app/entities/feature-excluded/feature-excluded.component';
import { FeatureExcludedService } from 'app/entities/feature-excluded/feature-excluded.service';
import { FeatureExcluded } from 'app/shared/model/feature-excluded.model';

describe('Component Tests', () => {
    describe('FeatureExcluded Management Component', () => {
        let comp: FeatureExcludedComponent;
        let fixture: ComponentFixture<FeatureExcludedComponent>;
        let service: FeatureExcludedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [FeatureExcludedComponent],
                providers: []
            })
                .overrideTemplate(FeatureExcludedComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FeatureExcludedComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeatureExcludedService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new FeatureExcluded(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.featureExcludeds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
