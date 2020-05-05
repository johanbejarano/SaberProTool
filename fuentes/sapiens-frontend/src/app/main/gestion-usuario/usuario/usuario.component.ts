import { Component, OnInit } from '@angular/core';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { MatDialog } from '@angular/material';
import {locale as espanol} from '../i18n/es';
import { UsuarioDialogComponent } from './usuario-dialog/usuario-dialog.component';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent implements OnInit {

  actualizar: boolean = true;

  constructor(private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private dialog: MatDialog) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
  }

  ngOnInit() {
  }

  crear(){
    let dialogRef = this.dialog.open(UsuarioDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.actualizar = !this.actualizar;
      }
    });
  }

}
