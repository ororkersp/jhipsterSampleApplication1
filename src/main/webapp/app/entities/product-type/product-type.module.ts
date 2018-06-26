import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplication1SharedModule } from 'app/shared';
import {
    ProductTypeComponent,
    ProductTypeDetailComponent,
    ProductTypeUpdateComponent,
    ProductTypeDeletePopupComponent,
    ProductTypeDeleteDialogComponent,
    productTypeRoute,
    productTypePopupRoute
} from './';

const ENTITY_STATES = [...productTypeRoute, ...productTypePopupRoute];

@NgModule({
    imports: [JhipsterSampleApplication1SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProductTypeComponent,
        ProductTypeDetailComponent,
        ProductTypeUpdateComponent,
        ProductTypeDeleteDialogComponent,
        ProductTypeDeletePopupComponent
    ],
    entryComponents: [ProductTypeComponent, ProductTypeUpdateComponent, ProductTypeDeleteDialogComponent, ProductTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplication1ProductTypeModule {}
