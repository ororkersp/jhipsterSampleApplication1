/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { ProductCategoryGroupDeleteDialogComponent } from 'app/entities/product-category-group/product-category-group-delete-dialog.component';
import { ProductCategoryGroupService } from 'app/entities/product-category-group/product-category-group.service';

describe('Component Tests', () => {
    describe('ProductCategoryGroup Management Delete Component', () => {
        let comp: ProductCategoryGroupDeleteDialogComponent;
        let fixture: ComponentFixture<ProductCategoryGroupDeleteDialogComponent>;
        let service: ProductCategoryGroupService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [ProductCategoryGroupDeleteDialogComponent]
            })
                .overrideTemplate(ProductCategoryGroupDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProductCategoryGroupDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductCategoryGroupService);
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
