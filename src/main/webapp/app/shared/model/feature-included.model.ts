import { IProduct } from 'app/shared/model//product.model';

export interface IFeatureIncluded {
    id?: number;
    name?: string;
    caveat?: string;
    highlighted?: boolean;
    products?: IProduct;
}

export class FeatureIncluded implements IFeatureIncluded {
    constructor(
        public id?: number,
        public name?: string,
        public caveat?: string,
        public highlighted?: boolean,
        public products?: IProduct
    ) {
        this.highlighted = false;
    }
}
