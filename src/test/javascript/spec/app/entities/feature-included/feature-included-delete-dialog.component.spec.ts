/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { FeatureIncludedDeleteDialogComponent } from 'app/entities/feature-included/feature-included-delete-dialog.component';
import { FeatureIncludedService } from 'app/entities/feature-included/feature-included.service';

describe('Component Tests', () => {
    describe('FeatureIncluded Management Delete Component', () => {
        let comp: FeatureIncludedDeleteDialogComponent;
        let fixture: ComponentFixture<FeatureIncludedDeleteDialogComponent>;
        let service: FeatureIncludedService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [FeatureIncludedDeleteDialogComponent]
            })
                .overrideTemplate(FeatureIncludedDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FeatureIncludedDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FeatureIncludedService);
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
