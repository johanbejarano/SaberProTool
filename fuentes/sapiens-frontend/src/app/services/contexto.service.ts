import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContextoService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'contexto/';
  }

  public getByModulo(moduId): Observable<any> {
    return this.httpClient.get(this.url + 'getByModulo/' + moduId);
  }

  public guardar(contexto): Observable<any> {
    return this.httpClient.post(this.url + 'guardar/', contexto);
  }
}
