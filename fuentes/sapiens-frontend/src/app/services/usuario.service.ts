import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';
import { global } from 'app/utils/global';
import { Usuario } from 'app/domain/usuario';
import { environment } from 'environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url: string;

  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) {
    this.url = environment.URL + 'usuario/';
  }

  public getUsuario(): Usuario {
    let usuario: Usuario = this.localStorage.getFromLocal(global.SESSION_ITEMS.USUARIO);

    return usuario;
  }

  public login(usuario: Usuario): Observable<any> {
    return this.httpClient.post(this.url + 'login/', usuario);
  }

  public find(): Observable<any> {
    return this.httpClient.get(this.url + 'find/');
  }

  public guardar(usuario: Usuario): Observable<any> {
    return this.httpClient.post(this.url + 'guardar/', usuario);
  }

  public eliminar(usuario: Usuario): Observable<any> {
    return this.httpClient.post(this.url + 'eliminar/', usuario);
  }

  public getUsuariosPorTipo(tiusId: number, filtro: string, pageNumber: number, pageSize: number): Observable<any> {
    return this.httpClient.get(this.url + 'getUsuariosPorTipo/'+tiusId+"/"+filtro+"/"+pageNumber+"/"+pageSize);
  }

}
