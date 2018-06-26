import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFeatureIncluded } from 'app/shared/model/feature-included.model';

type EntityResponseType = HttpResponse<IFeatureIncluded>;
type EntityArrayResponseType = HttpResponse<IFeatureIncluded[]>;

@Injectable({ providedIn: 'root' })
export class FeatureIncludedService {
    private resourceUrl = SERVER_API_URL + 'api/feature-includeds';

    constructor(private http: HttpClient) {}

    create(featureIncluded: IFeatureIncluded): Observable<EntityResponseType> {
        return this.http.post<IFeatureIncluded>(this.resourceUrl, featureIncluded, { observe: 'response' });
    }

    update(featureIncluded: IFeatureIncluded): Observable<EntityResponseType> {
        return this.http.put<IFeatureIncluded>(this.resourceUrl, featureIncluded, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFeatureIncluded>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFeatureIncluded[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
