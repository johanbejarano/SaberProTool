import { ModuleWithProviders } from '@angular/compiler/src/core';
import { RouterModule, Routes } from '@angular/router';
import { global } from './utils/global';
import { AuthGuard } from './utils/guards/auth.guard';

const routes: Routes = [
    
    {path: '', redirectTo: '/auth/login', pathMatch: 'full'},
    {path: 'auth', loadChildren: () => import('./main/auth/auth.module').then(mod => mod.AuthModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.NO_AUTH}},
    {path: 'init', loadChildren: () => import('./main/auth/auth.module').then(mod => mod.AuthModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    {path: 'gestionPreguntas', loadChildren: () => import('./main/gestion-pregunta/gestion-pregunta.module').then(mod => mod.GestionPreguntaModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.PROFESOR}},
    {path: 'gestionPruebas', loadChildren: () => import('./main/gestion-prueba/gestion-prueba.module').then(mod => mod.GestionPruebaModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.PROFESOR}},
    {path: 'gestionGrupos', loadChildren: () => import('./main/gestion-grupo/gestion-grupo.module').then(mod => mod.GestionGrupoModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.PROFESOR}},
    {path: 'estudiante', loadChildren: () => import('./main/estudiante/estudiante.module').then(mod => mod.EstudianteModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.ESTUDIANTE}},
    {path: 'gestionUsuarios', loadChildren: () => import('./main/gestion-usuario/gestion-usuario.module').then(mod => mod.GestionUsuarioModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.DIRECTOR}},
    {path: 'gestionModulos', loadChildren: () => import('./main/gestion-modulo/gestion-modulo.module').then(mod => mod.GestionModuloModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.DIRECTOR}},
    {path: 'reportes', loadChildren: () => import('./main/reportes/reportes.module').then(mod => mod.ReportesModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.DIRECTOR}},
    {path: '**', redirectTo: '/init', pathMatch: 'full'}
];

export const routing : ModuleWithProviders = RouterModule.forRoot(routes);