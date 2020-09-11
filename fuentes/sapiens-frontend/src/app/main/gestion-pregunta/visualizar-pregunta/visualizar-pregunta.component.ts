import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { Pregunta } from 'app/domain/pregunta';

@Component({
  selector: 'app-visualizar-pregunta',
  templateUrl: './visualizar-pregunta.component.html',
  styleUrls: ['./visualizar-pregunta.component.scss']
})
export class VisualizarPreguntaComponent implements OnInit {

  pregunta: Pregunta;

  constructor(@Inject(MAT_DIALOG_DATA) data: any) {
    this.pregunta = data;
  }

  ngOnInit() {
  }

}
