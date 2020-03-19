import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PruebaComponent } from './prueba/prueba.component';
import { CrearPruebaComponent } from './prueba/crear-prueba/crear-prueba.component';


const routes: Routes = [
  {path: '', component: PruebaComponent},
  {path: 'registrarPrueba', component: CrearPruebaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionPruebaRoutingModule { }
