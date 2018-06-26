import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFeatureExcluded } from 'app/shared/model/feature-excluded.model';
import { Principal } from 'app/core';
import { FeatureExcludedService } from './feature-excluded.service';

@Component({
    selector: '-feature-excluded',
    templateUrl: './feature-excluded.component.html'
})
export class FeatureExcludedComponent implements OnInit, OnDestroy {
    featureExcludeds: IFeatureExcluded[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private featureExcludedService: FeatureExcludedService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.featureExcludedService.query().subscribe(
            (res: HttpResponse<IFeatureExcluded[]>) => {
                this.featureExcludeds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFeatureExcludeds();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFeatureExcluded) {
        return item.id;
    }

    registerChangeInFeatureExcludeds() {
        this.eventSubscriber = this.eventManager.subscribe('featureExcludedListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
