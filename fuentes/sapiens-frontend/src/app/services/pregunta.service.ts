import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CargueMasivo } from 'app/domain/cargue-masivo';
import { Pregunta } from 'app/domain/pregunta';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreguntaService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'pregunta/';
  }

  public guardarPregunta(pregunta: Pregunta): Observable<any> {
    return this.httpClient.post(this.url + 'guardarPregunta/', pregunta);
  }

  public actualizarPregunta(pregunta: Pregunta): Observable<any> {
    return this.httpClient.post(this.url + 'actualizarPregunta/', pregunta);
  }

  public findById(pregId: number): Observable<any> {
    return this.httpClient.get(this.url + 'findById/' + pregId);
  }

  public find(): Observable<any> {
    return this.httpClient.get(this.url + 'findAll/');
  }

  public getPregunta(pregId: number): Observable<any> {
    return this.httpClient.get(this.url + 'getPregunta/' + pregId);
  }

  public getPreguntasPorUsuario(usuaId: number): Observable<any> {
    return this.httpClient.get(this.url + 'getPreguntasPorUsuario/' + usuaId);
  }

  public eliminar(rol: Pregunta): Observable<any> {
    return this.httpClient.post(this.url + 'eliminar/', rol);
  }

  public cargar(request: CargueMasivo): Observable<any> {
    return this.httpClient.post(this.url + 'cargar/', request);
  }

  public getAllPreguntasPorModulos(moduIds: number[], filtro: string): Observable<any> {
    if (!filtro || filtro.length == 0 || filtro === '%') {
      filtro = "*";
    }
    return this.httpClient.get(this.url + 'getAllPreguntasPorModulos/' + moduIds + "/" + filtro);
  }

  public getPreguntasPorModulos(moduIds: number[], filtro: string, pageNumber: number, pageSize: number): Observable<any> {
    if (!filtro || filtro.length == 0 || filtro === '%') {
      filtro = "*";
    }
    return this.httpClient.get(this.url + 'getPreguntasPorModulos/' + moduIds +  "/" + filtro + "/" + pageNumber + "/" + pageSize);
  }
}
