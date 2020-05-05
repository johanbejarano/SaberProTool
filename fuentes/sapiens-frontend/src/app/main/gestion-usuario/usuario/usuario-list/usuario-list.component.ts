import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatPaginator, MatTableDataSource } from '@angular/material';
import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { Usuario } from 'app/domain/usuario';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { locale as espanol } from '../../i18n/es';
import { UsuarioDialogComponent } from '../usuario-dialog/usuario-dialog.component';

@Component({
  selector: 'usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.scss']
})
export class UsuarioListComponent implements OnInit {

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @Input() actualizar: boolean;

  data: Usuario[] = [];
  datasource: MatTableDataSource<Usuario> = new MatTableDataSource<Usuario>();
  public displayedColumns = ['usuario', 'perfil', 'accion'];

  form: FormGroup;
  subscription: Subscription;
  usuario: Usuario;

  constructor(private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private usuarioService: UsuarioService) {
      this.usuario = usuarioService.getUsuario();
    }

  ngOnInit() {
    this.form = this.formBuilder.group({
      'filtro': ''
    });

    this.getData();
  }

  ngOnDestroy() {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }
  
  ngOnChanges() {
    this.getData();
  }

  getData() {
    this.subscription = this.usuarioService.find()
      .subscribe((roles: Usuario[]) => {
        this.data = roles;
        this.datasource = new MatTableDataSource<Usuario>(this.data);
        this.datasource.paginator = this.paginator;
      });
  }

  seleccionar(item: Usuario) {
    let dialogRef = this.dialog.open(UsuarioDialogComponent, {
      data: item
    })

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getData();
      }
    });
  }

  applyFilter() {
    let filtro: string = this.form.controls.filtro.value;
    if (!filtro) {
      filtro = '';
    } else {
      filtro = filtro.trim().toUpperCase();
    }
    this.datasource.data = this.data.filter(item => (item.nombre + ' ' + item.apellido).toUpperCase().search(filtro) != -1 || item.perfil.toUpperCase().search(filtro) != -1);
    this.paginator.firstPage();
  }

  onEliminar(usuario: Usuario) {
    let confirmDialogRef = this.dialog.open(FuseConfirmDialogComponent, {
      disableClose: false
    });

    confirmDialogRef.componentInstance.confirmMessage = espanol.data.msg.confirmacionEliminarPerfil;

    confirmDialogRef.afterClosed().subscribe(result => {
      if (result) {
        let request : Usuario = new Usuario();
        request.usuaId = usuario.usuaId;
        request.usuCreador = this.usuario.usuaId;
        this.usuarioService.eliminar(request).subscribe(()=>{
          this.getData();
        });
      }
      confirmDialogRef = null;
    });
  }

}
