import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProductCategoryGroup } from 'app/shared/model/product-category-group.model';
import { ProductCategoryGroupService } from './product-category-group.service';
import { IProductType } from 'app/shared/model/product-type.model';
import { ProductTypeService } from 'app/entities/product-type';

@Component({
    selector: '-product-category-group-update',
    templateUrl: './product-category-group-update.component.html'
})
export class ProductCategoryGroupUpdateComponent implements OnInit {
    private _productCategoryGroup: IProductCategoryGroup;
    isSaving: boolean;

    producttypes: IProductType[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private productCategoryGroupService: ProductCategoryGroupService,
        private productTypeService: ProductTypeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ productCategoryGroup }) => {
            this.productCategoryGroup = productCategoryGroup;
        });
        this.productTypeService.query().subscribe(
            (res: HttpResponse<IProductType[]>) => {
                this.producttypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.productCategoryGroup.id !== undefined) {
            this.subscribeToSaveResponse(this.productCategoryGroupService.update(this.productCategoryGroup));
        } else {
            this.subscribeToSaveResponse(this.productCategoryGroupService.create(this.productCategoryGroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProductCategoryGroup>>) {
        result.subscribe(
            (res: HttpResponse<IProductCategoryGroup>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProductTypeById(index: number, item: IProductType) {
        return item.id;
    }
    get productCategoryGroup() {
        return this._productCategoryGroup;
    }

    set productCategoryGroup(productCategoryGroup: IProductCategoryGroup) {
        this._productCategoryGroup = productCategoryGroup;
    }
}
