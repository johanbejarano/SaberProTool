import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LocalStorageService } from 'app/services/local-storage.service';
import { Prueba } from 'app/domain/prueba';
import { Modulo } from 'app/domain/modulo';
import { Subscription } from 'rxjs';
import { ModuloService } from 'app/services/modulo.service';
import { PruebaService } from 'app/services/prueba.service';
import { Usuario } from 'app/domain/usuario';
import { UsuarioService } from 'app/services/usuario.service';

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
    let idPrueba = this.localStorage.getFromLocal('prueId');

    if (idPrueba){
      //Se consulta la prueba
    }else{

    }

    this.getModulos();

    this.actualizarFomulario();
  }

  ngOnDestroy(): void {
    if (this.subscription !== null && this.subscription !== undefined){
      this.subscription.unsubscribe();
    }

  }

  actualizarFomulario(){
    // Reactive Form
    this.form = this._formBuilder.group({
      
      tipoPrueba: [this.prueba.tiprId_TipoPrueba, Validators.required],
      fechaInicial: [this.prueba.fechaInicial, Validators.required],
      fechaFinal: [this.prueba.fechaFinal, Validators.required],
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
    }

    this.prueba = new Prueba();
    this.prueba.fechaInicial = this.form.controls.fechaInicial.value;
    this.prueba.fechaFinal = this.form.controls.fechaFinal.value;
    this.prueba.idModulos = this.form.controls.modulos.value;
    this.prueba.idUsuarios = this.usuariosSeleccionados;
    this.prueba.tiprId_TipoPrueba = this.form.controls.tipoPrueba.value;
    this.prueba.tiempo = this.form.controls.duracion.value;
    this.prueba.usuCreador = this.usuario.usuaId;

    this.localStorage.putInLocal('x', this.prueba);

    this.subscription = this.pruebaService.guardarPrueba(this.prueba)
        .subscribe((prueba: Prueba) => {
          this.snackBar.open('Se ha almacenado correctamente la prueba ' + prueba.prueId, 'x', {verticalPosition: 'top', duration: 10000});
          this.router.navigate(["/gestionPruebas"]);
        },
        error => {
          this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
        });
  }

}
