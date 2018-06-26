/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { ProductCategoryGroupUpdateComponent } from 'app/entities/product-category-group/product-category-group-update.component';
import { ProductCategoryGroupService } from 'app/entities/product-category-group/product-category-group.service';
import { ProductCategoryGroup } from 'app/shared/model/product-category-group.model';

describe('Component Tests', () => {
    describe('ProductCategoryGroup Management Update Component', () => {
        let comp: ProductCategoryGroupUpdateComponent;
        let fixture: ComponentFixture<ProductCategoryGroupUpdateComponent>;
        let service: ProductCategoryGroupService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [ProductCategoryGroupUpdateComponent]
            })
                .overrideTemplate(ProductCategoryGroupUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductCategoryGroupUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductCategoryGroupService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProductCategoryGroup(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.productCategoryGroup = entity;
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
                    const entity = new ProductCategoryGroup();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.productCategoryGroup = entity;
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
