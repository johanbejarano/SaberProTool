import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from 'app/domain/usuario';
import { global } from 'app/utils/global';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

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
    if (!filtro || filtro.length==0 || filtro === '%'){
      filtro = "*";
    }
    return this.httpClient.get(this.url + 'getUsuariosPorTipo/'+tiusId+"/"+filtro+"/"+pageNumber+"/"+pageSize);
  }
}
