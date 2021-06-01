import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { Programa } from 'app/domain/programa';
import { Usuario } from 'app/domain/usuario';
import { ProgramaService } from 'app/services/programa.service';
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

  programas: Programa[];

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
    private dialogRef: MatDialogRef<UsuarioDialogComponent>,
    private formBuilder: FormBuilder,
    private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private snackBar: MatSnackBar,
    private usuarioService: UsuarioService,
    private programaService: ProgramaService) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
    this.usuario = this.usuarioService.getUsuario();
    if (data) {
      this.usuarioDialog = data;
    } else {
      this.usuarioDialog = new Usuario();
    }
  }

  ngOnInit() {
    if (this.usuarioDialog.usuaId) {
      this.loadUsuario();
    }
    this.getProgramas();

    this.form = this.formBuilder.group({
      'nombres': [this.usuarioDialog.nombre, Validators.compose([Validators.required, Validators.maxLength(80)])],
      'apellidos': [this.usuarioDialog.apellido, Validators.compose([Validators.required, Validators.maxLength(80)])],
      'genero': [this.usuarioDialog.genero, Validators.required],
      'codigo': [this.usuarioDialog.codigo, Validators.compose([Validators.required, Validators.maxLength(80)])],
      'identificacion': [this.usuarioDialog.identificacion, Validators.compose([Validators.required, Validators.maxLength(80)])],
      'celular': [this.usuarioDialog.celular, Validators.compose([Validators.required, Validators.maxLength(80)])],
      'correo': [this.usuarioDialog.correo, Validators.compose([Validators.required, Validators.maxLength(80)])],
      'estado': [this.usuarioDialog.estadoRegistro, Validators.required],
      'programa': [this.usuarioDialog.progId_Programa, Validators.required],
      'tipoUsuario': [this.usuarioDialog.tiusId_TipoUsuario, Validators.required],
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

      request.nombre = this.form.controls.nombres.value;
      request.apellido = this.form.controls.apellidos.value;
      request.genero = this.form.controls.genero.value;
      request.codigo = this.form.controls.codigo.value;
      request.identificacion = this.form.controls.identificacion.value;
      request.celular = this.form.controls.celular.value;
      request.correo = this.form.controls.correo.value;
      request.estadoRegistro = this.form.controls.estado.value;
      request.progId_Programa = this.form.controls.programa.value;
      request.tiusId_TipoUsuario = this.form.controls.tipoUsuario.value;

      this.usuarioService.guardar(request).subscribe(() => {
        this.snackBar.open(espanol.data.msg.guardadoExitoso, '×', { panelClass: 'info', verticalPosition: 'top', duration: 8000 });
        this.dialogRef.close(true);
      });
    }
  }

  loadUsuario() {
    this.usuarioService.findById(this.usuarioDialog.usuaId).subscribe((usuario: Usuario) => {

      this.form = this.formBuilder.group({
        'nombres': [usuario.nombre, Validators.compose([Validators.required, Validators.maxLength(80)])],
        'apellidos': [usuario.apellido, Validators.compose([Validators.required, Validators.maxLength(80)])],
        'genero': [usuario.genero, Validators.required],
        'codigo': [usuario.codigo, Validators.compose([Validators.required, Validators.maxLength(80)])],
        'identificacion': [usuario.identificacion, Validators.compose([Validators.required, Validators.maxLength(80)])],
        'celular': [usuario.celular, Validators.compose([Validators.required, Validators.maxLength(80)])],
        'correo': [usuario.correo, Validators.compose([Validators.required, Validators.maxLength(80)])],
        'password': ['', Validators.maxLength(80)],
        'estado': [usuario.estadoRegistro, Validators.required],
        'programa': ['' + usuario.progId_Programa, Validators.required],
        'tipoUsuario': [usuario.tiusId_TipoUsuario, Validators.required],
      });
    });
  }

  getProgramas() {
    this.programaService.getAll().subscribe((programas: Programa[]) => {
      this.programas = programas;
    });
  }

  reenviarClave(codigo){
    if (codigo != null) {

      this.usuarioService.solicitarClave(codigo).subscribe((usuario: Usuario) => {
        if (usuario.correo) {
          this.snackBar.open('Se ha enviado un email de confirmación al correo electrónico ' + usuario.correo, 'x', { verticalPosition: 'top', duration: 10000 });
        }
      }, error => {
        this.snackBar.open(error, 'x', { verticalPosition: 'top', duration: 10000 });
      });
    }
  }

}
