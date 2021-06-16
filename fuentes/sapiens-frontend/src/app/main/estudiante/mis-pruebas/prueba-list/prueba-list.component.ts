import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { Prueba } from 'app/domain/prueba';
import { PruebaUsuario } from 'app/domain/prueba-usuario';
import { Usuario } from 'app/domain/usuario';
import { LocalStorageService } from 'app/services/local-storage.service';
import { PruebaUsuarioService } from 'app/services/prueba-usuario.service';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { locale as espanol } from '../../i18n/es';

@Component({
  selector: 'prueba-list',
  templateUrl: './prueba-list.component.html',
  styleUrls: ['./prueba-list.component.scss']
})
export class PruebaListComponent implements OnInit {

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  form: FormGroup;
  subscription: Subscription;
  usuario: Usuario;

  data: Prueba[] = [];
  datasource: MatTableDataSource<Prueba> = new MatTableDataSource<Prueba>();

  // public displayedColumns = ['tipoPrueba', 'modulos', 'fechaInicial', 'fechaFinal', 'duracion'];
  public displayedColumns = ['prueId', 'nombreTipoPrueba', 'fechaInicial', 'fechaFinal', 'duracion', 'propietario', 'estado'];

  constructor(
    private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private formBuilder: FormBuilder,
    private pruebaUsuarioService: PruebaUsuarioService,
    private usuarioService: UsuarioService,
    private localStorage: LocalStorageService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
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
    this.subscription = this.pruebaUsuarioService.getPruebas(this.usuario.usuaId, -1).subscribe((pruebas: Prueba[]) => {
      console.log(pruebas);
      
      this.data = pruebas;
      this.datasource = new MatTableDataSource<Prueba>(this.data);
      this.datasource.paginator = this.paginator;
    }, error => {
      this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
    });
  }

  seleccionar(item: PruebaUsuario) {
    console.log(item);
    this.localStorage.putInLocal('pruebaUsuario', item);
    this.router.navigate(["/estudiante/resumenPrueba"]);
  }

}
