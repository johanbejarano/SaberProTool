import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PreguntaComponent } from './pregunta/pregunta.component';
import { PreguntaListComponent } from './pregunta/pregunta-list/pregunta-list.component';
import { CrearPreguntaComponent } from './pregunta/crear-pregunta/crear-pregunta.component';


const routes: Routes = [
  {path: '', component: PreguntaComponent},
  {path: 'registrarPregunta', component: CrearPreguntaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionPreguntaRoutingModule { }
