
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CrearGrupoComponent } from './grupo/crear-grupo/crear-grupo.component';
import { GrupoComponent } from './grupo/grupo.component';


const routes: Routes = [
  {path: '', component: GrupoComponent},
  {path: 'crearGrupo', component: CrearGrupoComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionGrupoRoutingModule { }
