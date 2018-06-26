import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProductCategoryGroup } from 'app/shared/model/product-category-group.model';

type EntityResponseType = HttpResponse<IProductCategoryGroup>;
type EntityArrayResponseType = HttpResponse<IProductCategoryGroup[]>;

@Injectable({ providedIn: 'root' })
export class ProductCategoryGroupService {
    private resourceUrl = SERVER_API_URL + 'api/product-category-groups';

    constructor(private http: HttpClient) {}

    create(productCategoryGroup: IProductCategoryGroup): Observable<EntityResponseType> {
        return this.http.post<IProductCategoryGroup>(this.resourceUrl, productCategoryGroup, { observe: 'response' });
    }

    update(productCategoryGroup: IProductCategoryGroup): Observable<EntityResponseType> {
        return this.http.put<IProductCategoryGroup>(this.resourceUrl, productCategoryGroup, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProductCategoryGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProductCategoryGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
