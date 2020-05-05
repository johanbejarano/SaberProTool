import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PruebaUsuario } from 'app/domain/prueba-usuario';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PruebaUsuarioService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'pruebaUsuario/';
  }

  public getPruebas(usuaId: number, prusId: number): Observable<any> {
    return this.httpClient.get(this.url + 'getPruebas/' + usuaId + '/' + prusId);
  }

  public iniciarPrueba(pruebaUsuario: PruebaUsuario): Observable<any> {
    return this.httpClient.post(this.url + 'iniciarPrueba/', pruebaUsuario);
  }

  public pausarPrueba(pruebaUsuario: PruebaUsuario): Observable<any> {
    return this.httpClient.post(this.url + 'pausarPrueba/', pruebaUsuario);
  }

  public finalizarPrueba(pruebaUsuario: PruebaUsuario): Observable<any> {
    return this.httpClient.post(this.url + 'finalizarPrueba/', pruebaUsuario);
  }
}
