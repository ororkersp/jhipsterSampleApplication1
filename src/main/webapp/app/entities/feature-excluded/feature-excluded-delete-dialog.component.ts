import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFeatureExcluded } from 'app/shared/model/feature-excluded.model';
import { FeatureExcludedService } from './feature-excluded.service';

@Component({
    selector: '-feature-excluded-delete-dialog',
    templateUrl: './feature-excluded-delete-dialog.component.html'
})
export class FeatureExcludedDeleteDialogComponent {
    featureExcluded: IFeatureExcluded;

    constructor(
        private featureExcludedService: FeatureExcludedService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.featureExcludedService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'featureExcludedListModification',
                content: 'Deleted an featureExcluded'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: '-feature-excluded-delete-popup',
    template: ''
})
export class FeatureExcludedDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ featureExcluded }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FeatureExcludedDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.featureExcluded = featureExcluded;
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
