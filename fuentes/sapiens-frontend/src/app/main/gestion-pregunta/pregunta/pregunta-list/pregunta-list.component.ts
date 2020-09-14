import { Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { Modulo } from 'app/domain/modulo';
import { Pregunta } from 'app/domain/pregunta';
import { Usuario } from 'app/domain/usuario';
import { LocalStorageService } from 'app/services/local-storage.service';
import { ModuloService } from 'app/services/modulo.service';
import { PreguntaService } from 'app/services/pregunta.service';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { locale as espanol } from '../../i18n/es';

@Component({
  selector: 'app-pregunta-list',
  templateUrl: './pregunta-list.component.html',
  styleUrls: ['./pregunta-list.component.scss']
})
export class PreguntaListComponent implements OnInit, OnDestroy {

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @Input() actualizar: boolean;

  data: Pregunta[] = [];
  datasource: MatTableDataSource<Pregunta> = new MatTableDataSource<Pregunta>();
  public displayedColumns = ['idPregunta', 'modulo', 'descripcion'];

  form: FormGroup;
  subscription: Subscription;
  usuario: Usuario;

  modulos: Modulo[] = [];

  constructor(
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private preguntaService: PreguntaService,
    private usuarioService: UsuarioService,
    private localStorage: LocalStorageService,
    private router: Router,
    private snackBar: MatSnackBar,
    private moduloService: ModuloService
  ) {

    this.usuario = usuarioService.getUsuario();

  }



  ngOnInit() {

    this.form = this.formBuilder.group({
      modulo: [''],
      filtro: ['']
    });

    this.getData();
    this.getModulos();

  }

  ngOnDestroy() {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }

  ngOnChanges() {
    this.getData();
  }

  getData() {
    console.log(this.usuario.usuaId);
    
    this.subscription = this.preguntaService.getPreguntasPorUsuario(this.usuario.usuaId)
      .subscribe((preguntas: Pregunta[]) => {
        
        this.data = preguntas;
        console.log(this.data);
        this.datasource = new MatTableDataSource<Pregunta>(this.data);

        this.datasource.paginator = this.paginator;
      },
        error => {
          this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
        });
  }

  getModulos() {
    this.subscription = this.moduloService.findByPrograma(this.usuario.progId)
      .subscribe((modulos: Modulo[]) => {
        this.modulos = modulos;
      });
  }

  seleccionar(item: Pregunta) {
    this.localStorage.putInLocal('idPregunta', item.pregId);

    this.router.navigate(["/gestionPreguntas/registrarPregunta"]);

  }

  onEliminar(pregunta: Pregunta) {
    let confirmDialogRef = this.dialog.open(FuseConfirmDialogComponent, {
      disableClose: false
    });

    confirmDialogRef.componentInstance.confirmMessage = espanol.data.msg.confirmacionEliminarPerfil;

    confirmDialogRef.afterClosed().subscribe(result => {
      if (result) {
        let request: Pregunta = new Pregunta();
        request.pregId = pregunta.pregId;
        request.usuCreador = this.usuario.usuaId;
        this.preguntaService.eliminar(request).subscribe(() => {
          this.getData();
        });
      }
      confirmDialogRef = null;
    });
  }

  applyFilter() {
    let dataTmp = this.data;

    let modulo: string = this.form.controls.modulo.value ? this.form.controls.modulo.value : '';
    let filtro: string = this.form.controls.filtro.value ? this.form.controls.filtro.value.toLowerCase() : '';

    if (modulo) {
      dataTmp = dataTmp.filter(pregunta => {
        return pregunta.moduId_Modulo === +modulo
      });
    }

    if (filtro) {
      let showData = [];
      for (let i = 0; i < dataTmp.length; i++) {
        const pregunta = dataTmp[i];
        let contiene = false;
        if (pregunta.descripcion.toLowerCase().search(filtro) !== -1 || pregunta.codigoUsuario.toLowerCase() === filtro) {
          contiene = true;
        } else {
          if (pregunta.respuestasDTO) {
            for (let j = 0; j < pregunta.respuestasDTO.length; j++) {
              const respuesta = pregunta.respuestasDTO[j];
              if (respuesta.descripcion.toLowerCase().search(filtro) !== -1) {
                contiene = true;
              }
            }
          }
        }
        if (contiene) {
          showData.push(pregunta);
        }
      }
      this.datasource = new MatTableDataSource<Pregunta>(showData);
    } else {
      this.datasource = new MatTableDataSource<Pregunta>(dataTmp);
    }
    this.datasource.paginator = this.paginator;
  }

}
