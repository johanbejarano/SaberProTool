import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Modulo } from 'app/domain/modulo';
import { Prueba } from 'app/domain/prueba';
import { PruebaUsuario } from 'app/domain/prueba-usuario';
import { Reporte } from 'app/domain/reporte';
import { Usuario } from 'app/domain/usuario';
import { LocalStorageService } from 'app/services/local-storage.service';
import { ModuloService } from 'app/services/modulo.service';
import { PruebaUsuarioService } from 'app/services/prueba-usuario.service';
import { PruebaService } from 'app/services/prueba.service';
import { ReporteService } from 'app/services/reporte.service';
import { UsuarioService } from 'app/services/usuario.service';
import { createAndDownloadBlobFile } from 'app/utils/files';
import { Subscription } from 'rxjs';

@Component({
  selector: 'resumen-prueba',
  templateUrl: './resumen-prueba.component.html',
  styleUrls: ['./resumen-prueba.component.scss']
})
export class ResumenPruebaComponent implements OnInit {

  prueba: Prueba;
  pruebaUsuario: PruebaUsuario;
  form: FormGroup;

  modulosSeleccionados: number[] = [];
  listaModulos: Modulo[] = [];

  subscription: Subscription;

  usuario: Usuario;

  constructor(private pruebaService: PruebaService,
    private pruebaUsuarioService: PruebaUsuarioService,
    private localStorage: LocalStorageService,
    private usuarioService: UsuarioService,
    private snackBar: MatSnackBar,
    private moduloService: ModuloService,
    private formBuilder: FormBuilder,
    private router: Router,
    private datepipe: DatePipe,
    private reporteService: ReporteService) {
    this.usuario = usuarioService.getUsuario();
  }

  ngOnInit(): void {
    this.prueba = new Prueba();
    this.pruebaUsuario = this.localStorage.getFromLocal('pruebaUsuario');
    this.form = this.formBuilder.group({
      tipoPrueba: { value: '', disabled: true },
      fechaInicial: { value: '', disabled: true },
      fechaFinal: { value: '', disabled: true },
      duracion: { value: '', disabled: true },
    });

    if (this.pruebaUsuario) {

      //Se consulta la prueba
      this.pruebaService.getPrueba(this.pruebaUsuario.prueId)
        .subscribe((prueba: Prueba) => {
          this.prueba = prueba;
          this.modulosSeleccionados = prueba.idModulos;
          this.form.controls.tipoPrueba.setValue(this.prueba.tiprId_TipoPrueba);
          this.form.controls.fechaInicial.setValue(this.datepipe.transform(new Date(this.prueba.fechaInicial), 'dd/MM/yyyy HH:mm'));
          this.form.controls.fechaFinal.setValue(this.datepipe.transform(new Date(this.prueba.fechaFinal), 'dd/MM/yyyy HH:mm'));
          this.form.controls.duracion.setValue(this.pruebaUsuario.tiempoDisponible);
          this.getModulos();
        }, error => {
          this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
        });
    }
  }

  getModulos() {
    this.subscription = this.moduloService.find()
      .subscribe((modulos: Modulo[]) => {
        this.listaModulos = [];
        for (let i = 0; i < modulos.length; i++) {
          if (this.modulosSeleccionados.indexOf(modulos[i].moduId) !== -1) {
            this.listaModulos.push(modulos[i]);
          }
        }
      });
  }

  iniciarPrueba() {
    let fecha: Date = new Date();
    let fechaInicial = new Date(this.prueba.fechaInicial);
    let fechaFinal = new Date(this.prueba.fechaFinal);

    if (fecha.getTime() < fechaInicial.getTime()) {
      this.snackBar.open('La prueba no se encuentra en tiempos para iniciar', 'x', { verticalPosition: 'top', duration: 10000 });
      return;
    }
    let request: PruebaUsuario = new PruebaUsuario();
    request.prusId = this.pruebaUsuario.prusId;
    request.usuCreador = this.usuarioService.getUsuario().usuaId;
    this.subscription = this.pruebaUsuarioService.iniciarPrueba(request).subscribe(() => {
      this.pruebaUsuario.fechaInicio = new Date();
      this.pruebaUsuario.nombreEstadoPrueba = 'INICIADA';
      this.localStorage.putInLocal('pruebaUsuario', this.pruebaUsuario);
      this.irAPrueba();
    });
  }

  irAPrueba() {
    this.router.navigate(['/estudiante/prueba/']);
  }

  verInforme() {
    console.log(this.pruebaUsuario);
    let request = new Reporte();
    request.usuaId = this.usuario.usuaId;
    request.prueId = this.pruebaUsuario.prueId;

    this.reporteService.reportePruebaEstudiante(request).subscribe((result) => {
      const arrayBuffer = result.pdf;
      createAndDownloadBlobFile(arrayBuffer, 'reporteResultados', 'pdf');
    });
  }

}
