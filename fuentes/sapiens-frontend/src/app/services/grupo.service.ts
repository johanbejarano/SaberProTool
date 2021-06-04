import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = environment.URL + 'grupo/';
  }

  public consultarGrupos(): Observable<any> {
    return this.httpClient.get(this.url + 'consultarGrupos/');
  }
}
