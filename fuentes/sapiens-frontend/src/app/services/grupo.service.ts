import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Grupo } from 'app/domain/grupo';

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

  public guardarGrupo(grupo: Grupo): Observable<any>{
    return this.httpClient.post(this.url + 'guardarGrupo/',grupo);
  }

  public findById(grupoId: number): Observable<any>{
    return this.httpClient.get(this.url + 'findById/' + grupoId);
  }

  public actualizarGrupo(grupo: Grupo): Observable<any>{
    return this.httpClient.post(this.url + 'actualizarGrupo/', grupo);
  }
}
