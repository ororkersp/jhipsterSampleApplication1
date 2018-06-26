import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { FeatureExcluded } from 'app/shared/model/feature-excluded.model';
import { FeatureExcludedService } from './feature-excluded.service';
import { FeatureExcludedComponent } from './feature-excluded.component';
import { FeatureExcludedDetailComponent } from './feature-excluded-detail.component';
import { FeatureExcludedUpdateComponent } from './feature-excluded-update.component';
import { FeatureExcludedDeletePopupComponent } from './feature-excluded-delete-dialog.component';
import { IFeatureExcluded } from 'app/shared/model/feature-excluded.model';

@Injectable({ providedIn: 'root' })
export class FeatureExcludedResolve implements Resolve<IFeatureExcluded> {
    constructor(private service: FeatureExcludedService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((featureExcluded: HttpResponse<FeatureExcluded>) => featureExcluded.body);
        }
        return Observable.of(new FeatureExcluded());
    }
}

export const featureExcludedRoute: Routes = [
    {
        path: 'feature-excluded',
        component: FeatureExcludedComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureExcludeds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feature-excluded/:id/view',
        component: FeatureExcludedDetailComponent,
        resolve: {
            featureExcluded: FeatureExcludedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureExcludeds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feature-excluded/new',
        component: FeatureExcludedUpdateComponent,
        resolve: {
            featureExcluded: FeatureExcludedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureExcludeds'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'feature-excluded/:id/edit',
        component: FeatureExcludedUpdateComponent,
        resolve: {
            featureExcluded: FeatureExcludedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureExcludeds'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const featureExcludedPopupRoute: Routes = [
    {
        path: 'feature-excluded/:id/delete',
        component: FeatureExcludedDeletePopupComponent,
        resolve: {
            featureExcluded: FeatureExcludedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'FeatureExcludeds'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
