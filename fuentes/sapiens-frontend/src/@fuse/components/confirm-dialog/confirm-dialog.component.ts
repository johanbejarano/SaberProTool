import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { locale as espanol } from './i18n/es';
@Component({
    selector   : 'fuse-confirm-dialog',
    templateUrl: './confirm-dialog.component.html',
    styleUrls  : ['./confirm-dialog.component.scss']
})
export class FuseConfirmDialogComponent
{
    public confirmMessage: string;
    public aceptarCancelar: boolean = true;

    /**
     * Constructor
     *
     * @param {MatDialogRef<FuseConfirmDialogComponent>} dialogRef
     */
    constructor(
        public dialogRef: MatDialogRef<FuseConfirmDialogComponent>,
        private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    ){
        this._fuseTranslationLoaderService.loadTranslations(espanol);
    }

}
