import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reporte } from 'app/domain/reporte';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReporteService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'reporte/';
  }

  public reportePruebaEstudiante(reporte: Reporte): Observable<any> {
    return this.httpClient.post(this.url + 'reportePruebaEstudiante/', reporte);
  }

  public reportePruebaModulo(reporte: Reporte): Observable<any> {
    return this.httpClient.post(this.url + 'reportePruebaModulo/', reporte);
  }

  public reportePruebaPrograma(reporte: Reporte): Observable<any> {
    return this.httpClient.post(this.url + 'reportePruebaPrograma/', reporte);
  }

  public reportePruebaDetalleEstudiante(reporte: Reporte): Observable<any> {
    return this.httpClient.post(this.url + 'reportePruebaDetalleEstudiante/', reporte);
  }

}
