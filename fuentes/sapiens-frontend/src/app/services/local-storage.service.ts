import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() { }

  putInLocal(id: string, value: any){
    localStorage.setItem(id, JSON.stringify(value));
  }

  getFromLocal(id: string) : any{
    return JSON.parse(localStorage.getItem(id));
  }

  clearLocal(){
    localStorage.clear();
  }

  removeFromLocal(id: string){
    localStorage.removeItem(id);
  }
}
