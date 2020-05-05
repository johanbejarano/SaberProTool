import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from 'app/services/local-storage.service';

@Component({
  selector: 'app-prueba',
  templateUrl: './prueba.component.html',
  styleUrls: ['./prueba.component.scss']
})
export class PruebaComponent implements OnInit {

  constructor(private localStorage: LocalStorageService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  crear() {
    this.localStorage.removeFromLocal('idPregunta');
    this.router.navigate(["/gestionPruebas/registrarPrueba"]);
  }

}
