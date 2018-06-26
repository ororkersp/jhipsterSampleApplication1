/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { FeatureIncludedComponent } from 'app/entities/feature-included/feature-included.component';
import { FeatureIncludedService } from 'app/entities/feature-included/feature-included.service';
import { FeatureIncluded } from 'app/shared/model/feature-included.model';

describe('Component Tests', () => {
    describe('FeatureIncluded Management Component', () => {
        let comp: FeatureIncludedComponent;
        let fixture: ComponentFixture<FeatureIncludedComponent>;
        let service: FeatureIncludedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [FeatureIncludedComponent],
                providers: []
            })
                .overrideTemplate(FeatureIncludedComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FeatureIncludedComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeatureIncludedService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new FeatureIncluded(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.featureIncludeds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
