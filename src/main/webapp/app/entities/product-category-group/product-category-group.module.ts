import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplication1SharedModule } from 'app/shared';
import {
    ProductCategoryGroupComponent,
    ProductCategoryGroupDetailComponent,
    ProductCategoryGroupUpdateComponent,
    ProductCategoryGroupDeletePopupComponent,
    ProductCategoryGroupDeleteDialogComponent,
    productCategoryGroupRoute,
    productCategoryGroupPopupRoute
} from './';

const ENTITY_STATES = [...productCategoryGroupRoute, ...productCategoryGroupPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplication1SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProductCategoryGroupComponent,
        ProductCategoryGroupDetailComponent,
        ProductCategoryGroupUpdateComponent,
        ProductCategoryGroupDeleteDialogComponent,
        ProductCategoryGroupDeletePopupComponent
    ],
    entryComponents: [
        ProductCategoryGroupComponent,
        ProductCategoryGroupUpdateComponent,
        ProductCategoryGroupDeleteDialogComponent,
        ProductCategoryGroupDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplication1ProductCategoryGroupModule {}
