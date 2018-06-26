import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFeatureIncluded } from 'app/shared/model/feature-included.model';
import { Principal } from 'app/core';
import { FeatureIncludedService } from './feature-included.service';

@Component({
    selector: '-feature-included',
    templateUrl: './feature-included.component.html'
})
export class FeatureIncludedComponent implements OnInit, OnDestroy {
    featureIncludeds: IFeatureIncluded[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private featureIncludedService: FeatureIncludedService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.featureIncludedService.query().subscribe(
            (res: HttpResponse<IFeatureIncluded[]>) => {
                this.featureIncludeds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFeatureIncludeds();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFeatureIncluded) {
        return item.id;
    }

    registerChangeInFeatureIncludeds() {
        this.eventSubscriber = this.eventManager.subscribe('featureIncludedListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
