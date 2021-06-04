import { LocalStorageService } from './../../../services/local-storage.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-grupo',
  templateUrl: './grupo.component.html',
  styleUrls: ['./grupo.component.scss']
})
export class GrupoComponent implements OnInit {

  constructor(
    private localStorage: LocalStorageService,
    private router: Router,
  ) { }

  ngOnInit() {
  }

  crear(){
    console.log("Hola");
    this.localStorage.removeFromLocal('idGrupo');
    this.router.navigate(["/gestionGrupos/crearGrupo"]);
  }
}
