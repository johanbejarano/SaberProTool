import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UsuarioService } from 'app/services/usuario.service';
import { LocalStorageService } from 'app/services/local-storage.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Usuario } from 'app/domain/usuario';
import { PruebaService } from 'app/services/prueba.service';
import { Prueba } from 'app/domain/prueba';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-prueba-list',
  templateUrl: './prueba-list.component.html',
  styleUrls: ['./prueba-list.component.scss']
})
export class PruebaListComponent implements OnInit, OnDestroy {

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  
  form: FormGroup;
  subscription: Subscription;
  usuario: Usuario;

  data: Prueba[] = [];
  datasource: MatTableDataSource<Prueba> = new MatTableDataSource<Prueba>();

  // public displayedColumns = ['tipoPrueba', 'modulos', 'fechaInicial', 'fechaFinal', 'duracion'];
  public displayedColumns = ['tipoPrueba'];
  
  constructor(
    private formBuilder: FormBuilder,
    private pruebaService: PruebaService,
    private usuarioService: UsuarioService,
    private localStorage: LocalStorageService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) { 
    this.usuario = usuarioService.getUsuario();
  }

  ngOnInit(): void {

    this.form = this.formBuilder.group({
      
      
    });

    this.getData();
  }

  ngOnDestroy() {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }

  getData() {
    this.subscription = this.pruebaService.getPruebasDeUsuarioCreador(this.usuario.usuaId)
      .subscribe((pruebas: Prueba[]) => {
        
        this.data = pruebas;
        this.datasource = new MatTableDataSource<Prueba>(this.data);
        
        this.datasource.paginator = this.paginator;
      }, 
      error => {
        this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
      });
  }

}
