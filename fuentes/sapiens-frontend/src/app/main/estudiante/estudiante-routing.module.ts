import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MisPruebasComponent } from './mis-pruebas/mis-pruebas.component';
import { PruebaComponent } from './mis-pruebas/prueba/prueba.component';
import { ResumenPruebaComponent } from './mis-pruebas/resumen-prueba/resumen-prueba.component';

const routes: Routes = [
  { path: '', redirectTo: 'misPruebas' },
  { path: 'misPruebas', component: MisPruebasComponent },
  { path: 'resumenPrueba', component: ResumenPruebaComponent },
  { path: 'prueba', component: PruebaComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EstudianteRoutingModule { }
