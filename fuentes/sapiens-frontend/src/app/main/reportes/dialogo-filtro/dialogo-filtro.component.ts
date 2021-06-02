import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MatPaginator, MatTableDataSource, MAT_DIALOG_DATA } from '@angular/material';
import { Modulo } from 'app/domain/modulo';
import { Programa } from 'app/domain/programa';
import { Prueba } from 'app/domain/prueba';
import { FacultadService } from 'app/services/facultad.service';
import { ModuloService } from 'app/services/modulo.service';
import { ProgramaService } from 'app/services/programa.service';
import { PruebaService } from 'app/services/prueba.service';
import { UsuarioService } from 'app/services/usuario.service';
import { global } from 'app/utils/global';
import { Page } from 'app/utils/pagination/page';

@Component({
  selector: 'app-dialogo-filtro',
  templateUrl: './dialogo-filtro.component.html',
  styleUrls: ['./dialogo-filtro.component.scss']
})
export class DialogoFiltroComponent implements OnInit {

  form: FormGroup;

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  pageNumber: number;
  pageSize: number;
  total: Number;

  data: any[];
  datasource: MatTableDataSource<any> = new MatTableDataSource();

  tipo: string;

  displayedColumns: string[];

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
    private dialogRef: MatDialogRef<DialogoFiltroComponent>,
    private formBuilder: FormBuilder,
    private usuarioService: UsuarioService,
    private pruebaService: PruebaService,
    private moduloService: ModuloService,
    private programaService: ProgramaService,
    private facultadService: FacultadService
  ) {
    this.tipo = data;
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      'filtro': ''
    });

    this.pageNumber = 0;
    this.pageSize = 10;

    if (this.tipo == 'usuario') {
      this.displayedColumns = ['usuarioCodigo', 'usuarioIdentificacion', 'usuarioNombre'];
    } else if (this.tipo == 'prueba') {
      this.displayedColumns = ['pruebaId', 'pruebaPropietario', 'pruebaFecha'];
    } else if (this.tipo == 'modulo') {
      this.displayedColumns = ['moduloNombre'];
    } else if (this.tipo == 'programa') {
      this.displayedColumns = ['programa'];
    } else if (this.tipo == 'facultad') {
      this.displayedColumns = ['facultad'];
    }
    this.getData();
  }

  getData() {
    if (this.tipo == 'usuario') {
      this.getUsuarios();
    } else if (this.tipo == 'prueba') {
      this.getPruebas();
    } else if (this.tipo == 'modulo') {
      this.getModulos();
    } else if (this.tipo == 'programa') {
      this.getProgramas();
    } else if (this.tipo == 'facultad') {
      this.getFacultades();
    }
  }

  getUsuarios() {
    const filtro = this.form.controls.filtro.value ? this.form.controls.filtro.value.trim() : '';
    this.usuarioService.getUsuariosPorTipo(global.TIPOS_USUARIO.ESTUDIANTE, -1,filtro, this.pageNumber, this.pageSize).subscribe((page: Page) => {
      let data = page.content;
      this.total = page.totalElements;
      this.datasource = new MatTableDataSource(data);
      this.datasource.paginator = this.paginator;
    });
  }

  getPruebas() {
    this.pruebaService.getPruebasDeUsuarioCreador(this.usuarioService.getUsuario().usuaId).subscribe((pruebas: Prueba[]) => {
      this.data = pruebas;
      this.total = pruebas.length;
      this.datasource = new MatTableDataSource(this.data);
      this.datasource.paginator = this.paginator;
    });
  }

  getModulos() {
    this.moduloService.find().subscribe((modulos: Modulo[]) => {
      this.data = modulos;
      this.total = modulos.length;
      this.datasource = new MatTableDataSource(this.data);
      this.datasource.paginator = this.paginator;
    });
  }

  getProgramas() {
    this.programaService.getAll().subscribe((programas: Programa[]) => {
      this.data = programas;
      this.total = programas.length;
      this.datasource = new MatTableDataSource(this.data);
      this.datasource.paginator = this.paginator;
    });
  }

  getFacultades() {
    this.facultadService.getAll().subscribe((facultades: any[]) => {
      this.data = facultades;
      this.total = facultades.length;
      this.datasource = new MatTableDataSource(this.data);
      this.datasource.paginator = this.paginator;
    });
  }

  seleccionar(item) {
    let result;
    if (this.tipo == 'usuario') {
      result = {
        id: item.usuaId,
        nombre: item.nombre + ' ' + item.apellido
      }
    } else if (this.tipo == 'prueba') {
      result = {
        id: item.prueId,
        nombre: '' + item.prueId
      }
    } else if (this.tipo == 'modulo') {
      result = {
        id: item.moduId,
        nombre: item.nombre
      }
    } else if (this.tipo == 'programa') {
      result = {
        id: item.progId,
        nombre: item.nombre
      }
    } else if (this.tipo == 'facultad') {
      result = {
        id: item.facuId,
        nombre: item.nombre
      }
    }
    this.dialogRef.close(result);
  }

  loadPage(event) {
    if (this.tipo == 'usuario') {
      if (this.pageNumber !== event.pageIndex) {
        this.pageNumber = event.pageIndex;
      }
      if (this.pageSize != event.pageSize) {
        this.pageSize = event.pageSize;
      }
      this.getData();
    }
  }

  filtrar() {
    const filtro = this.form.controls.filtro.value ? this.form.controls.filtro.value.trim().toLowerCase() : '';
    if (this.tipo == 'usuario') {
      this.getUsuarios();
    } else if (this.tipo == 'prueba') {
      
      let dataTmp = this.data;
      dataTmp = dataTmp.filter(prueba => {
        return prueba.prueId.toString().trim().toLowerCase().indexOf(filtro) !== -1 || prueba.nombrePropietario.trim().toLowerCase().indexOf(filtro) !== -1
      });
      this.total = dataTmp.length;
      this.datasource = new MatTableDataSource(dataTmp);
      this.datasource.paginator = this.paginator;
    } else if (this.tipo == 'modulo') {
      let dataTmp = this.data;
      dataTmp = dataTmp.filter(modulo => {
        let filter: string = modulo.nombre;
        return filter.trim().toLowerCase().indexOf(filtro) !== -1
      });
      this.total = dataTmp.length;
      this.datasource = new MatTableDataSource(dataTmp);
      this.datasource.paginator = this.paginator;
    }
  }

}
