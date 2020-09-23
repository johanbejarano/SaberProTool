import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { Modulo } from 'app/domain/modulo';
import { TipoModulo } from 'app/domain/tipo-modulo';
import { Usuario } from 'app/domain/usuario';
import { ModuloService } from 'app/services/modulo.service';
import { TipoModuloService } from 'app/services/tipo-modulo.service';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { locale as espanol } from '../../i18n/es';

@Component({
  selector: 'app-modulo-dialog',
  templateUrl: './modulo-dialog.component.html',
  styleUrls: ['./modulo-dialog.component.scss']
})
export class ModuloDialogComponent implements OnInit {

  subscription: Subscription;
  form: FormGroup;
  usuario: Usuario;

  moduloDialog: Modulo;

  tiposModulo: TipoModulo[];

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
    private dialogRef: MatDialogRef<ModuloDialogComponent>,
    private formBuilder: FormBuilder,
    private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private snackBar: MatSnackBar,
    private moduloService: ModuloService,
    private usuarioService: UsuarioService,
    private tipoModuloService: TipoModuloService) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
    this.usuario = this.usuarioService.getUsuario();
    if (data) {
      this.moduloDialog = data;
    } else {
      this.moduloDialog = new Modulo();
    }
  }

  ngOnInit() {
    this.getTiposModulo();

    this.form = this.formBuilder.group({
      'nombre': [this.moduloDialog.nombre, Validators.required],
      'descripcion': [this.moduloDialog.descripcion, Validators.maxLength(500)],
      'prioridad': [this.moduloDialog.prioridad, Validators.required],
      'tipoModulo': [this.moduloDialog.timoId_TipoModulo ? '' + this.moduloDialog.timoId_TipoModulo : null, Validators.required],
      'estado': [this.moduloDialog.estadoRegistro, Validators.required],
      'valorPregunta': [this.moduloDialog.igualValor, Validators.required],
      'cantidadPreguntas1': [this.moduloDialog.cantidadPreguntas1, Validators.required],
      'cantidadPreguntas2': [this.moduloDialog.cantidadPreguntas2, Validators.required],
      'cantidadPreguntas3': [this.moduloDialog.cantidadPreguntas3, Validators.required],
      'cantidadPreguntas4': [this.moduloDialog.cantidadPreguntas4, Validators.required],
    });
  }

  ngOnDestroy() {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }

  onFormSubmit() {
    if (this.form.valid) {
      let request: Modulo = new Modulo();
      request.moduId = this.moduloDialog.moduId;
      request.usuCreador = this.usuario.usuaId;

      request.nombre = this.form.controls.nombre.value;
      request.descripcion = this.form.controls.descripcion.value;
      request.prioridad = this.form.controls.prioridad.value;
      request.timoId_TipoModulo = this.form.controls.tipoModulo.value;
      request.estadoRegistro = this.form.controls.estado.value;
      request.igualValor = this.form.controls.valorPregunta.value;
      request.cantidadPreguntas1 = this.form.controls.cantidadPreguntas1.value;
      request.cantidadPreguntas2 = this.form.controls.cantidadPreguntas2.value;
      request.cantidadPreguntas3 = this.form.controls.cantidadPreguntas3.value;
      request.cantidadPreguntas4 = this.form.controls.cantidadPreguntas4.value;

      this.moduloService.guardar(request).subscribe(() => {
        this.snackBar.open(espanol.data.msg.guardadoExitoso, '×', { panelClass: 'info', verticalPosition: 'top', duration: 8000 });
        this.dialogRef.close(true);
      });
    }
  }

  getTiposModulo() {
    this.tipoModuloService.find().subscribe((tiposModulo: TipoModulo[]) => {
      this.tiposModulo = tiposModulo;
    });
  }

}
