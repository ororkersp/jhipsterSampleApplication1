import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplication1SharedModule } from 'app/shared';
import {
    FeatureIncludedComponent,
    FeatureIncludedDetailComponent,
    FeatureIncludedUpdateComponent,
    FeatureIncludedDeletePopupComponent,
    FeatureIncludedDeleteDialogComponent,
    featureIncludedRoute,
    featureIncludedPopupRoute
} from './';

const ENTITY_STATES = [...featureIncludedRoute, ...featureIncludedPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplication1SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FeatureIncludedComponent,
        FeatureIncludedDetailComponent,
        FeatureIncludedUpdateComponent,
        FeatureIncludedDeleteDialogComponent,
        FeatureIncludedDeletePopupComponent
    ],
    entryComponents: [
        FeatureIncludedComponent,
        FeatureIncludedUpdateComponent,
        FeatureIncludedDeleteDialogComponent,
        FeatureIncludedDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplication1FeatureIncludedModule {}
