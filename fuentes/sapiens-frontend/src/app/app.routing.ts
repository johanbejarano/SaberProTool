import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/compiler/src/core';
// import { global } from './utils/global';
// import { AuthGuard } from './utils/guards/auth.guard';

const routes: Routes = [
    
    // {path: '', redirectTo: '/auth/login', pathMatch: 'full'},
    // {path: 'auth', loadChildren: () => import('./main/auth/auth.module').then(mod => mod.AuthModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.NO_AUTH}},
    // {path: 'init', loadChildren: () => import('./main/auth/auth.module').then(mod => mod.AuthModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'parametrizacionVariables', loadChildren: () => import('./main/parametrizacion-variables/parametrizacion-variables.module').then(mod => mod.ParametrizacionVariablesModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'gestionEmpresarioSocio', loadChildren: () => import('./main/gestion-empresario-socio/gestion-empresario-socio.module').then(mod => mod.GestionEmpresarioSocioModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'gestionTerceroAliado', loadChildren: () => import('./main/gestion-tercero-aliado/gestion-tercero-aliado.module').then(mod => mod.GestionTerceroAliadoModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'gestionPuntoServicio', loadChildren: () => import('./main/gestion-punto-servicio/gestion-punto-servicio.module').then(mod => mod.GestionPuntoServicioModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'generarPlantilla', loadChildren: () => import('./main/generar-plantilla/generar-plantilla.module').then(mod => mod.GenerarPlantillaModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'monitoreoSeguimiento', loadChildren: () => import('./main/monitoreo-seguimiento/monitoreo-seguimiento.module').then(mod => mod.MonitoreoSeguimientoModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'reportes', loadChildren: () => import('./main/reportes/reportes.module').then(mod => mod.ReportesModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'trazabilidad', loadChildren: () => import('./main/trazabilidad/trazabilidad.module').then(mod => mod.TrazabilidadModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    // {path: 'gestionUsuario', loadChildren: () => import('./main/gestion-usuario/gestion-usuario.module').then(mod => mod.GestionUsuarioModule), canActivate: [AuthGuard], data: {guards: global.GUARDS.AUTH}},
    

    {path: '', redirectTo: '/auth/login', pathMatch: 'full'},
    {path: 'auth', loadChildren: () => import('./main/auth/auth.module').then(mod => mod.AuthModule)},
    {path: 'gestionPreguntas', loadChildren: () => import('./main/gestion-pregunta/gestion-pregunta.module').then(mod => mod.GestionPreguntaModule)},
    {path: '**', redirectTo: '/gestionPreguntas', pathMatch: 'full'},
];

export const routing : ModuleWithProviders = RouterModule.forRoot(routes);