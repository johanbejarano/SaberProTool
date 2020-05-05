import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { LocalStorageService } from 'app/services/local-storage.service';
import { locale as espanol } from '../i18n/es';


@Component({
  selector: 'app-pregunta',
  templateUrl: './pregunta.component.html',
  styleUrls: ['./pregunta.component.scss']
})
export class PreguntaComponent implements OnInit {

  actualizar: boolean = true;

  constructor(private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private localStorage: LocalStorageService,
    private router: Router) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
  }

  ngOnInit() {
  }

  crear(){
    this.localStorage.removeFromLocal('idPregunta');
    this.router.navigate(["/gestionPreguntas/registrarPregunta"]);
  }

}
