import { Component, Inject, OnInit, Pipe, PipeTransform } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { Pregunta } from 'app/domain/pregunta';
import { environment } from 'environments/environment';
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
    toolbar: []
  };

  public Editor = ClassicEditor;

  pregunta: Pregunta;

  constructor(@Inject(MAT_DIALOG_DATA) data: any) {
    this.pregunta = data;
  }

  ngOnInit() {
  }

  public setReadOnly(editor) {
    editor.isReadOnly = true;
  }

}

@Pipe({ name: 'safe' })
export class SafePipe implements PipeTransform {
  constructor(private sanitizer: DomSanitizer) { }
  transform(url) {
    return this.sanitizer.bypassSecurityTrustHtml(url);
  }
}