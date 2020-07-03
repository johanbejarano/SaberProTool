import { DatePipe } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  selector: 'app-crear-prueba',
  templateUrl: './crear-prueba.component.html',
  styleUrls: ['./crear-prueba.component.scss']
})
export class CrearPruebaComponent implements OnInit, OnDestroy {

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

  usuariosSeleccionados: number[] = [];
  modulosSeleccionados: number[] = [];
  listaModulos: Modulo[];

  usuario: Usuario;
  
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
    let idPrueba = this.localStorage.getFromLocal('idPrueba');

    if (idPrueba){
      
      //Se consulta la prueba
      this.pruebaService.getPrueba(idPrueba)
        .subscribe((prueba: Prueba) => {
          this.prueba = prueba;
          this.modulosSeleccionados = prueba.idModulos;
          this.usuariosSeleccionados = prueba.idUsuarios;

          this.actualizarFomulario();

          this.localStorage.putInLocal('x', this.prueba);
        },
        error => {
          this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
        });

    }else{
      this.actualizarFomulario();
    }

    this.getModulos();

    this.actualizarFomulario();
  }

  ngOnDestroy(): void {
    if (this.subscription !== null && this.subscription !== undefined){
      this.subscription.unsubscribe();
    }

    this.localStorage.removeFromLocal('idPrueba');

  }

  actualizarFomulario(){
    // Reactive Form
    this.form = this._formBuilder.group({
      
      tipoPrueba: [this.prueba.tiprId_TipoPrueba, Validators.required],
      fechaInicial: [this.prueba.fechaInicial ? new DatePipe('en-US').transform(this.prueba.fechaInicial, 'yyyy-MM-ddTHH:mm') : '', Validators.required],
      fechaFinal: [this.prueba.fechaFinal ? new DatePipe('en-US').transform(this.prueba.fechaFinal, 'yyyy-MM-ddTHH:mm') : '', Validators.required],
      duracion: [this.prueba.tiempo, Validators.required],
      modulos: [this.modulosSeleccionados, Validators.required],
    });

    
  }

  getSliderTickInterval(): number | 'auto' {
    if (this.showTicks) {
      return this.autoTicks ? 'auto' : this.tickInterval;
    }

    return 0;
  }

  getModulos(){

    this.subscription = this.moduloService.find()
      .subscribe((modulos: Modulo[]) => {
        this.listaModulos = modulos;
      });

  }

  guardarPrueba(){
    if (!this.form.valid){
      this.snackBar.open('Faltan datos por diligenciar', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }

    if (!this.usuariosSeleccionados || this.usuariosSeleccionados.length==0){
      this.snackBar.open('Debe seleccionar la población objetivo de la prueba', 'x', {verticalPosition: 'top', duration: 10000});
      return;
    }

    this.prueba = new Prueba();
    this.prueba.fechaInicial = new Date(this.form.controls.fechaInicial.value);
    this.prueba.fechaFinal = new Date(this.form.controls.fechaFinal.value);
    this.prueba.idModulos = this.form.controls.modulos.value;
    this.prueba.idUsuarios = this.usuariosSeleccionados;
    this.prueba.tiprId_TipoPrueba = this.form.controls.tipoPrueba.value;
    this.prueba.tiempo = this.form.controls.duracion.value;
    this.prueba.usuCreador = this.usuario.usuaId;

    let idPrueba = this.localStorage.getFromLocal('idPrueba'); 

    this.localStorage.putInLocal('x', this.prueba);

    if (!idPrueba){
    
      this.subscription = this.pruebaService.guardarPrueba(this.prueba)
          .subscribe((prueba: Prueba) => {
            this.snackBar.open('Se ha almacenado correctamente la prueba ' + prueba.prueId, 'x', {verticalPosition: 'top', duration: 10000});
            this.router.navigate(["/gestionPruebas"]);
          },
          error => {
            this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
          });

      }else{

        this.prueba.prueId = idPrueba;

        this.subscription = this.pruebaService.modificarPrueba(this.prueba)
          .subscribe((prueba: Prueba) => {
            this.snackBar.open('Se ha modificado correctamente la prueba ' + prueba.prueId, 'x', {verticalPosition: 'top', duration: 10000});
            this.router.navigate(["/gestionPruebas"]);
          },
          error => {
            this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
          });

      }
    }

}
