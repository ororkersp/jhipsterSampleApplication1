/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplication1TestModule } from '../../../test.module';
import { ProductCategoryGroupDetailComponent } from 'app/entities/product-category-group/product-category-group-detail.component';
import { ProductCategoryGroup } from 'app/shared/model/product-category-group.model';

describe('Component Tests', () => {
    describe('ProductCategoryGroup Management Detail Component', () => {
        let comp: ProductCategoryGroupDetailComponent;
        let fixture: ComponentFixture<ProductCategoryGroupDetailComponent>;
        const route = ({ data: of({ productCategoryGroup: new ProductCategoryGroup(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplication1TestModule],
                declarations: [ProductCategoryGroupDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProductCategoryGroupDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProductCategoryGroupDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.productCategoryGroup).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
