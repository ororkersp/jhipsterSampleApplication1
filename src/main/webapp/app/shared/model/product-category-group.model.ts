import { IProductCategory } from 'app/shared/model//product-category.model';
import { IProductType } from 'app/shared/model//product-type.model';

export interface IProductCategoryGroup {
    id?: number;
    productCategories?: IProductCategory[];
    productType?: IProductType;
}

export class ProductCategoryGroup implements IProductCategoryGroup {
    constructor(public id?: number, public productCategories?: IProductCategory[], public productType?: IProductType) {}
}
