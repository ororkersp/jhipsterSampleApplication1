import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFeatureExcluded } from 'app/shared/model/feature-excluded.model';

@Component({
    selector: '-feature-excluded-detail',
    templateUrl: './feature-excluded-detail.component.html'
})
export class FeatureExcludedDetailComponent implements OnInit {
    featureExcluded: IFeatureExcluded;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ featureExcluded }) => {
            this.featureExcluded = featureExcluded;
        });
    }

    previousState() {
        window.history.back();
    }
}
