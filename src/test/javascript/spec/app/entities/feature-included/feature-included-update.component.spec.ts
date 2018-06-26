/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { FeatureIncludedUpdateComponent } from 'app/entities/feature-included/feature-included-update.component';
import { FeatureIncludedService } from 'app/entities/feature-included/feature-included.service';
import { FeatureIncluded } from 'app/shared/model/feature-included.model';

describe('Component Tests', () => {
    describe('FeatureIncluded Management Update Component', () => {
        let comp: FeatureIncludedUpdateComponent;
        let fixture: ComponentFixture<FeatureIncludedUpdateComponent>;
        let service: FeatureIncludedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [FeatureIncludedUpdateComponent]
            })
                .overrideTemplate(FeatureIncludedUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FeatureIncludedUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeatureIncludedService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FeatureIncluded(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.featureIncluded = entity;
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
                    const entity = new FeatureIncluded();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.featureIncluded = entity;
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
