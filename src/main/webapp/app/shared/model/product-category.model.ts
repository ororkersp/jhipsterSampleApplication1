import { IProduct } from 'app/shared/model//product.model';
import { IProductCategoryGroup } from 'app/shared/model//product-category-group.model';

export interface IProductCategory {
    id?: number;
    name?: string;
    termsAndConditionsUrl?: string;
    defaultProductId?: string;
    products?: IProduct[];
    categoryGroup?: IProductCategoryGroup;
}

export class ProductCategory implements IProductCategory {
    constructor(
        public id?: number,
        public name?: string,
        public termsAndConditionsUrl?: string,
        public defaultProductId?: string,
        public products?: IProduct[],
        public categoryGroup?: IProductCategoryGroup
    ) {}
}
