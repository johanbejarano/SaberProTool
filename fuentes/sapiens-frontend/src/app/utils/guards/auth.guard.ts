import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Usuario } from 'app/domain/usuario';
import { UsuarioService } from 'app/services/usuario.service';
import { Observable } from 'rxjs';
import { global } from '../global';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (next.data.guards === global.GUARDS.NO_AUTH) {
      const usuario: Usuario = this.usuarioService.getUsuario();
      if (usuario != null) {
        this.router.navigate(['/init']);
        return false;
      }
    } else if (next.data.guards === global.GUARDS.AUTH) {
      const usuario: Usuario = this.usuarioService.getUsuario();
      if (usuario == null) {
        this.router.navigate(['/auth/login']);
        return false;
      }
    }

    //Por rol
    if (next.data.guards === global.GUARDS.PROFESOR) {
      const usuario: Usuario = this.usuarioService.getUsuario();
      if (usuario == null) {
        this.router.navigate(['/auth/login']);
        return false;
      }
      if (usuario.tiusId_TipoUsuario === global.TIPOS_USUARIO.ESTUDIANTE) {
        this.router.navigate(['/init']);
        return false;
      }
    } else if (next.data.guards === global.GUARDS.DIRECTOR) {
      const usuario: Usuario = this.usuarioService.getUsuario();
      if (usuario == null) {
        this.router.navigate(['/auth/login']);
        return false;
      }
      if (usuario.tiusId_TipoUsuario !== global.TIPOS_USUARIO.DIRECTOR) {
        this.router.navigate(['/init']);
        return false;
      }
    } else if (next.data.guards === global.GUARDS.ESTUDIANTE) {
      const usuario: Usuario = this.usuarioService.getUsuario();
      if (usuario == null) {
        this.router.navigate(['/auth/login']);
        return false;
      }
      if (usuario.tiusId_TipoUsuario !== global.TIPOS_USUARIO.ESTUDIANTE) {
        this.router.navigate(['/init']);
        return false;
      }
    }

    return true;
  }

}
