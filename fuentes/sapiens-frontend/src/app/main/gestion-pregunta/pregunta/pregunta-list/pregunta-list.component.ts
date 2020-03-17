import { Component, OnInit, ViewChild, Input, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { Pregunta } from 'app/domain/pregunta';
import { Usuario } from 'app/domain/usuario';
import { PreguntaService } from 'app/services/pregunta.service';
import { UsuarioService } from 'app/services/usuario.service';
import { locale as espanol } from '../../i18n/es'; 
import { PreguntaDialogComponent } from '../pregunta-dialog/pregunta-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { LocalStorageService } from 'app/services/local-storage.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

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

  constructor(
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private preguntaService: PreguntaService,
    private usuarioService: UsuarioService,
    private localStorage: LocalStorageService,
    private router: Router,
    private snackBar: MatSnackBar,
    ) {

    this.usuario = usuarioService.getUsuario();

  }



  ngOnInit() {

    this.form = this.formBuilder.group({
      
      tipoModulo: [0, Validators.required],
      modulo: [0, Validators.required],
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
    this.subscription = this.preguntaService.getPreguntasPorUsuario(this.usuario.usuaId)
      .subscribe((preguntas: Pregunta[]) => {
        
        this.data = preguntas;
        this.datasource = new MatTableDataSource<Pregunta>(this.data);
        
        this.datasource.paginator = this.paginator;
      }, 
      error => {
        this.snackBar.open(error.error, 'x', {verticalPosition: 'top', duration: 10000});
      });
  }

  seleccionar(item: Pregunta) {
    // let dialogRef = this.dialog.open(PreguntaDialogComponent, {
    //   data: item
    // })

    // dialogRef.afterClosed().subscribe(result => {
    //   if (result) {
    //     this.getData();
    //   }
    // });

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
        let request : Pregunta = new Pregunta();
        request.pregId = pregunta.pregId;
        request.usuCreador = this.usuario.usuaId;
        this.preguntaService.eliminar(request).subscribe(()=>{
          this.getData();
        });
      }
      confirmDialogRef = null;
    });
  }

}
