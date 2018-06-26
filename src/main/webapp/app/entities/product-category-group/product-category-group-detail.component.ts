import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductCategoryGroup } from 'app/shared/model/product-category-group.model';

@Component({
    selector: '-product-category-group-detail',
    templateUrl: './product-category-group-detail.component.html'
})
export class ProductCategoryGroupDetailComponent implements OnInit {
    productCategoryGroup: IProductCategoryGroup;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ productCategoryGroup }) => {
            this.productCategoryGroup = productCategoryGroup;
        });
    }

    previousState() {
        window.history.back();
    }
}
