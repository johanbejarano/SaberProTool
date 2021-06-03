import { Component, OnInit, ViewChild, Input, OnDestroy, OnChanges } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { MatTableDataSource } from '@angular/material/table';
import { Pregunta } from '../../../../domain/pregunta';
import { UsuarioService } from '../../../../services/usuario.service';
import { MatSnackBar } from '@angular/material';
import { Usuario } from '../../../../domain/usuario';
import { global } from 'app/utils/global';
import { PreguntaService } from '../../../../services/pregunta.service';
import { Page } from '../../../../utils/pagination/page';

@Component({
  selector: 'app-lista-preguntas',
  templateUrl: './lista-preguntas.component.html',
  styleUrls: ['./lista-preguntas.component.scss']
})
export class ListaPreguntasComponent implements OnInit, OnDestroy, OnChanges {


  formListaPreguntas: FormGroup;
  subscription: Subscription;
  usuario: Usuario;


  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  pageNumber: number;
  pageSize: number;
  total: Number;
  strBusqueda: string;

  @Input() listaModulos:number[];
  @Input() listaPreguntas: number[]=[];
  preguntas:number[]=[];
  checkboxes: {};

  checkedAll: boolean;

  data: Pregunta[] = [];
  datasource: MatTableDataSource<Pregunta> = new MatTableDataSource<Pregunta>();
  public displayedColumns = ['checkbox', 'pregId', 'nombreModulo', 'descripcion', 'nombreTipoPregunta', 'complejidad'];

  constructor(
    private _formBuilder: FormBuilder,
    private preguntaService: PreguntaService,
    private usuarioService: UsuarioService,
    private snackBar: MatSnackBar,
  ) { 
    this.usuario = usuarioService.getUsuario();
  }

  ngOnInit() {
    this.pageNumber = 0;
    this.pageSize = 10;

    this.actualizarFomulario();
    // this.getData();
    // this.selectAllData();
  }

  ngOnChanges(){
    this.getData();
    this.selectAllData();
  }

  actualizarFomulario() {
    // Reactive Form
    this.formListaPreguntas = this._formBuilder.group({
      busqueda: [this.strBusqueda],
    });
  }

  ngOnDestroy(): void {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }

  selectAllData(){
    if (this.listaModulos.length > 0) {
      
      this.subscription = this.preguntaService.getAllPreguntasPorModulos(this.listaModulos,
        this.formListaPreguntas.controls.busqueda.value).subscribe(d=>{
          if (d) {
            this.preguntas = d;          
          }
        });
      this.checkedAll=false;
    }
  }

  getData() {
    if (this.listaModulos.length > 0) {
      this.subscription = this.preguntaService.getPreguntasPorModulos(this.listaModulos,
        this.formListaPreguntas.controls.busqueda.value,
        this.pageNumber,
        this.pageSize)
        .subscribe((page: Page) => {
  
          this.data = page.content;
          this.total = page.totalElements;
  
          console.log(this.data);
          
  
          this.datasource = new MatTableDataSource<Pregunta>(this.data);
  
          this.checkboxes = {};
          this.data.map(pregunta => {
  
            //Se valida si la pregunta ya estaba seleccionado previamente
            let seleccionado = this.listaPreguntas.indexOf(pregunta.pregId);
  
            this.checkboxes[pregunta.pregId] = seleccionado >= 0;
          });
  
        },
          error => {
            this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
          });
    }

  }

  toogleSelectedUsuario(pregId: number) {

    //Si la pregunta ya estaba seleccionada, se saca de la lista
    if (this.listaPreguntas.length > 0) {
      const idx = this.listaPreguntas.indexOf(pregId);

      if (idx !== -1) {
        this.listaPreguntas.splice(idx, 1);
        //this.localStorage.putInLocal('x', this.listaPreguntas);
        return;
      }
    }

    //Si la pregunta no estaba seleccionada, se mete en la lista
    this.listaPreguntas.push(pregId);  
    // this.localStorage.putInLocal('x', this.listaPreguntas);
  }

  loadPage(event) {
    this.checkedAll = false;
    if (this.pageNumber !== event.pageIndex) {
      this.pageNumber = event.pageIndex;
    }
    if (this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
    }
    this.getData();
  }

  cambioFiltro() {
    this.pageNumber = 0;
    this.pageSize = 10;

    this.getData();
    this.selectAllData();
  }

  selectAll() {
    let checked = !this.checkedAll;
    this.preguntas.filter( d => this.listaPreguntas.push(d) );
    this.preguntas.map(pregunta => {
      
      this.checkboxes[pregunta] = checked;

      //Si la pregunta ya estaba seleccionada
      if (this.listaPreguntas.length > 0) {
        const idx = this.listaPreguntas.indexOf(pregunta);
        
        if (idx !== -1) {

          if (!checked) {
            this.listaPreguntas.splice(idx, 1);   
          }else {
            
          }
          return;
        }
      }
    });
    console.log(this.listaPreguntas);
  }

  

}
