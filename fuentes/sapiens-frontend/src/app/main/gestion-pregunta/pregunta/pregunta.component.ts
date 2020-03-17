import { Component, OnInit } from '@angular/core';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import {locale as espanol} from '../i18n/es';
import { PreguntaDialogComponent } from './pregunta-dialog/pregunta-dialog.component';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-pregunta',
  templateUrl: './pregunta.component.html',
  styleUrls: ['./pregunta.component.scss']
})
export class PreguntaComponent implements OnInit {

  actualizar: boolean = true;

  constructor(private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private dialog: MatDialog) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
  }

  ngOnInit() {
  }

  crear(){
    let dialogRef = this.dialog.open(PreguntaDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.actualizar = !this.actualizar;
      }
    });
  }

}
