import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ProductType } from 'app/shared/model/product-type.model';
import { ProductTypeService } from './product-type.service';
import { ProductTypeComponent } from './product-type.component';
import { ProductTypeDetailComponent } from './product-type-detail.component';
import { ProductTypeUpdateComponent } from './product-type-update.component';
import { ProductTypeDeletePopupComponent } from './product-type-delete-dialog.component';
import { IProductType } from 'app/shared/model/product-type.model';

@Injectable({ providedIn: 'root' })
export class ProductTypeResolve implements Resolve<IProductType> {
    constructor(private service: ProductTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((productType: HttpResponse<ProductType>) => productType.body);
        }
        return Observable.of(new ProductType());
    }
}

export const productTypeRoute: Routes = [
    {
        path: 'product-type',
        component: ProductTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-type/:id/view',
        component: ProductTypeDetailComponent,
        resolve: {
            productType: ProductTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-type/new',
        component: ProductTypeUpdateComponent,
        resolve: {
            productType: ProductTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-type/:id/edit',
        component: ProductTypeUpdateComponent,
        resolve: {
            productType: ProductTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productTypePopupRoute: Routes = [
    {
        path: 'product-type/:id/delete',
        component: ProductTypeDeletePopupComponent,
        resolve: {
            productType: ProductTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
