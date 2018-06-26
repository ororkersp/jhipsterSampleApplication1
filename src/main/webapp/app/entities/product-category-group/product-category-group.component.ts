import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProductCategoryGroup } from 'app/shared/model/product-category-group.model';
import { Principal } from 'app/core';
import { ProductCategoryGroupService } from './product-category-group.service';

@Component({
    selector: '-product-category-group',
    templateUrl: './product-category-group.component.html'
})
export class ProductCategoryGroupComponent implements OnInit, OnDestroy {
    productCategoryGroups: IProductCategoryGroup[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private productCategoryGroupService: ProductCategoryGroupService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.productCategoryGroupService.query().subscribe(
            (res: HttpResponse<IProductCategoryGroup[]>) => {
                this.productCategoryGroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProductCategoryGroups();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProductCategoryGroup) {
        return item.id;
    }

    registerChangeInProductCategoryGroups() {
        this.eventSubscriber = this.eventManager.subscribe('productCategoryGroupListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
