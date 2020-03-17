import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

  
  
}
