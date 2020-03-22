import { Component, OnInit, OnDestroy, ViewChild, Input } from '@angular/core';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UsuarioService } from 'app/services/usuario.service';
import { LocalStorageService } from 'app/services/local-storage.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Usuario } from 'app/domain/usuario';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { fuseAnimations } from '@fuse/animations';
import { Page } from 'app/utils/pagination/page';
import { global } from 'app/utils/global';
import { listenerCount } from 'cluster';

@Component({
  selector: 'app-lista-estudiantes',
  templateUrl: './lista-estudiantes.component.html',
  styleUrls: ['./lista-estudiantes.component.scss'],
  animations   : fuseAnimations
})
export class ListaEstudiantesComponent implements OnInit, OnDestroy {

  formListaEstudiantes: FormGroup;
  subscription: Subscription;
  usuario: Usuario;
  

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  pageNumber: number;
  pageSize: number;
  total: number;
  strBusqueda: string;
  
  data: Usuario[] = [];

  @Input() usuariosSeleccionados;
  checkboxes: {};

  checkedAll: boolean;

  datasource: MatTableDataSource<Usuario> = new MatTableDataSource<Usuario>();
  public displayedColumns = ['checkbox', 'codigo', 'identificacion', 'nombre', 'email'];
  
  constructor(
    private dialog: MatDialog,
    private _formBuilder: FormBuilder,
    private usuarioService: UsuarioService,
    private localStorage: LocalStorageService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) { 
    this.usuario = usuarioService.getUsuario();
  }

  ngOnInit(): void {
    this.pageNumber=0;
    this.pageSize = 10;
    
    this.actualizarFomulario();

    this.getData();
  }

  actualizarFomulario(){
    // Reactive Form
    this.formListaEstudiantes = this._formBuilder.group({
      busqueda: [this.strBusqueda],
    });
  }

  ngOnDestroy(): void {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }

  getData() {
    
    this.subscription = this.usuarioService.getUsuariosPorTipo(
      global.VARIABLES.TIPO_USUARIO_ESTUDIANTE, 
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

            this.checkboxes[usuario.usuaId] = seleccionado>=0;
        });

      }, 
      error => {
        this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
      });
  }

  toogleSelectedUsuario(usuaId: number){

    //Si el usuario ya estaba seleccionado, se saca de la lista
    if (this.usuariosSeleccionados.length >0 ){
      const idx = this.usuariosSeleccionados.indexOf(usuaId);

      if (idx !== -1){
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

  cambioFiltro(){
    this.pageNumber=0;
    this.pageSize = 10;
    
    this.getData();
  }

  selectAll(){
    
    let checked = !this.checkedAll;
    
    this.data.map(usuario => {
        
        this.checkboxes[usuario.usuaId] = checked;

        //Si el usuario ya estaba seleccionado
        if (this.usuariosSeleccionados.length >0 ){
          const idx = this.usuariosSeleccionados.indexOf(usuario.usuaId);

          if (idx !== -1){
            
            if (!checked){
              this.usuariosSeleccionados.splice(idx, 1);
            }else{
              //this.usuariosSeleccionados.push(usuario.usuaId);
            }
            //this.localStorage.putInLocal('x', this.usuariosSeleccionados);
            return;
          }
        }

        this.usuariosSeleccionados.push(usuario.usuaId);
        // this.localStorage.putInLocal('x', this.usuariosSeleccionados);
    });


  }

}
