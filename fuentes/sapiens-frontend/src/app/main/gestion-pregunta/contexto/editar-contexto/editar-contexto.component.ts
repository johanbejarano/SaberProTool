import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { Contexto } from 'app/domain/contexto';
import { Usuario } from 'app/domain/usuario';
import { ContextoService } from 'app/services/contexto.service';
import { UsuarioService } from 'app/services/usuario.service';
import { environment } from 'environments/environment';
import * as ClassicEditor from '../../../../../assets/ckeditor';

@Component({
  selector: 'app-editar-contexto',
  templateUrl: './editar-contexto.component.html',
  styleUrls: ['./editar-contexto.component.scss']
})
export class EditarContextoComponent implements OnInit {

  contexto: Contexto;
  form: FormGroup;

  usuario: Usuario;

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
    private contextoService: ContextoService,
    private snackBar: MatSnackBar,
    usuarioService: UsuarioService) {
    this.contexto = data;
    this.usuario = usuarioService.getUsuario();
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      'nombre': [this.contexto.nombre, Validators.required],
      'descripcion': [this.contexto.descripcion, Validators.required],
    });
  }

  onFormSubmit() {
    if (this.form.valid) {
      let request: Contexto = new Contexto();
      request.contId = this.contexto.contId;
      request.moduId = this.contexto.moduId;
      request.nombre = this.form.controls.nombre.value;
      request.descripcion = this.form.controls.descripcion.value;
      request.usuCreador = this.usuario.usuaId;
      this.contextoService.guardar(request).subscribe(() => {
        this.snackBar.open('Se ha guardado el contexto', 'x', { verticalPosition: 'top', duration: 10000 });
        this.dialogRef.close(true);
      });
    }
  }
}
