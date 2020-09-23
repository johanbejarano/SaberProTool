import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CrearEntrenamientoComponent } from './crear-entrenamiento/crear-entrenamiento.component';
import { MisPruebasComponent } from './mis-pruebas/mis-pruebas.component';
import { PruebaComponent } from './mis-pruebas/prueba/prueba.component';
import { ResumenPruebaComponent } from './mis-pruebas/resumen-prueba/resumen-prueba.component';

const routes: Routes = [
  { path: '', redirectTo: 'misPruebas' },
  { path: 'misPruebas', component: MisPruebasComponent },
  { path: 'resumenPrueba', component: ResumenPruebaComponent },
  { path: 'prueba', component: PruebaComponent },
  { path: 'crearEntrenamiento', component: CrearEntrenamientoComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EstudianteRoutingModule { }
