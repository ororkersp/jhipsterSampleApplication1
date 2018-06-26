import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProductType } from 'app/shared/model/product-type.model';
import { Principal } from 'app/core';
import { ProductTypeService } from './product-type.service';

@Component({
    selector: '-product-type',
    templateUrl: './product-type.component.html'
})
export class ProductTypeComponent implements OnInit, OnDestroy {
    productTypes: IProductType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private productTypeService: ProductTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.productTypeService.query().subscribe(
            (res: HttpResponse<IProductType[]>) => {
                this.productTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProductTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProductType) {
        return item.id;
    }

    registerChangeInProductTypes() {
        this.eventSubscriber = this.eventManager.subscribe('productTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
