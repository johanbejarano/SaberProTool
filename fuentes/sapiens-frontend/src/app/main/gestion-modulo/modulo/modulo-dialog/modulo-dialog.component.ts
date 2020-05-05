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
      'cantidadPreguntas': [this.moduloDialog.cantidadPreguntas, Validators.required],
      'prioridad': [this.moduloDialog.prioridad, Validators.required],
      'tipoModulo': [this.moduloDialog.timoId_TipoModulo ? '' + this.moduloDialog.timoId_TipoModulo : null, Validators.required],
      'estado': [this.moduloDialog.estadoRegistro, Validators.required]
    });
    console.log(this.moduloDialog.timoId_TipoModulo);
    
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
      request.cantidadPreguntas = this.form.controls.cantidadPreguntas.value;
      request.prioridad = this.form.controls.prioridad.value;
      request.timoId_TipoModulo = this.form.controls.tipoModulo.value;
      request.estadoRegistro = this.form.controls.estado.value;

      this.moduloService.guardar(request).subscribe(() => {
        this.snackBar.open(espanol.data.msg.guardadoExitoso, '×', { panelClass: 'info', verticalPosition: 'top', duration: 8000 });
        this.dialogRef.close(true);
      });
    }
  } 

  getTiposModulo(){
    this.tipoModuloService.find().subscribe((tiposModulo: TipoModulo[]) => {
      this.tiposModulo = tiposModulo;
    });
  }

}
