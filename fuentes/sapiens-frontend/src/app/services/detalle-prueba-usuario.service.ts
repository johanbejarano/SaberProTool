import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DetallePruebaUsuario } from 'app/domain/detalle-prueba-usuario';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DetallePruebaUsuarioService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'detallePruebaUsuario/';
  }

  public getPreguntasByPruebaUsuario(prusId: number): Observable<any> {
    return this.httpClient.get(this.url + 'getPreguntasByPruebaUsuario/' + prusId);
  }

  public responder(detallePruebaUsuario: DetallePruebaUsuario): Observable<any> {
    return this.httpClient.post(this.url + 'responder/', detallePruebaUsuario);
  }
}
