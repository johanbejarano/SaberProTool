import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
  
}
