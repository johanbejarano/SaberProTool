import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { locale as espanol } from '../i18n/es';
import { ModuloDialogComponent } from './modulo-dialog/modulo-dialog.component';

@Component({
  selector: 'app-modulo',
  templateUrl: './modulo.component.html',
  styleUrls: ['./modulo.component.scss']
})
export class ModuloComponent implements OnInit {

  actualizar: boolean = true;

  constructor(private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private dialog: MatDialog) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
  }

  ngOnInit() {
  }

  crear(){
    let dialogRef = this.dialog.open(ModuloDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.actualizar = !this.actualizar;
      }
    });
  }

}
