import { Component, Input, OnChanges, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';
import { Usuario } from 'app/domain/usuario';
import { LocalStorageService } from 'app/services/local-storage.service';
import { UsuarioService } from 'app/services/usuario.service';
import { global } from 'app/utils/global';
import { Page } from 'app/utils/pagination/page';
import { Subscription } from 'rxjs';
import { FacultadService } from '../../../../services/facultad.service';
import { Facultad } from '../../../../domain/facultad';
import { map, filter } from 'rxjs/operators';

@Component({
  selector: 'app-lista-estudiantes',
  templateUrl: './lista-estudiantes.component.html',
  styleUrls: ['./lista-estudiantes.component.scss'],
  animations: fuseAnimations
})
export class ListaEstudiantesComponent implements OnInit, OnDestroy, OnChanges {

  formListaEstudiantes: FormGroup;
  subscription: Subscription;
  usuario: Usuario;


  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  pageNumber: number;
  pageSize: number;
  total: Number;
  strBusqueda: string;

  data: Usuario[] = [];

  @Input() usuariosSeleccionados: number[]=[];
  usuarios:number[]=[];
  checkboxes: {};

  checkedAll: boolean;

  listFacultad: Facultad[];

  datasource: MatTableDataSource<Usuario> = new MatTableDataSource<Usuario>();
  public displayedColumns = ['checkbox', 'codigo', 'identificacion', 'nombre', 'email'];

  constructor(
    private dialog: MatDialog,
    private _formBuilder: FormBuilder,
    private usuarioService: UsuarioService,
    private facultadService: FacultadService,
    private localStorage: LocalStorageService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {
    this.usuario = usuarioService.getUsuario();
  }

  ngOnInit(): void {
    this.pageNumber = 0;
    this.pageSize = 10;

    this.actualizarFomulario();

    this.getData();
    this.getFacultad();
    this.selectAllData();
  }

  ngOnChanges(){
    this.pageNumber = 0;
    this.pageSize = 10;
    let idPrueba = this.localStorage.getFromLocal('idPrueba');
    this.actualizarFomulario();
    if (idPrueba) {
      this.getData();
      this.selectAllData();
    }
  }

  actualizarFomulario() {
    // Reactive Form
    this.formListaEstudiantes = this._formBuilder.group({
      busqueda: [this.strBusqueda],
      'facultad': '',
    });
  }

  ngOnDestroy(): void {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }

  getFacultad(){
    this.facultadService.getAll().subscribe(d=>{
      if (d) {        
        this.listFacultad = d.slice();
      }

    });
  }

  selectAllData(){
    this.subscription = this.usuarioService.getAllUsuariosPorTipo(global.TIPOS_USUARIO.ESTUDIANTE,
      -1,//semestre
      -1,//programa
      -1,//grupo
      this.formListaEstudiantes.controls.facultad.value,
      this.formListaEstudiantes.controls.busqueda.value).subscribe(d=>{
        if (d) {
          this.usuarios = d;
          // d.filter(data=>this.usuarios.push(data));
          // console.log(this.usuarios);
        }
      });
    this.checkedAll=false;
  }

  getData() {
    this.subscription = this.usuarioService.getUsuariosPorTipo(
      global.TIPOS_USUARIO.ESTUDIANTE,
      -1,//semestre
      -1,//programa
      -1,//grupo
      this.formListaEstudiantes.controls.facultad.value,
      this.formListaEstudiantes.controls.busqueda.value,
      this.pageNumber,
      this.pageSize)
      .subscribe((page: Page) => {

        this.data = page.content;
        this.total = page.totalElements;

        this.datasource = new MatTableDataSource<Usuario>(this.data);

        this.checkboxes = {};
        this.data.map(usuario => {

          //Se valida si el usuario ya estaba seleccionado previamente
          let seleccionado = this.usuariosSeleccionados.indexOf(usuario.usuaId);

          this.checkboxes[usuario.usuaId] = seleccionado >= 0;
        });

      },
        error => {
          this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
        });
  }

  toogleSelectedUsuario(usuaId: number) {

    //Si el usuario ya estaba seleccionado, se saca de la lista
    if (this.usuariosSeleccionados.length > 0) {
      const idx = this.usuariosSeleccionados.indexOf(usuaId);

      if (idx !== -1) {
        this.usuariosSeleccionados.splice(idx, 1);
        //this.localStorage.putInLocal('x', this.usuariosSeleccionados);
        return;
      }
    }

    //Si el usuario no estaba seleccionado, se mete en la lista
    this.usuariosSeleccionados.push(usuaId);
    // this.localStorage.putInLocal('x', this.usuariosSeleccionados);
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
    this.usuarios.filter( d => this.usuariosSeleccionados.push(d) );
    this.usuarios.map(usuario => {
      
      this.checkboxes[usuario] = checked;

      //Si el usuario ya estaba seleccionado
      if (this.usuariosSeleccionados.length > 0) {
        const idx = this.usuariosSeleccionados.indexOf(usuario);
        // console.log(this.usuariosSeleccionados);
        // console.log(usuario);
        // console.log(idx);
        
        if (idx !== -1) {

          if (!checked) {
            this.usuariosSeleccionados.splice(idx, 1);   
          }else {
            //this.usuariosSeleccionados.push(usuario.usuaId);
          }
          //this.localStorage.putInLocal('x', this.usuariosSeleccionados);
          return;
        }
      }

      // this.usuariosSeleccionados.push(usuario.usuaId);
      // this.localStorage.putInLocal('x', this.usuariosSeleccionados);
    });
  }

}
