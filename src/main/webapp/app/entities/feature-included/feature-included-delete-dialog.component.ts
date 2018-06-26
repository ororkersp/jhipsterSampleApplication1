import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFeatureIncluded } from 'app/shared/model/feature-included.model';
import { FeatureIncludedService } from './feature-included.service';

@Component({
    selector: '-feature-included-delete-dialog',
    templateUrl: './feature-included-delete-dialog.component.html'
})
export class FeatureIncludedDeleteDialogComponent {
    featureIncluded: IFeatureIncluded;

    constructor(
        private featureIncludedService: FeatureIncludedService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.featureIncludedService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'featureIncludedListModification',
                content: 'Deleted an featureIncluded'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-feature-included-delete-popup',
    template: ''
})
export class FeatureIncludedDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ featureIncluded }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FeatureIncludedDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.featureIncluded = featureIncluded;
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
