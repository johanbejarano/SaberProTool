import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ContextoService } from 'app/services/contexto.service';
import { environment } from 'environments/environment';
import * as ClassicEditor from '../../../../../assets/ckeditor.js';

@Component({
  selector: 'app-editar-contexto',
  templateUrl: './editar-contexto.component.html',
  styleUrls: ['./editar-contexto.component.scss']
})
export class EditarContextoComponent implements OnInit {

  contexto: any;
  form: FormGroup;

  //tomado de https://stackoverflow.com/questions/46765197/how-to-enable-image-upload-support-in-ckeditor-5
  public editorConfig = {
    simpleUpload: {
      uploadUrl: environment.URL_CKEDITOR_UPLOAD,
    }
  };

  public Editor = ClassicEditor;

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
    private dialogRef: MatDialogRef<EditarContextoComponent>,
    private formBuilder: FormBuilder,
    private contextoService: ContextoService) {
    if (data) {
      this.contexto = data;
    } else {
      this.contexto = {};
    }
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      'nombre': [this.contexto.nombre, Validators.required],
      'descripcion': [this.contexto.descripcion, Validators.required],
    });
  }

  onFormSubmit() {
    if (this.form.valid) {
      this.contextoService.getByModulo
    }
  }


}
