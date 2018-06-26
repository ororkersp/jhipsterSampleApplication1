import { IFeatureIncluded } from 'app/shared/model//feature-included.model';
import { IFeatureExcluded } from 'app/shared/model//feature-excluded.model';
import { IProductCategory } from 'app/shared/model//product-category.model';

export interface IProduct {
    id?: number;
    name?: string;
    excessCharge?: number;
    priceMonthly?: number;
    priceYearly?: number;
    introPriceMonthly?: number;
    introMonths?: number;
    defaultTariff?: boolean;
    featuresIncludeds?: IFeatureIncluded[];
    featuresExcludeds?: IFeatureExcluded[];
    productCategory?: IProductCategory;
}

export class Product implements IProduct {
    constructor(
        public id?: number,
        public name?: string,
        public excessCharge?: number,
        public priceMonthly?: number,
        public priceYearly?: number,
        public introPriceMonthly?: number,
        public introMonths?: number,
        public defaultTariff?: boolean,
        public featuresIncludeds?: IFeatureIncluded[],
        public featuresExcludeds?: IFeatureExcluded[],
        public productCategory?: IProductCategory
    ) {
        this.defaultTariff = false;
    }
}
