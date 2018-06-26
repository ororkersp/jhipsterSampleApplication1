import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { FeatureIncluded } from 'app/shared/model/feature-included.model';
import { FeatureIncludedService } from './feature-included.service';
import { FeatureIncludedComponent } from './feature-included.component';
import { FeatureIncludedDetailComponent } from './feature-included-detail.component';
import { FeatureIncludedUpdateComponent } from './feature-included-update.component';
import { FeatureIncludedDeletePopupComponent } from './feature-included-delete-dialog.component';
import { IFeatureIncluded } from 'app/shared/model/feature-included.model';

@Injectable({ providedIn: 'root' })
export class FeatureIncludedResolve implements Resolve<IFeatureIncluded> {
    constructor(private service: FeatureIncludedService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((featureIncluded: HttpResponse<FeatureIncluded>) => featureIncluded.body);
        }
        return Observable.of(new FeatureIncluded());
    }
}

export const featureIncludedRoute: Routes = [
    {
        path: 'feature-included',
        component: FeatureIncludedComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureIncludeds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feature-included/:id/view',
        component: FeatureIncludedDetailComponent,
        resolve: {
            featureIncluded: FeatureIncludedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureIncludeds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feature-included/new',
        component: FeatureIncludedUpdateComponent,
        resolve: {
            featureIncluded: FeatureIncludedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureIncludeds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feature-included/:id/edit',
        component: FeatureIncludedUpdateComponent,
        resolve: {
            featureIncluded: FeatureIncludedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureIncludeds'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const featureIncludedPopupRoute: Routes = [
    {
        path: 'feature-included/:id/delete',
        component: FeatureIncludedDeletePopupComponent,
        resolve: {
            featureIncluded: FeatureIncludedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureIncludeds'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
