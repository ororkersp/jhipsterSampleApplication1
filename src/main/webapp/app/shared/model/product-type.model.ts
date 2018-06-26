import { IProductCategoryGroup } from 'app/shared/model//product-category-group.model';

export interface IProductType {
    id?: number;
    categoryGroups?: IProductCategoryGroup[];
}

export class ProductType implements IProductType {
    constructor(public id?: number, public categoryGroups?: IProductCategoryGroup[]) {}
}
