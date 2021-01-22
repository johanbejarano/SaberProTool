import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { CargueMasivo } from 'app/domain/cargue-masivo';
import { Usuario } from 'app/domain/usuario';
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

  private usuarios: Usuario[];

  public selectedFiles: File;
  private usuario: Usuario;

  constructor(private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private snackBar: MatSnackBar,
    private usuarioService: UsuarioService) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
    this.usuario = this.usuarioService.getUsuario();
  }

  ngOnInit() { }

  seleccionar() {
    if (this.fileInput) {
      this.fileInput.nativeElement.value = "";
      this.selectedFiles = null;
      this.usuarios = null;
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
        this.usuarios = [];
        let workBook: XLSX.WorkBook = XLSX.read(reader.result, { type: "binary" });
        let worksheet = workBook.Sheets[workBook.SheetNames[0]];

        const archivoToJson: any[] = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

        for (let i = 1; i < archivoToJson.length; i++) {
          const usuarioArray: any[] = archivoToJson[i];

          if (!usuarioArray || usuarioArray.length == 0) {
            continue;
          }
          console.log(usuarioArray);
          

          let usuario: Usuario = new Usuario();

          usuario.codigo = usuarioArray[0];
          usuario.nombre = usuarioArray[1];
          usuario.apellido = usuarioArray[2];
          usuario.identificacion = usuarioArray[3];
          usuario.correo = usuarioArray[4];
          usuario.celular = usuarioArray[5];
          if (usuarioArray[6].toLowerCase() == 'masculino' || usuarioArray[6].toLowerCase() == 'm') {
            usuario.genero = 'M';
          } else if (usuarioArray[6].toLowerCase() == 'femenino' || usuarioArray[6].toLowerCase() == 'f') {
            usuario.genero = 'F';
          } else if (usuarioArray[6].toLowerCase() == 'otro' || usuarioArray[6].toLowerCase() == 'o') {
            usuario.genero = 'O';
          }else{
            this.usuarios = [];
            this.snackBar.open('El género ' + usuarioArray[6] + ' no es válido', '×', { panelClass: 'error', verticalPosition: 'top', duration: 8000 });
            return;
          }
          usuario.nombrePrograma = usuarioArray[7];
          if (usuarioArray[8] == 'Estudiante') {
            usuario.tiusId_TipoUsuario = 3;
          }else if (usuarioArray[8] == 'Profesor') {
            usuario.tiusId_TipoUsuario = 1;
          }else if (usuarioArray[8] == 'Director') {
            usuario.tiusId_TipoUsuario = 2;
          }else{
            this.usuarios = [];
            this.snackBar.open('El tipo de usuario ' + usuarioArray[8] + ' no es válido', '×', { panelClass: 'error', verticalPosition: 'top', duration: 8000 });
            return;
          }
          usuario.perfil = usuarioArray[8];
          this.usuarios.push(usuario);
        }
      }
    };

    reader.readAsBinaryString(this.selectedFiles);
  }

  guardar() {
    if (this.usuarios && this.usuarios.length > 0) {
      let request: CargueMasivo = new CargueMasivo();

      request.usuarios = this.usuarios;
      request.usuarioCreador = this.usuario.usuaId;
      this.usuarioService.cargar(request).subscribe(() => {
        this.snackBar.open(espanol.data.msg.guardadoExitoso, '×', { panelClass: 'info', verticalPosition: 'top', duration: 8000 });
      }, error => {
        if(error.error){
          this.snackBar.open(error.error, '×', { panelClass: 'error', verticalPosition: 'top', duration: 8000 });
        }
      });
    }
  }

}