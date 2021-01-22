import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { CargueMasivo } from 'app/domain/cargue-masivo';
import { Pregunta } from 'app/domain/pregunta';
import { Respuesta } from 'app/domain/respuesta';
import { Usuario } from 'app/domain/usuario';
import { PreguntaService } from 'app/services/pregunta.service';
import { UsuarioService } from 'app/services/usuario.service';
import { global } from 'app/utils/global';
import * as XLSX from 'xlsx';
import { locale as espanol } from '../i18n/es';

@Component({
  selector: 'cargar-archivo',
  templateUrl: './cargar-archivo.component.html',
  styleUrls: ['./cargar-archivo.component.scss']
})
export class CargarArchivoComponent implements OnInit {

  @ViewChild('fileInput', { static: false }) fileInput: ElementRef;
  @Output() actualizar = new EventEmitter();

  private preguntas: Pregunta[];

  public selectedFiles: File;
  private usuario: Usuario;

  constructor(private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private snackBar: MatSnackBar,
    private usuarioService: UsuarioService,
    private preguntaService: PreguntaService) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
    this.usuario = this.usuarioService.getUsuario();
  }

  ngOnInit() { }

  seleccionar() {
    if (this.fileInput) {
      this.fileInput.nativeElement.value = "";
      this.selectedFiles = null;
      this.preguntas = null;
    }
  }

  selectFile(event) {
    this.selectedFiles = event.target.files[0];
    const filesize: number = ((event.target.files[0].size / 1024) / 1024);
    const ext: string = event.target.files[0].name.split('.').pop();
    if (filesize > 5 || global.EXTENSIONES.indexOf(ext.toLowerCase()) === -1) {
      this.snackBar.open('No es posible procesar el archivo', '×', { panelClass: 'error', verticalPosition: 'top', duration: 8000 });
      this.fileInput.nativeElement.value = "";
      this.selectedFiles = null;
    } else {
      this.upload();
    }
  }

  upload() {
    let reader = new FileReader();
    reader.onloadend = (e) => {
      if (reader.readyState == FileReader.DONE) {
        this.preguntas = [];
        let workBook: XLSX.WorkBook = XLSX.read(reader.result, { type: "binary" });
        let worksheet = workBook.Sheets[workBook.SheetNames[0]];

        const archivoToJson: any[] = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

        for (let i = 1; i < archivoToJson.length; i++) {
          const preguntaArray: any[] = archivoToJson[i];

          if (!preguntaArray || preguntaArray.length == 0) {
            continue;
          }

          let pregunta: Pregunta = new Pregunta();

          pregunta.nombreModulo = preguntaArray[0];
          if(preguntaArray[1].toLowerCase() == 'abierta' || preguntaArray[1].toLowerCase() == 'a'){
            pregunta.tprgId_TipoPregunta = 2;
          }else if(preguntaArray[1].toLowerCase() == 'cerrada' || preguntaArray[1].toLowerCase() == 'c'){
            pregunta.tprgId_TipoPregunta = 1;
          }else{
            this.preguntas = [];
            this.snackBar.open('El tipo de pregunta ' + preguntaArray[1] + ' no es válido', '×', { panelClass: 'error', verticalPosition: 'top', duration: 8000 });
            return;
          }
          pregunta.descripcion = preguntaArray[2];
          pregunta.retroalimentacion = preguntaArray[3];
          pregunta.complejidad = preguntaArray[4];
          pregunta.valorPregunta = preguntaArray[5];
          pregunta.orden = preguntaArray[6];

          let respuestas: Respuesta[] = [];
          for (let i = 6; i < preguntaArray.length; i++) {
            const respuesta: Respuesta = new Respuesta();
            respuesta.descripcion = preguntaArray[i];
            respuesta.correcta = i == 6 ? 1 : 0;
            respuestas.push(respuesta);
          }
          pregunta.respuestasDTO = respuestas;

          this.preguntas.push(pregunta);
        }
      }
    };

    reader.readAsBinaryString(this.selectedFiles);
  }

  guardar() {
    if (this.preguntas && this.preguntas.length > 0) {
      let request: CargueMasivo = new CargueMasivo();

      request.preguntas = this.preguntas;
      request.usuarioCreador = this.usuario.usuaId;
      this.preguntaService.cargar(request).subscribe(() => {
        this.snackBar.open(espanol.data.msg.guardadoExitoso, '×', { panelClass: 'info', verticalPosition: 'top', duration: 8000 });
      }, error => {
        this.snackBar.open(error.error, '×', { panelClass: 'error', verticalPosition: 'top', duration: 8000 });
      });
    }
  }

}