import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Prueba } from 'app/domain/prueba';
import { Reporte } from 'app/domain/reporte';
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
    console.log(this.url + 'getPruebasDeUsuarioCreador/');
    
    return this.httpClient.get(this.url + 'getPruebasDeUsuarioCreador/' + usuCreador);
  }

  public find(filtro: string): Observable<any> {
    console.log(this.url + 'find/');
    
    return this.httpClient.get(this.url + 'find/' + filtro);
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

  public consultarReporteResultados(reporte: Reporte): Observable<any> {
    return this.httpClient.post(this.url + 'consultarReporteResultados/', reporte);
  }

  public consultarReporteResumenEstudiantes(reporte: Reporte): Observable<any> {
    return this.httpClient.post(this.url + 'consultarReporteResumenEstudiantes/', reporte);
  }
}
