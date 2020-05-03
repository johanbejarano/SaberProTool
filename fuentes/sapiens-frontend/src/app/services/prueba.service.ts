import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Prueba } from 'app/domain/prueba';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PruebaService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'prueba/';
  }

  public getPruebasDeUsuarioCreador(usuCreador: number): Observable<any> {
    return this.httpClient.get(this.url + 'getPruebasDeUsuarioCreador/' + usuCreador);
  }

  public getPrueba(prueId: number): Observable<any> {
    return this.httpClient.get(this.url + 'getPrueba/' + prueId);
  }
  
  public guardarPrueba(prueba: Prueba): Observable<any> {
    return this.httpClient.post(this.url + 'guardarPrueba/', prueba);
  }

  public modificarPrueba(prueba: Prueba): Observable<any> {
    return this.httpClient.put(this.url + 'modificarPrueba/', prueba);
  }

  public getPruebasEstudiante(usuaId: number): Observable<any> {
    return this.httpClient.get(this.url + 'getPruebasEstudiante/' + usuaId);
  }
}
