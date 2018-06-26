import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplication1ProductTypeModule } from './product-type/product-type.module';
import { JhipsterSampleApplication1ProductCategoryGroupModule } from './product-category-group/product-category-group.module';
import { JhipsterSampleApplication1ProductCategoryModule } from './product-category/product-category.module';
import { JhipsterSampleApplication1ProductModule } from './product/product.module';
import { JhipsterSampleApplication1FeatureIncludedModule } from './feature-included/feature-included.module';
import { JhipsterSampleApplication1FeatureExcludedModule } from './feature-excluded/feature-excluded.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterSampleApplication1ProductTypeModule,
        JhipsterSampleApplication1ProductCategoryGroupModule,
        JhipsterSampleApplication1ProductCategoryModule,
        JhipsterSampleApplication1ProductModule,
        JhipsterSampleApplication1FeatureIncludedModule,
        JhipsterSampleApplication1FeatureExcludedModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplication1EntityModule {}
