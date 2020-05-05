import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ProgramaService {

    url: string;

    constructor(private httpClient: HttpClient) {
        this.url = environment.URL + 'programa/';
    }

    public getAll(): Observable<any> {
        return this.httpClient.get(this.url + 'findAll/');
    }
}
