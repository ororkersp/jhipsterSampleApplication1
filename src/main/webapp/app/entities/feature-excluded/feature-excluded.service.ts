import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFeatureExcluded } from 'app/shared/model/feature-excluded.model';

type EntityResponseType = HttpResponse<IFeatureExcluded>;
type EntityArrayResponseType = HttpResponse<IFeatureExcluded[]>;

@Injectable({ providedIn: 'root' })
export class FeatureExcludedService {
    private resourceUrl = SERVER_API_URL + 'api/feature-excludeds';

    constructor(private http: HttpClient) {}

    create(featureExcluded: IFeatureExcluded): Observable<EntityResponseType> {
        return this.http.post<IFeatureExcluded>(this.resourceUrl, featureExcluded, { observe: 'response' });
    }

    update(featureExcluded: IFeatureExcluded): Observable<EntityResponseType> {
        return this.http.put<IFeatureExcluded>(this.resourceUrl, featureExcluded, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFeatureExcluded>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFeatureExcluded[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
