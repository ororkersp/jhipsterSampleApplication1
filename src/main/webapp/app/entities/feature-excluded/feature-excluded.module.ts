import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplication1SharedModule } from 'app/shared';
import {
    FeatureExcludedComponent,
    FeatureExcludedDetailComponent,
    FeatureExcludedUpdateComponent,
    FeatureExcludedDeletePopupComponent,
    FeatureExcludedDeleteDialogComponent,
    featureExcludedRoute,
    featureExcludedPopupRoute
} from './';

const ENTITY_STATES = [...featureExcludedRoute, ...featureExcludedPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplication1SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FeatureExcludedComponent,
        FeatureExcludedDetailComponent,
        FeatureExcludedUpdateComponent,
        FeatureExcludedDeleteDialogComponent,
        FeatureExcludedDeletePopupComponent
    ],
    entryComponents: [
        FeatureExcludedComponent,
        FeatureExcludedUpdateComponent,
        FeatureExcludedDeleteDialogComponent,
        FeatureExcludedDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplication1FeatureExcludedModule {}
