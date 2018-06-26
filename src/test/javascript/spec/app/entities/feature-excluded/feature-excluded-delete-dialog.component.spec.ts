/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { FeatureExcludedDeleteDialogComponent } from 'app/entities/feature-excluded/feature-excluded-delete-dialog.component';
import { FeatureExcludedService } from 'app/entities/feature-excluded/feature-excluded.service';

describe('Component Tests', () => {
    describe('FeatureExcluded Management Delete Component', () => {
        let comp: FeatureExcludedDeleteDialogComponent;
        let fixture: ComponentFixture<FeatureExcludedDeleteDialogComponent>;
        let service: FeatureExcludedService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [FeatureExcludedDeleteDialogComponent]
            })
                .overrideTemplate(FeatureExcludedDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FeatureExcludedDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeatureExcludedService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
