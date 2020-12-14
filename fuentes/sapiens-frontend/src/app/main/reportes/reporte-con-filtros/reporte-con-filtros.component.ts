import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatSnackBar } from '@angular/material';
import { Reporte } from 'app/domain/reporte';
import { Usuario } from 'app/domain/usuario';
import { PruebaService } from 'app/services/prueba.service';
import { ReporteService } from 'app/services/reporte.service';
import { UsuarioService } from 'app/services/usuario.service';
import { createAndDownloadBlobFile } from 'app/utils/files';
import { global } from 'app/utils/global';
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

  constructor(private formBuilder: FormBuilder,
    private pruebaService: PruebaService,
    private usuarioService: UsuarioService,
    private reporteService: ReporteService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar) {
    this.usuario = this.usuarioService.getUsuario();
  }

  ngOnInit() {
    if (this.tipo == 1) {
      this.nombre = 'Consultar reporte de resultados';
    } else if (this.tipo == 2) {
      this.nombre = 'Consultar reporte resumen de estudiante';
    } else if (this.tipo == 3) {
      this.nombre = 'Consultar reporte prueba por módulos';
    } else if (this.tipo == 4) {
      this.nombre = 'Consultar reporte prueba detalle estudiantes';
    } else if (this.tipo == 5) {
      this.nombre = 'Consultar reporte prueba por programa';
    } else if (this.tipo == 6) {
      this.nombre = 'Consultar reporte prueba de estudiante';
    } else if (this.tipo == 7) {
      this.nombre = 'Consultar reporte participantes';
    }

    this.form = this.formBuilder.group({
      facuId: this.usuario.tiusId_TipoUsuario == global.TIPOS_USUARIO.ADMINISTRADOR ? '' : this.usuario.facuId,
      facultad: this.usuario.tiusId_TipoUsuario == global.TIPOS_USUARIO.ADMINISTRADOR ? '' : this.usuario.nombreFacultad,
      progId: this.usuario.tiusId_TipoUsuario == global.TIPOS_USUARIO.ADMINISTRADOR ? '' : this.usuario.progId_Programa,
      programa: this.usuario.tiusId_TipoUsuario == global.TIPOS_USUARIO.ADMINISTRADOR ? '' : this.usuario.nombrePrograma,
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
    } else if (this.tipo == 3) {
      if (!request.prueId) {
        this.snackBar.open('Se debe seleccionar la prueba', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      }
      this.reporteService.reportePruebaModulo(request).subscribe((result) => {
        const arrayBuffer = result.pdf;
        createAndDownloadBlobFile(arrayBuffer, 'reportePruebaModulo', 'pdf');
      });
    } else if (this.tipo == 4) {
      if (!request.prueId) {
        this.snackBar.open('Se debe seleccionar la prueba', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      }
      this.reporteService.reportePruebaDetalleEstudiante(request).subscribe((result) => {
        const arrayBuffer = result.pdf;
        createAndDownloadBlobFile(arrayBuffer, 'reportePruebaDetalleEstudiante', 'pdf');
      });
    } else if (this.tipo == 5) {
      if (!request.prueId) {
        this.snackBar.open('Se debe seleccionar la prueba', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      }
      this.reporteService.reportePruebaPrograma(request).subscribe((result) => {
        const arrayBuffer = result.pdf;
        createAndDownloadBlobFile(arrayBuffer, 'reportePruebaPrograma', 'pdf');
      });
    } else if (this.tipo == 6) {
      if (!request.prueId) {
        this.snackBar.open('Se debe seleccionar la prueba', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      } if (!request.usuaId) {
        this.snackBar.open('Se debe seleccionar el estudiante', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      }
      this.reporteService.reportePruebaEstudiante(request).subscribe((result) => {
        const arrayBuffer = result.pdf;
        createAndDownloadBlobFile(arrayBuffer, 'reportePruebaEstudiante', 'pdf');
      });
    } else if (this.tipo == 7) {
      if (!request.prueId) {
        this.snackBar.open('Se debe seleccionar la prueba', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      }
      this.reporteService.reportePruebaResultado(request).subscribe((result) => {
        const arrayBuffer = result.pdf;
        createAndDownloadBlobFile(arrayBuffer, 'reportePruebaResultado', 'xlsx');
      });
    }
  }

  abrirDialogo(tipo) {
    if (this.usuario.tiusId_TipoUsuario !== global.TIPOS_USUARIO.ADMINISTRADOR && (tipo == 'facultad' || tipo == 'programa')) {
      return;
    }
    let dialogRef = this.dialog.open(DialogoFiltroComponent, {
      data: tipo
    });

    let inputId;
    let inputNombre;
    if (tipo == 'facultad') {
      inputId = this.form.controls.facuId;
      inputNombre = this.form.controls.facultad;
    }
    if (tipo == 'programa') {
      inputId = this.form.controls.progId;
      inputNombre = this.form.controls.programa;
    }
    if (tipo == 'usuario') {
      inputId = this.form.controls.usuaId;
      inputNombre = this.form.controls.nombreUsuario;
    }
    if (tipo == 'prueba') {
      inputId = this.form.controls.prueId;
      inputNombre = this.form.controls.nombrePrueba;
    }
    if (tipo == 'modulo') {
      inputId = this.form.controls.moduId;
      inputNombre = this.form.controls.nombreModulo;
    }

    dialogRef.afterClosed().subscribe(result => {
      if (result) {

        inputId.setValue(result.id);
        inputNombre.setValue(result.nombre);
      }
    })
  }

}
