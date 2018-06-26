/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { ProductCategoryGroupComponent } from 'app/entities/product-category-group/product-category-group.component';
import { ProductCategoryGroupService } from 'app/entities/product-category-group/product-category-group.service';
import { ProductCategoryGroup } from 'app/shared/model/product-category-group.model';

describe('Component Tests', () => {
    describe('ProductCategoryGroup Management Component', () => {
        let comp: ProductCategoryGroupComponent;
        let fixture: ComponentFixture<ProductCategoryGroupComponent>;
        let service: ProductCategoryGroupService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [ProductCategoryGroupComponent],
                providers: []
            })
                .overrideTemplate(ProductCategoryGroupComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductCategoryGroupComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductCategoryGroupService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProductCategoryGroup(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.productCategoryGroups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
