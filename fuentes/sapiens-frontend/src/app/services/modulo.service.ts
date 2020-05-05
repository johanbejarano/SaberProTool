import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Modulo } from 'app/domain/modulo';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModuloService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'modulo/';
  }

  public find(): Observable<any> {
    return this.httpClient.get(this.url + 'findAll/');
  }

  public findByTipoModulo(timoId: number): Observable<any> {
    return this.httpClient.get(this.url + 'findByTipoModulo/' + timoId);
  }

  public guardar(modulo: Modulo): Observable<any> {
    return this.httpClient.post(this.url + 'guardar/', modulo);
  }
}
