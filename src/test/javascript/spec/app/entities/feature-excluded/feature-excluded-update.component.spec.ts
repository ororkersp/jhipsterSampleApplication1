/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { FeatureExcludedUpdateComponent } from 'app/entities/feature-excluded/feature-excluded-update.component';
import { FeatureExcludedService } from 'app/entities/feature-excluded/feature-excluded.service';
import { FeatureExcluded } from 'app/shared/model/feature-excluded.model';

describe('Component Tests', () => {
    describe('FeatureExcluded Management Update Component', () => {
        let comp: FeatureExcludedUpdateComponent;
        let fixture: ComponentFixture<FeatureExcludedUpdateComponent>;
        let service: FeatureExcludedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [FeatureExcludedUpdateComponent]
            })
                .overrideTemplate(FeatureExcludedUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FeatureExcludedUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeatureExcludedService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FeatureExcluded(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.featureExcluded = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FeatureExcluded();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.featureExcluded = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
