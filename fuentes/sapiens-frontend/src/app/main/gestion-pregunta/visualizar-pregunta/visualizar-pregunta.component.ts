import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { Pregunta } from 'app/domain/pregunta';
import { environment } from 'environments/environment.js';
import * as ClassicEditor from '../../../../assets/ckeditor.js';

@Component({
  selector: 'app-visualizar-pregunta',
  templateUrl: './visualizar-pregunta.component.html',
  styleUrls: ['./visualizar-pregunta.component.scss']
})
export class VisualizarPreguntaComponent implements OnInit {

  //tomado de https://stackoverflow.com/questions/46765197/how-to-enable-image-upload-support-in-ckeditor-5
  public editorConfig = {
    simpleUpload: {
      uploadUrl: environment.URL_CKEDITOR_UPLOAD,
    },
  };

  public Editor = ClassicEditor;

  pregunta: Pregunta;

  constructor(@Inject(MAT_DIALOG_DATA) data: any) {
    this.pregunta = data;
  }

  ngOnInit() {
  }
  
}
