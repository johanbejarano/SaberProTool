import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatPaginator, MatTableDataSource } from '@angular/material';
import { Usuario } from 'app/domain/usuario';
import { UsuarioService } from 'app/services/usuario.service';
import { Page } from 'app/utils/pagination/page';
import { Subscription } from 'rxjs';
import { UsuarioDialogComponent } from '../usuario-dialog/usuario-dialog.component';

@Component({
  selector: 'usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.scss']
})
export class UsuarioListComponent implements OnInit {

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @Input() actualizar: boolean;

  pageNumber: number;
  pageSize: number;
  total: Number;

  datasource: MatTableDataSource<Usuario> = new MatTableDataSource<Usuario>();
  public displayedColumns = ['codigo', 'identificacion', 'nombre', 'email', 'tipoUsuario'];

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

    this.pageNumber = 0;
    this.pageSize = 10;
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
    if (this.form && this.form.valid) {
      this.subscription = this.usuarioService.getUsuariosPorTipo(-1, -1, -1, -1, this.form.controls.filtro.value, this.pageNumber, this.pageSize)
        .subscribe((page: Page) => {
          let data = page.content;
          this.total = page.totalElements;
          this.datasource = new MatTableDataSource<Usuario>(data);
        });
    }
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

  loadPage(event) {
    if (this.pageNumber !== event.pageIndex) {
      this.pageNumber = event.pageIndex;
    }
    if (this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
    }
    this.getData();
  }

  applyFilter() {
    this.pageNumber = 0;
    this.paginator.pageIndex = 0;

    this.getData();
  }

}
