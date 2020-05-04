import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Modulo } from 'app/domain/modulo';
import { Pregunta } from 'app/domain/pregunta';
import { Respuesta } from 'app/domain/respuesta';
import { TipoModulo } from 'app/domain/tipo-modulo';
import { LocalStorageService } from 'app/services/local-storage.service';
import { ModuloService } from 'app/services/modulo.service';
import { PreguntaService } from 'app/services/pregunta.service';
import { RespuestaService } from 'app/services/respuesta.service';
import { TipoModuloService } from 'app/services/tipo-modulo.service';
import { Subscription } from 'rxjs';
import { environment } from '../../../../../../src/environments/environment.js';
import * as ClassicEditor from '../../../../../assets/ckeditor.js';


@Component({
  selector: 'app-crear-pregunta',
  templateUrl: './crear-pregunta.component.html',
  styleUrls: ['./crear-pregunta.component.scss']
})
export class CrearPreguntaComponent implements OnInit, OnDestroy {

  form: FormGroup;

  // Horizontal Stepper
  horizontalStepperStep1: FormGroup;
  horizontalStepperStep2: FormGroup;
  horizontalStepperStep3: FormGroup;
  horizontalStepperStep4: FormGroup;
  horizontalStepperStep5: FormGroup;
  horizontalStepperStep6: FormGroup;

  subscription: Subscription;

  tiposModulo: TipoModulo[] = [];
  tipoModulo: TipoModulo = new TipoModulo();
  
  modulos: Modulo[] = [];
  modulo: Modulo = new Modulo();

  pregunta: Pregunta;
  idxRespuestaCorrecta: number;
  
  //tomado de https://stackoverflow.com/questions/46765197/how-to-enable-image-upload-support-in-ckeditor-5
  public editorPreguntaConfig = {
    placeholder: 'Redacte la pregunta aquí!',
    
    simpleUpload: {
      // The URL that the images are uploaded to.
      uploadUrl: environment.URL_CKEDITOR_UPLOAD,
    }
  };

  public editorRetroalimentacionConfig = {
    placeholder: 'Redacte la retroalimentación aquí!',
    
    simpleUpload: {
      // The URL that the images are uploaded to.
      uploadUrl: environment.URL_CKEDITOR_UPLOAD,
    }
  };

  public EditorPregunta = ClassicEditor;
  public EditorRespuesta1 = ClassicEditor;
  public EditorRespuesta2 = ClassicEditor;
  public EditorRespuesta3 = ClassicEditor;
  public EditorRespuesta4 = ClassicEditor;
  public EditorRetroalimentacion = ClassicEditor;

  constructor(
    private _formBuilder: FormBuilder,
    private tipomoduloService: TipoModuloService,
    private moduloService: ModuloService,
    private preguntaService: PreguntaService,
    private respuestaService: RespuestaService,
    private matDialog: MatDialog,
    private router: Router,
    private snackBar: MatSnackBar,
    private localStorage: LocalStorageService,
  ) {


  }

  ngOnInit(): void {

    this.pregunta = new Pregunta();
    let idPregunta = this.localStorage.getFromLocal('idPregunta');

    if (idPregunta){

      //Se consulta la pregunta
      this.preguntaService.getPregunta(idPregunta)
        .subscribe((pregunta: Pregunta) => {
          this.pregunta = pregunta;

          //Se calcula el idx de la respuesta correcta
          for (let i=0; i<this.pregunta.respuestasDTO.length; i++){
            
            if (this.pregunta.respuestasDTO[i].correcta == 1){
              this.idxRespuestaCorrecta = i;
              
              break;
            }
          }
          
          this.localStorage.putInLocal('pregunta', this.pregunta);

          this.actualizarFormulario();
          this.getModulos(false);
        });

    }else{
      //se va a crear una pregunta
      
      this.actualizarFormulario();
    }

    this.actualizarFormulario();

  }

  actualizarFormulario(){

    // Reactive Form
    this.form = this._formBuilder.group({
      
      facultad: [{ value: 'Facultad de Ingeniería', disabled: true }],
      programa: [{ value: 'Programa de Ingeniería de sistemas', disabled: true }],
      'tipoModulo': [this.pregunta.timoId, Validators.required],
      modulo: [this.pregunta.moduId_Modulo, Validators.required],

    });

    // Horizontal Stepper form steps
    this.horizontalStepperStep1 = this._formBuilder.group({
        editorPregunta: [this.pregunta.descripcion, Validators.required]
    });

    this.horizontalStepperStep2 = this._formBuilder.group({
        editorRespuesta1: [this.pregunta.respuestasDTO && this.pregunta.respuestasDTO.length>0 ? this.pregunta.respuestasDTO[0].descripcion : '', Validators.required]
    });

    this.horizontalStepperStep3 = this._formBuilder.group({
      editorRespuesta2: [this.pregunta.respuestasDTO && this.pregunta.respuestasDTO.length>1 ? this.pregunta.respuestasDTO[1].descripcion : '', Validators.required]
    });

    this.horizontalStepperStep4 = this._formBuilder.group({
      editorRespuesta3: [this.pregunta.respuestasDTO && this.pregunta.respuestasDTO.length>2 ? this.pregunta.respuestasDTO[2].descripcion : '', Validators.required]
    });

    this.horizontalStepperStep5 = this._formBuilder.group({
      editorRespuesta4: [this.pregunta.respuestasDTO && this.pregunta.respuestasDTO.length>3 ? this.pregunta.respuestasDTO[3].descripcion : '', Validators.required]
    });
    
    this.horizontalStepperStep6 = this._formBuilder.group({
      respuestaCorrecta: [this.idxRespuestaCorrecta+'', Validators.required],
      editorRetroalimentacion: [this.pregunta.retroalimentacion, Validators.required]
    });

    //Se consultan los tipos de modulo
    this.getTiposModulo();

  }

  ngOnDestroy(): void {
    if (this.subscription !== null && this.subscription !== undefined){
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

  getModulos(clear: boolean){
    if (this.form.controls.tipoModulo.value) {
      const tipoModulo: TipoModulo = this.tiposModulo.filter(tipoModulo => { return tipoModulo.timoId == this.form.controls.tipoModulo.value })[0];
      
      if (clear) {
        this.form.controls.modulo.setValue(null);
        //this.changeModulo();
      }

      this.subscription = this.moduloService.findByTipoModulo(this.form.controls.tipoModulo.value)
        .subscribe((modulos: Modulo[]) => {
          this.modulos = modulos;
        });

      
    }
  }

  guardarPregunta(): void{

    if (!this.form.valid){
      this.snackBar.open('Faltan datos asociados a la clasificación de la pregunta', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }

    if (!this.horizontalStepperStep1.valid){
      this.snackBar.open('Faltan datos asociados al encabezado de la pregunta', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }

    if (!this.horizontalStepperStep2.valid){
      this.snackBar.open('Faltan datos asociados a la respuesta 1', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }

    if (!this.horizontalStepperStep3.valid){
      this.snackBar.open('Faltan datos asociados a la respuesta 2', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }

    if (!this.horizontalStepperStep4.valid){
      this.snackBar.open('Faltan datos asociados a la respuesta 3', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }

    if (!this.horizontalStepperStep5.valid){
      this.snackBar.open('Faltan datos asociados a la respuesta 4', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }

    if (!this.horizontalStepperStep6.valid){
      this.snackBar.open('Faltan datos asociados a la retroalimentación', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }
    
    this.pregunta.respuestasDTO = Array<Respuesta>();
    
    let respuestaCorrecta = (+this.horizontalStepperStep6.controls.respuestaCorrecta.value);

    //Se guarda la pregunta
    this.pregunta.moduId_Modulo = this.form.controls.modulo.value;
    this.pregunta.tprgId_TipoPregunta = 1;
    this.pregunta.descripcion = this.horizontalStepperStep1.controls.editorPregunta.value;
    this.pregunta.retroalimentacion = this.horizontalStepperStep6.controls.editorRetroalimentacion.value;
    this.pregunta.usuCreador = 1;
    this.pregunta.usuModificador = 1;

    let respuesta1 = new Respuesta();
    respuesta1.correcta = respuestaCorrecta==0?1:0;
    respuesta1.descripcion = this.horizontalStepperStep2.controls.editorRespuesta1.value;
    respuesta1.estadoRegistro = 'A';
    respuesta1.fechaCreacion = new Date();
    respuesta1.usuCreador = 1;
    respuesta1.usuModificador = 1;

    let respuesta2 = new Respuesta();
    respuesta2.correcta = respuestaCorrecta==1?1:0;
    respuesta2.descripcion = this.horizontalStepperStep3.controls.editorRespuesta2.value;;
    respuesta2.estadoRegistro = 'A';
    respuesta2.fechaCreacion = new Date();
    respuesta2.usuCreador = 1;
    respuesta2.usuModificador = 1;

    let respuesta3 = new Respuesta();
    respuesta3.correcta = respuestaCorrecta==2?1:0;
    respuesta3.descripcion = this.horizontalStepperStep4.controls.editorRespuesta3.value;;
    respuesta3.estadoRegistro = 'A';
    respuesta3.fechaCreacion = new Date();
    respuesta3.usuCreador = 1;
    respuesta3.usuModificador = 1;

    let respuesta4 = new Respuesta();
    respuesta4.correcta = respuestaCorrecta==3?1:0;
    respuesta4.descripcion = this.horizontalStepperStep5.controls.editorRespuesta4.value;
    respuesta4.estadoRegistro = 'A';
    respuesta4.fechaCreacion = new Date();
    respuesta4.usuCreador = 1;
    respuesta4.usuModificador = 1;
    
    this.pregunta.respuestasDTO.push(respuesta1);
    this.pregunta.respuestasDTO.push(respuesta2);
    this.pregunta.respuestasDTO.push(respuesta3);
    this.pregunta.respuestasDTO.push(respuesta4);

    let idPregunta = this.localStorage.getFromLocal('idPregunta');

    if (!idPregunta){
      this.subscription = this.preguntaService.guardarPregunta(this.pregunta)
        .subscribe((pregunta: Pregunta) => {
          this.snackBar.open('Se ha almacenado correctamente la pregunta ' + pregunta.pregId, 'x', {verticalPosition: 'top', duration: 10000});
          this.router.navigate(["/gestionPreguntas"]);
        },
        error => {
          this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
        });
    }else{
      
      this.subscription = this.preguntaService.actualizarPregunta(this.pregunta)
        .subscribe((pregunta: Pregunta) => {
          this.snackBar.open('Se ha actualizado correctamente la pregunta ' + pregunta.pregId, 'x', {verticalPosition: 'top', duration: 10000});
          this.router.navigate(["/gestionPreguntas"]);
        },
        error => {
          this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
        });
    }

  }

  // public onChange( { editor }: ChangeEvent ) {
  //   if (editor){
  //     const data = editor.getData();

  //     console.log( data );
  //   }
  // }

}
