import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Modulo } from 'app/domain/modulo';
import { Prueba } from 'app/domain/prueba';
import { Usuario } from 'app/domain/usuario';
import { LocalStorageService } from 'app/services/local-storage.service';
import { ModuloService } from 'app/services/modulo.service';
import { PruebaService } from 'app/services/prueba.service';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-crear-entrenamiento',
  templateUrl: './crear-entrenamiento.component.html',
  styleUrls: ['./crear-entrenamiento.component.scss']
})
export class CrearEntrenamientoComponent implements OnInit {

  form: FormGroup;
  subscription: Subscription;

  prueba: Prueba;

  autoTicks = false;
  disabled = false;
  invert = false;
  max = 240;
  min = 0;
  showTicks = true;
  step = 2;
  thumbLabel = true;
  value = 0;
  vertical = false;
  tickInterval = 1;

  strBusqueda: string;

  modulosSeleccionados: number[] = [];
  listaModulos: Modulo[];

  usuario: Usuario;
  hoy = new Date();

  constructor(
    private _formBuilder: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private moduloService: ModuloService,
    private localStorage: LocalStorageService,
    private pruebaService: PruebaService,
    private usuarioService: UsuarioService
  ) {
    this.usuario = usuarioService.getUsuario();
  }

  ngOnInit(): void {

    this.prueba = new Prueba();
    this.getModulos();
    this.actualizarFomulario();
  }

  ngOnDestroy(): void {
    if (this.subscription !== null && this.subscription !== undefined) {
      this.subscription.unsubscribe();
    }
  }

  actualizarFomulario() {
    // Reactive Form
    this.hoy.setSeconds(0);
    this.form = this._formBuilder.group({
      fechaInicial: [this.hoy, Validators.required],
      fechaFinal: [null, Validators.required],
      duracion: [this.prueba.tiempo, Validators.required],
      modulos: [this.modulosSeleccionados, Validators.required],
    });

    this.form.controls.fechaInicial.valueChanges.subscribe((event: Date) => {
      let fecha: Date = event;
      fecha.setSeconds(0);
      this.form.controls.fechaInicial.setValue(fecha, { emitEvent: false });
    });

    this.form.controls.fechaFinal.valueChanges.subscribe((event: Date) => {
      let fecha: Date = event;
      fecha.setSeconds(0);
      this.form.controls.fechaFinal.setValue(fecha, { emitEvent: false });
    });

  }

  getSliderTickInterval(): number | 'auto' {
    if (this.showTicks) {
      return this.autoTicks ? 'auto' : this.tickInterval;
    }

    return 0;
  }

  getModulos() {

    this.subscription = this.moduloService.find()
      .subscribe((modulos: Modulo[]) => {
        this.listaModulos = modulos;
      });

  }

  guardarPrueba() {

    if (!this.form.valid) {
      this.snackBar.open('Faltan datos por diligenciar', 'x', { verticalPosition: 'top', duration: 10000 });
      return;
    }


    if (this.form.controls.fechaInicial.value === this.form.controls.fechaFinal.value) {
      this.snackBar.open('La fecha final debe ser superior a la inicial', 'x', { verticalPosition: 'top', duration: 10000 });
      return;
    }

    this.prueba = new Prueba();
    this.prueba.fechaInicial = new Date(this.form.controls.fechaInicial.value);
    this.prueba.fechaFinal = new Date(this.form.controls.fechaFinal.value);
    this.prueba.idModulos = this.form.controls.modulos.value;
    this.prueba.idUsuarios = [this.usuario.usuaId];
    this.prueba.tiprId_TipoPrueba = 1;
    this.prueba.tiempo = this.form.controls.duracion.value;
    this.prueba.usuCreador = this.usuario.usuaId;

    this.localStorage.putInLocal('x', this.prueba);

    this.subscription = this.pruebaService.guardarPrueba(this.prueba)
      .subscribe((prueba: Prueba) => {
        this.snackBar.open('Se ha almacenado correctamente el entrenamiento ' + prueba.prueId, 'x', { verticalPosition: 'top', duration: 10000 });
        this.router.navigate(["/estudiante/misPruebas"]);
      },
        error => {
          this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
        });
  }

}
