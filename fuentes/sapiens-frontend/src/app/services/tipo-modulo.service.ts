import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { Pregunta } from 'app/domain/pregunta';

@Injectable({
  providedIn: 'root'
})
export class TipoModuloService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'tipoModulo/';
  }

  public find(): Observable<any> {
    return this.httpClient.get(this.url + 'findAll/');
  }

  public guardar(rol: Pregunta): Observable<any> {
    return this.httpClient.post(this.url + 'guardar/', rol);
  }

  public eliminar(rol: Pregunta): Observable<any> {
    return this.httpClient.post(this.url + 'eliminar/', rol);
  }
  
}
