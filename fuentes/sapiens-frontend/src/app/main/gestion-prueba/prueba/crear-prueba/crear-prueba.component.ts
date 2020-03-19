import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LocalStorageService } from 'app/services/local-storage.service';
import { Prueba } from 'app/domain/prueba';

@Component({
  selector: 'app-crear-prueba',
  templateUrl: './crear-prueba.component.html',
  styleUrls: ['./crear-prueba.component.scss']
})
export class CrearPruebaComponent implements OnInit {

  form: FormGroup;
  frmPoblacion: FormGroup;

  prueba: Prueba;

  autoTicks = false;
  disabled = false;
  invert = false;
  max = 240;
  min = 0;
  showTicks = false;
  step = 1;
  thumbLabel = true;
  value = 0;
  vertical = false;
  tickInterval = 1;

  strBusqueda: string;
  
  constructor(
    private _formBuilder: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private localStorage: LocalStorageService,
  ) { }

  ngOnInit(): void {

    this.prueba = new Prueba();
    let idPrueba = this.localStorage.getFromLocal('prueId');

    if (idPrueba){
      //Se consulta la prueba
    }else{

    }

    this.actualizarFomulario();
  }

  actualizarFomulario(){
    // Reactive Form
    this.form = this._formBuilder.group({
      
      tipoPrueba: [this.prueba.tiprId_TipoPrueba, Validators.required],
      fechaInicial: [this.prueba.fechaInicial, Validators.required],
      fechaFinal: [this.prueba.fechaFinal, Validators.required],
      duracion: [this.prueba.tiempo, Validators.required],
      
    });

    this.frmPoblacion = this._formBuilder.group({
      busqueda: [this.strBusqueda],
    });
  }

  getSliderTickInterval(): number | 'auto' {
    if (this.showTicks) {
      return this.autoTicks ? 'auto' : this.tickInterval;
    }

    return 0;
  }

}
