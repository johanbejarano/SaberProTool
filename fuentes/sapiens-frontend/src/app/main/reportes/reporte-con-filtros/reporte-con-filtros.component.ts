import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { Reporte } from 'app/domain/reporte';
import { Usuario } from 'app/domain/usuario';
import { PruebaService } from 'app/services/prueba.service';
import { UsuarioService } from 'app/services/usuario.service';
import { createAndDownloadBlobFile } from 'app/utils/files';
import { DialogoFiltroComponent } from '../dialogo-filtro/dialogo-filtro.component';

@Component({
  selector: 'reporte-con-filtros',
  templateUrl: './reporte-con-filtros.component.html',
  styleUrls: ['./reporte-con-filtros.component.scss']
})
export class ReporteConFiltrosComponent implements OnInit {

  @Input() tipo;
  nombre: string;

  form: FormGroup;

  usuario: Usuario;

  constructor(private formBuilder: FormBuilder, private pruebaService: PruebaService, private usuarioService: UsuarioService, private dialog: MatDialog) {
    this.usuario = this.usuarioService.getUsuario();
  }

  ngOnInit() {
    if (this.tipo == 1) {
      this.nombre = 'Consultar reporte de resultados';
    } else if (this.tipo == 2) {
      this.nombre = 'Consultar reporte resumen de estudiante';
    }

    this.form = this.formBuilder.group({
      facuId: this.usuario.facuId,
      progId: this.usuario.progId_Programa,
      usuaId: '',
      tiusId: '',
      prueId: '',
      esprId: '',
      pregId: '',
      respId: '',
      respOk: '',
      moduId: '',
      nombreUsuario: '',
      nombrePrueba: '',
      nombreModulo: '',
    })
  }

  descargarInforme() {
    let request = new Reporte();
    request.facuId = this.form.controls.facuId.value;
    request.progId = this.form.controls.progId.value;
    request.usuaId = this.form.controls.usuaId.value;
    request.tiusId = this.form.controls.tiusId.value;
    request.prueId = this.form.controls.prueId.value;
    request.esprId = this.form.controls.esprId.value;
    request.pregId = this.form.controls.pregId.value;
    request.respId = this.form.controls.respId.value;
    request.respOk = this.form.controls.respOk.value;
    request.moduId = this.form.controls.moduId.value;

    if (this.tipo == 1) {
      this.pruebaService.consultarReporteResultados(request).subscribe((result) => {
        const arrayBuffer = result.pdf;
        createAndDownloadBlobFile(arrayBuffer, 'reporteResultados', 'pdf');
      });
    } else if (this.tipo == 2) {
      this.pruebaService.consultarReporteResumenEstudiantes(request).subscribe((result) => {
        const arrayBuffer = result.pdf;
        createAndDownloadBlobFile(arrayBuffer, 'reporteResumenEstudiantes', 'pdf');
      });
    }
  }

  abrirDialogo(tipo) {
    let dialogRef = this.dialog.open(DialogoFiltroComponent, {
      data: tipo
    });

    let inputId;
    let inputNombre;
    if(tipo == 'usuario'){
      inputId = this.form.controls.usuaId;
      inputNombre = this.form.controls.nombreUsuario;
    }
    if(tipo == 'prueba'){
      inputId = this.form.controls.prueId;
      inputNombre = this.form.controls.nombrePrueba;
    }
    if(tipo == 'modulo'){
      inputId = this.form.controls.moduId;
      inputNombre = this.form.controls.nombreModulo;
    }

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        inputId.setValue(result.id);
        inputNombre.setValue(result.nombre);
      }
    })
  }

}
