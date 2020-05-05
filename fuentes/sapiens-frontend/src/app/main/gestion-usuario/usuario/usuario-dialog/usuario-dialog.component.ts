import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { Usuario } from 'app/domain/usuario';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { locale as espanol } from '../../i18n/es';

@Component({
  selector: 'app-usuario-dialog',
  templateUrl: './usuario-dialog.component.html',
  styleUrls: ['./usuario-dialog.component.scss']
})
export class UsuarioDialogComponent implements OnInit {

  subscription: Subscription;
  form: FormGroup;
  usuario: Usuario;

  usuarioDialog: Usuario;

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
    private dialogRef: MatDialogRef<UsuarioDialogComponent>,
    private formBuilder: FormBuilder,
    private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private snackBar: MatSnackBar,
    private usuarioService: UsuarioService) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
    this.usuario = this.usuarioService.getUsuario();
    if (data) {
      this.usuarioDialog = data;
    } else {
      this.usuarioDialog = new Usuario();
    }
  }

  ngOnInit() {
    //const perfil: string = this.usuarioDialog.rolId ? this.usuarioDialog.rolId + '' : null;
    this.form = this.formBuilder.group({
      //'usuario': [this.usuarioDialog.usuario, Validators.compose([Validators.required, Validators.maxLength(80)])],
      //'perfil': [perfil, Validators.compose([Validators.required])],
    });
  }

  ngOnDestroy() {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }

  onFormSubmit() {
    if (this.form.valid) {
      let request: Usuario = new Usuario();
      request.usuaId = this.usuarioDialog.usuaId;
      request.usuCreador = this.usuario.usuaId;

      this.usuarioService.guardar(request).subscribe(() => {
        this.snackBar.open(espanol.data.msg.guardadoExitoso, '×', { panelClass: 'info', verticalPosition: 'top', duration: 8000 });
        this.dialogRef.close(true);
      });
    }
  }

}
