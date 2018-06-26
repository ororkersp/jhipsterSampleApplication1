import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductCategoryGroup } from 'app/shared/model/product-category-group.model';
import { ProductCategoryGroupService } from './product-category-group.service';

@Component({
    selector: '-product-category-group-delete-dialog',
    templateUrl: './product-category-group-delete-dialog.component.html'
})
export class ProductCategoryGroupDeleteDialogComponent {
    productCategoryGroup: IProductCategoryGroup;

    constructor(
        private productCategoryGroupService: ProductCategoryGroupService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.productCategoryGroupService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'productCategoryGroupListModification',
                content: 'Deleted an productCategoryGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-product-category-group-delete-popup',
    template: ''
})
export class ProductCategoryGroupDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ productCategoryGroup }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProductCategoryGroupDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.productCategoryGroup = productCategoryGroup;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
