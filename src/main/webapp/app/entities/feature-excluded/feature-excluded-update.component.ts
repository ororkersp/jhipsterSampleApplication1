import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFeatureExcluded } from 'app/shared/model/feature-excluded.model';
import { FeatureExcludedService } from './feature-excluded.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product';

@Component({
    selector: '-feature-excluded-update',
    templateUrl: './feature-excluded-update.component.html'
})
export class FeatureExcludedUpdateComponent implements OnInit {
    private _featureExcluded: IFeatureExcluded;
    isSaving: boolean;

    products: IProduct[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private featureExcludedService: FeatureExcludedService,
        private productService: ProductService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ featureExcluded }) => {
            this.featureExcluded = featureExcluded;
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
        if (this.featureExcluded.id !== undefined) {
            this.subscribeToSaveResponse(this.featureExcludedService.update(this.featureExcluded));
        } else {
            this.subscribeToSaveResponse(this.featureExcludedService.create(this.featureExcluded));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFeatureExcluded>>) {
        result.subscribe((res: HttpResponse<IFeatureExcluded>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get featureExcluded() {
        return this._featureExcluded;
    }

    set featureExcluded(featureExcluded: IFeatureExcluded) {
        this._featureExcluded = featureExcluded;
    }
}
