import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFeatureIncluded } from 'app/shared/model/feature-included.model';

@Component({
    selector: '-feature-included-detail',
    templateUrl: './feature-included-detail.component.html'
})
export class FeatureIncludedDetailComponent implements OnInit {
    featureIncluded: IFeatureIncluded;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ featureIncluded }) => {
            this.featureIncluded = featureIncluded;
        });
    }

    previousState() {
        window.history.back();
    }
}
