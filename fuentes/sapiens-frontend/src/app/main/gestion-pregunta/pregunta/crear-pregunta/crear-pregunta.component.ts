import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { Contexto } from 'app/domain/contexto';
import { Modulo } from 'app/domain/modulo';
import { Pregunta } from 'app/domain/pregunta';
import { Respuesta } from 'app/domain/respuesta';
import { TipoModulo } from 'app/domain/tipo-modulo';
import { Usuario } from 'app/domain/usuario';
import { LocalStorageService } from 'app/services/local-storage.service';
import { ModuloService } from 'app/services/modulo.service';
import { PreguntaService } from 'app/services/pregunta.service';
import { TipoModuloService } from 'app/services/tipo-modulo.service';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { environment } from '../../../../../../src/environments/environment';
import * as ClassicEditor from '../../../../../assets/ckeditor.js';
import { ContextoComponent } from '../../contexto/contexto.component';
import { VisualizarPreguntaComponent } from '../../visualizar-pregunta/visualizar-pregunta.component';
@Component({
  selector: 'app-crear-pregunta',
  templateUrl: './crear-pregunta.component.html',
  styleUrls: ['./crear-pregunta.component.scss']
})
export class CrearPreguntaComponent implements OnInit, OnDestroy {

  usuario: Usuario;

  form: FormGroup;

  // Horizontal Stepper
  stepsRespuestasList: FormGroup[];
  horizontalStepperStep1: FormGroup;
  horizontalStepperStep6: FormGroup;
  subscription: Subscription;

  tiposModulo: TipoModulo[] = [];
  tipoModulo: TipoModulo = new TipoModulo();

  modulos: Modulo[] = [];
  modulo: Modulo = new Modulo();

  pregunta: Pregunta;
  idxRespuestaCorrecta: number[];

  tiposPregunta = [{ key: 1, label: 'Cerrada' }, { key: 2, label: 'Abierta' }];
  complejidades = [1, 2, 3, 4];

  //tomado de https://stackoverflow.com/questions/46765197/how-to-enable-image-upload-support-in-ckeditor-5
  public editorConfig = {
    simpleUpload: {
      uploadUrl: environment.URL_CKEDITOR_UPLOAD,
    }
  };

  public Editor = ClassicEditor;

  constructor(
    private _formBuilder: FormBuilder,
    private tipomoduloService: TipoModuloService,
    private moduloService: ModuloService,
    private preguntaService: PreguntaService,
    usuarioService: UsuarioService,
    private router: Router,
    private snackBar: MatSnackBar,
    private localStorage: LocalStorageService,
    private dialog: MatDialog
  ) {
    this.usuario = usuarioService.getUsuario();
  }

  ngOnInit(): void {

    this.pregunta = new Pregunta();
    let idPregunta = this.localStorage.getFromLocal('idPregunta');

    if (idPregunta) {

      //Se consulta la pregunta
      this.preguntaService.getPregunta(idPregunta)
        .subscribe((pregunta: Pregunta) => {
          this.pregunta = pregunta;
          this.idxRespuestaCorrecta = [];
          console.log(this.pregunta);
          console.log(this.pregunta.respuestasDTO);
          console.log(this.pregunta.respuestasDTO.length);

          //Se calcula el idx de la respuesta correcta
          for (let i = 0; i < this.pregunta.respuestasDTO.length; i++) {

            console.log(this.pregunta.respuestasDTO[i].correcta);
            if (this.pregunta.respuestasDTO[i].correcta == 1) {

              this.idxRespuestaCorrecta.push(i) ;
              // break;
            }
          }
          console.log(this.idxRespuestaCorrecta);
          

          this.localStorage.putInLocal('pregunta', this.pregunta);

          this.actualizarFormulario();
          this.getModulos(false);
        });

    } else {
      this.pregunta.usuCreador = this.usuario.usuaId;
      //se va a crear una pregunta

      this.actualizarFormulario();
    }

    this.actualizarFormulario();

  }

  actualizarFormulario() {

    //Se consulta el programa al que pertenece el usuario

    // Reactive Form
    this.form = this._formBuilder.group({

      facultad: [{ value: this.usuario.nombreFacultad, disabled: true }],
      programa: [{ value: this.usuario.nombrePrograma, disabled: true }],
      'tipoModulo': [this.pregunta.timoId, Validators.required],
      modulo: [this.pregunta.moduId_Modulo, Validators.required],
      complejidad: [this.pregunta.complejidad, Validators.required],
      tipoPregunta: [this.pregunta.tprgId_TipoPregunta, Validators.required],
      cantidadRespuestas: [this.pregunta.tprgId_TipoPregunta ? this.pregunta.tprgId_TipoPregunta === 1 ? this.pregunta.respuestasDTO.length : 0 : 4, Validators.required],
      estado: [this.pregunta.estadoRegistro, Validators.required],
      valorPregunta: [this.pregunta.valorPregunta, Validators.compose([Validators.required, Validators.min(0), Validators.min(100)])],
      contexto: [this.pregunta.contId],
      contextoText: [this.pregunta.nombreContexto],
      descripcionContexto: [this.pregunta.contexto],
      orden:  [this.pregunta.orden]
    });

    if (this.form.controls.tipoPregunta.value == 1) {
      this.form.controls.cantidadRespuestas.setValidators(Validators.min(2));
      this.form.controls.cantidadRespuestas.setValidators(Validators.max(10));
    }

    // Horizontal Stepper form steps
    this.horizontalStepperStep1 = this._formBuilder.group({
      editorPregunta: [this.pregunta.descripcion, Validators.required]
    });

    this.stepsRespuestasList = [];
    if (this.pregunta.tprgId_TipoPregunta == 1) {
      for (let i = 0; i < this.pregunta.respuestasDTO.length; i++) {
        const respuesta = this.pregunta.respuestasDTO[i];
        let form = this._formBuilder.group({
          editorRespuesta: [respuesta.descripcion, Validators.required]
        });
        this.stepsRespuestasList.push(form);
      }
    }

    this.horizontalStepperStep6 = this._formBuilder.group({
      respuestaCorrecta: [this.idxRespuestaCorrecta !== undefined ? this.idxRespuestaCorrecta : []],
      editorRetroalimentacion: [this.pregunta.retroalimentacion, Validators.required]
    });

    console.log(this.horizontalStepperStep6);

    //Se consultan los tipos de modulo
    this.getTiposModulo();

    if (this.pregunta.usuCreador && this.pregunta.usuCreador !== this.usuario.usuaId) {
      this.form.disable();
      for (let i = 0; i < this.stepsRespuestasList.length; i++) {
        const formRespuesta = this.stepsRespuestasList[i];
        formRespuesta.disable();
      }

      this.horizontalStepperStep1.disable();
      this.horizontalStepperStep6.disable();

    }

  }

  ngOnDestroy(): void {
    if (this.subscription !== null && this.subscription !== undefined) {
      this.subscription.unsubscribe();
    }

    this.localStorage.removeFromLocal('idPregunta');

  }

  getTiposModulo() {
    this.subscription = this.tipomoduloService.find()
      .subscribe((tiposmodulo: TipoModulo[]) => {
        this.tiposModulo = tiposmodulo;
      });
  }

  getModulos(clear: boolean) {
    if (this.form.controls.tipoModulo.value) {
      const tipoModulo: TipoModulo = this.tiposModulo.filter(tipoModulo => { return tipoModulo.timoId == this.form.controls.tipoModulo.value })[0];

      if (clear) {
        this.form.controls.modulo.setValue(null);
        //this.changeModulo();
      }

      this.subscription = this.moduloService.findByTipoModulo(this.form.controls.tipoModulo.value)
        .subscribe((modulos: Modulo[]) => {
          this.modulos = modulos;
          this.changeModulo();
        });


    }
  }

  guardarPregunta(): void {

    if (!this.form.valid) {
      this.snackBar.open('Faltan datos asociados a la clasificación de la pregunta', 'x', { verticalPosition: 'top', duration: 10000 });
      return;
    }

    if (!this.horizontalStepperStep1.valid) {
      this.snackBar.open('Faltan datos asociados al encabezado de la pregunta', 'x', { verticalPosition: 'top', duration: 10000 });
      return;
    }

    //Si es cerrada, valido que todos los forms esten llenos
    if (this.form.controls.tipoPregunta.value == 1) {
      for (let i = 0; i < this.stepsRespuestasList.length; i++) {
        const formRespuesta = this.stepsRespuestasList[i];
        if (!formRespuesta.valid) {
          this.snackBar.open('Faltan datos asociados a la respuesta ' + (i + 1), 'x', { verticalPosition: 'top', duration: 10000 });
          return;
        }
      }
    }

    if (!this.horizontalStepperStep6.valid) {
      this.snackBar.open('Faltan datos asociados a la retroalimentación', 'x', { verticalPosition: 'top', duration: 10000 });
      return;
    }

    if (this.pregunta.tienePruebas) {
      let confirmDialogRef = this.dialog.open(FuseConfirmDialogComponent, {
        disableClose: false
      });

      confirmDialogRef.componentInstance.confirmMessage = 'Esta pregunta ya se encuentra con algunas ejecuciones (Respuestas) en simulaciones o talleres ¿Está seguro de modificar la pregunta?';

      confirmDialogRef.afterClosed().subscribe(result => {
        if (result) {
          this.guardar(true);
        }
      });
    } else {
      this.guardar(false);
    }
  }

  guardar(omitePruebas: boolean) {
    this.pregunta.respuestasDTO = Array<Respuesta>();
    let respuestaCorrecta = this.horizontalStepperStep6.controls.respuestaCorrecta.value;

    //Se guarda la pregunta
    this.pregunta.moduId_Modulo = this.form.controls.modulo.value;
    this.pregunta.tprgId_TipoPregunta = this.form.controls.tipoPregunta.value;
    this.pregunta.complejidad = this.form.controls.complejidad.value;
    this.pregunta.orden = this.form.controls.orden.value;
    this.pregunta.contId = this.form.controls.contexto.value;
    this.pregunta.descripcion = this.horizontalStepperStep1.controls.editorPregunta.value;
    this.pregunta.retroalimentacion = this.horizontalStepperStep6.controls.editorRetroalimentacion.value;
    this.pregunta.usuCreador = this.usuario.usuaId;
    this.pregunta.usuModificador = this.usuario.usuaId;
    this.pregunta.estadoRegistro = this.form.controls.estado.value;
    this.pregunta.tienePruebas = omitePruebas;
    
    console.log(this.stepsRespuestasList);
    console.log(respuestaCorrecta);

    if(respuestaCorrecta.length > 1){
      this.pregunta.seleccionMultiple = true;
    }else{
      this.pregunta.seleccionMultiple = false;
    }
    
    for (let i = 0; i < this.stepsRespuestasList.length; i++) {
      const respuestaForm = this.stepsRespuestasList[i];
      let respuesta = new Respuesta();

      for (let index = 0; index < respuestaCorrecta.length; index++) {

        if(i == respuestaCorrecta[index]){
          respuesta.correcta = 1;
          break;
        }else{
          respuesta.correcta = 0;
        }
      }
      // respuesta.correcta = respuestaCorrecta == i ? 1 : 0;
      respuesta.descripcion = respuestaForm.controls.editorRespuesta.value;
      this.pregunta.respuestasDTO.push(respuesta);
    }

    if (this.modulo.igualValor == 'N') {
      this.pregunta.valorPregunta = this.form.controls.valorPregunta.value;
      if (!this.pregunta.valorPregunta) {
        this.snackBar.open('Se debe de ingresar el peso de la pregunta', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      }
    }

    let idPregunta = this.localStorage.getFromLocal('idPregunta');

    console.log(this.pregunta);
    
    if (!idPregunta) {
      this.subscription = this.preguntaService.guardarPregunta(this.pregunta)
        .subscribe((pregunta: Pregunta) => {
          console.log(pregunta);
          
          this.snackBar.open('Se ha almacenado correctamente la pregunta ' + pregunta.pregId, 'x', { verticalPosition: 'top', duration: 10000 });
          this.router.navigate(["/gestionPreguntas"]);
        },
          error => {
            this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
          });
    } else {

      this.subscription = this.preguntaService.actualizarPregunta(this.pregunta)
        .subscribe((pregunta: Pregunta) => {
          console.log(pregunta);
          
          this.snackBar.open('Se ha actualizado correctamente la pregunta ' + pregunta.pregId, 'x', { verticalPosition: 'top', duration: 10000 });
          this.router.navigate(["/gestionPreguntas"]);
        },
          error => {
            this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
          });
    }
  }

  changeRespuestas() {
    if (this.form.controls.cantidadRespuestas.valid) {

      const cantidadRespuestas = this.form.controls.cantidadRespuestas.value;
      if (cantidadRespuestas) {
        let respuestasForm: FormGroup[] = [];
        for (let i = 0; i < cantidadRespuestas; i++) {
          if (i >= this.stepsRespuestasList.length) {

            let form = this._formBuilder.group({
              editorRespuesta: ['', Validators.required]
            });

            respuestasForm.push(form);
          } else {
            const formRespuesta = this.stepsRespuestasList[i];
            respuestasForm.push(formRespuesta);
          }
        }
        this.stepsRespuestasList = respuestasForm;
      } else {
        this.stepsRespuestasList = [];
      }
    }
  }

  changeTipoPregunta() {
    if (this.form.controls.tipoPregunta.value !== 1) {
      this.form.controls.cantidadRespuestas.clearValidators();
      this.form.controls.cantidadRespuestas.setValue(0);
    } else {
      this.form.controls.cantidadRespuestas.setValidators([Validators.required, Validators.min(2), Validators.max(10)]);

      if (this.form.controls.cantidadRespuestas.value === 0) {
        this.form.controls.cantidadRespuestas.setValue(4);
      }
    }
    this.changeRespuestas();
  }

  changeModulo() {
    if (this.form.controls.modulo.value) {
      let modulo = this.modulos.find(modulo => modulo.moduId == this.form.controls.modulo.value);
      this.modulo = modulo;
      if (modulo.igualValor !== 'N') {
        this.form.setControl('valorPregunta', new FormControl(''));
      } else {
        this.form.controls.valorPregunta.setValidators(Validators.required);
      }
    } else {
      this.modulo = null;
    }
  }

  visualizar() {
    let pregunta = new Pregunta();
    pregunta.respuestasDTO = [];
    pregunta.descripcion = this.horizontalStepperStep1.controls.editorPregunta.value;
    pregunta.retroalimentacion = this.horizontalStepperStep6.controls.editorRetroalimentacion.value;
    pregunta.nombreModulo = this.modulo.nombre;
    pregunta.contexto = this.form.controls.descripcionContexto.value;
    for (let i = 0; i < this.stepsRespuestasList.length; i++) {
      const respuestaForm = this.stepsRespuestasList[i];
      let respuesta = new Respuesta();
      respuesta.descripcion = respuestaForm.controls.editorRespuesta.value;
      pregunta.respuestasDTO.push(respuesta);
    }

    this.dialog.open(VisualizarPreguntaComponent, {
      data: pregunta
    });
  }

  duplicar() {
    this.pregunta.respuestasDTO = Array<Respuesta>();

    let respuestaCorrecta = (+this.horizontalStepperStep6.controls.respuestaCorrecta.value);

    //Se guarda la pregunta
    this.pregunta.moduId_Modulo = this.form.controls.modulo.value;
    this.pregunta.tprgId_TipoPregunta = this.form.controls.tipoPregunta.value;
    this.pregunta.complejidad = this.form.controls.complejidad.value;
    this.pregunta.contId = this.form.controls.contexto.value;
    this.pregunta.descripcion = this.horizontalStepperStep1.controls.editorPregunta.value;
    this.pregunta.retroalimentacion = this.horizontalStepperStep6.controls.editorRetroalimentacion.value;
    this.pregunta.usuCreador = this.usuario.usuaId;
    this.pregunta.usuModificador = this.usuario.usuaId;
    this.pregunta.estadoRegistro = 'I';

    for (let i = 0; i < this.stepsRespuestasList.length; i++) {
      const respuestaForm = this.stepsRespuestasList[i];
      let respuesta = new Respuesta();
      respuesta.correcta = respuestaCorrecta == i ? 1 : 0;
      respuesta.descripcion = respuestaForm.controls.editorRespuesta.value;
      this.pregunta.respuestasDTO.push(respuesta);
    }

    if (this.modulo.igualValor == 'N') {
      this.pregunta.valorPregunta = this.form.controls.valorPregunta.value;
      if (!this.pregunta.valorPregunta) {
        this.snackBar.open('Se debe de ingresar el peso de la pregunta', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      }
    }

    this.subscription = this.preguntaService.guardarPregunta(this.pregunta)
      .subscribe((pregunta: Pregunta) => {
        this.snackBar.open('Se ha duplicado correctamente la pregunta ' + pregunta.pregId, 'x', { verticalPosition: 'top', duration: 10000 });
        this.router.navigate(["/gestionPreguntas"]);
      },
        error => {
          this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
        });
  }

  seleccionarContexto() {
    let contexto = new Contexto();
    contexto.contId = this.form.controls.contexto.value;
    let dialogRef = this.dialog.open(ContextoComponent, {
      data: {
        modulo: this.form.controls.modulo.value,
        contexto: contexto
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.form.controls.contexto.setValue(result.contId);
        this.form.controls.contextoText.setValue(result.nombre);
        this.form.controls.descripcionContexto.setValue(result.descripcion);
      }
    });
  }
}
