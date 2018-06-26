import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFeatureIncluded } from 'app/shared/model/feature-included.model';
import { FeatureIncludedService } from './feature-included.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product';

@Component({
    selector: '-feature-included-update',
    templateUrl: './feature-included-update.component.html'
})
export class FeatureIncludedUpdateComponent implements OnInit {
    private _featureIncluded: IFeatureIncluded;
    isSaving: boolean;

    products: IProduct[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private featureIncludedService: FeatureIncludedService,
        private productService: ProductService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ featureIncluded }) => {
            this.featureIncluded = featureIncluded;
        });
        this.productService.query().subscribe(
            (res: HttpResponse<IProduct[]>) => {
                this.products = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.featureIncluded.id !== undefined) {
            this.subscribeToSaveResponse(this.featureIncludedService.update(this.featureIncluded));
        } else {
            this.subscribeToSaveResponse(this.featureIncludedService.create(this.featureIncluded));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFeatureIncluded>>) {
        result.subscribe((res: HttpResponse<IFeatureIncluded>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackProductById(index: number, item: IProduct) {
        return item.id;
    }
    get featureIncluded() {
        return this._featureIncluded;
    }

    set featureIncluded(featureIncluded: IFeatureIncluded) {
        this._featureIncluded = featureIncluded;
    }
}
