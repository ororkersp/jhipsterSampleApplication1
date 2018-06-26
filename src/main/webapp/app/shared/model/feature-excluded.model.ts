import { IProduct } from 'app/shared/model//product.model';

export interface IFeatureExcluded {
    id?: number;
    name?: string;
    products?: IProduct;
}

export class FeatureExcluded implements IFeatureExcluded {
    constructor(public id?: number, public name?: string, public products?: IProduct) {}
}
