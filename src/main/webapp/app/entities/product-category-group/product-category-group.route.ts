import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ProductCategoryGroup } from 'app/shared/model/product-category-group.model';
import { ProductCategoryGroupService } from './product-category-group.service';
import { ProductCategoryGroupComponent } from './product-category-group.component';
import { ProductCategoryGroupDetailComponent } from './product-category-group-detail.component';
import { ProductCategoryGroupUpdateComponent } from './product-category-group-update.component';
import { ProductCategoryGroupDeletePopupComponent } from './product-category-group-delete-dialog.component';
import { IProductCategoryGroup } from 'app/shared/model/product-category-group.model';

@Injectable({ providedIn: 'root' })
export class ProductCategoryGroupResolve implements Resolve<IProductCategoryGroup> {
    constructor(private service: ProductCategoryGroupService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((productCategoryGroup: HttpResponse<ProductCategoryGroup>) => productCategoryGroup.body);
        }
        return Observable.of(new ProductCategoryGroup());
    }
}

export const productCategoryGroupRoute: Routes = [
    {
        path: 'product-category-group',
        component: ProductCategoryGroupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductCategoryGroups'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-category-group/:id/view',
        component: ProductCategoryGroupDetailComponent,
        resolve: {
            productCategoryGroup: ProductCategoryGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductCategoryGroups'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-category-group/new',
        component: ProductCategoryGroupUpdateComponent,
        resolve: {
            productCategoryGroup: ProductCategoryGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductCategoryGroups'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-category-group/:id/edit',
        component: ProductCategoryGroupUpdateComponent,
        resolve: {
            productCategoryGroup: ProductCategoryGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductCategoryGroups'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productCategoryGroupPopupRoute: Routes = [
    {
        path: 'product-category-group/:id/delete',
        component: ProductCategoryGroupDeletePopupComponent,
        resolve: {
            productCategoryGroup: ProductCategoryGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductCategoryGroups'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
