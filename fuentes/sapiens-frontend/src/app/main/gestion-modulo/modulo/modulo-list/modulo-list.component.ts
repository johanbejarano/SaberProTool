import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatPaginator, MatTableDataSource } from '@angular/material';
import { Modulo } from 'app/domain/modulo';
import { Usuario } from 'app/domain/usuario';
import { ModuloService } from 'app/services/modulo.service';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { ModuloDialogComponent } from '../modulo-dialog/modulo-dialog.component';

@Component({
  selector: 'modulo-list',
  templateUrl: './modulo-list.component.html',
  styleUrls: ['./modulo-list.component.scss']
})
export class ModuloListComponent implements OnInit {

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @Input() actualizar: boolean;

  data: Modulo[];
  datasource: MatTableDataSource<Modulo> = new MatTableDataSource<Modulo>();
  public displayedColumns = ['nombre'];

  form: FormGroup;
  subscription: Subscription;
  usuario: Usuario;

  constructor(private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private moduloService: ModuloService,
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
    if (this.form && this.form.valid) {
      this.subscription = this.moduloService.find()
        .subscribe((modulos: Modulo[]) => {
          this.data = modulos;
          this.datasource = new MatTableDataSource<Modulo>(this.data);
          this.datasource.paginator = this.paginator;
        });
    }
  }

  seleccionar(item: Modulo) {
    let dialogRef = this.dialog.open(ModuloDialogComponent, {
      data: item
    })

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getData();
      }
    });
  }

  applyFilter() {
    let dataTmp = this.data;

    let filtro : string = this.form.controls.filtro.value ? this.form.controls.filtro.value : '';
    filtro = filtro.toUpperCase().trim();

    dataTmp = dataTmp.filter(modulo => {
      return modulo.nombre.toUpperCase().search(filtro) != -1
    })

    this.datasource = new MatTableDataSource<Modulo>(dataTmp);
    this.datasource.paginator = this.paginator;
  }

}
