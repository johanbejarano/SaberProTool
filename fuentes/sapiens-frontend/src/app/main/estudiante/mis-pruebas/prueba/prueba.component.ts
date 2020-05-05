import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { DetallePruebaUsuario } from 'app/domain/detalle-prueba-usuario';
import { PruebaUsuario } from 'app/domain/prueba-usuario';
import { Respuesta } from 'app/domain/respuesta';
import { Usuario } from 'app/domain/usuario';
import { DetallePruebaUsuarioService } from 'app/services/detalle-prueba-usuario.service';
import { LocalStorageService } from 'app/services/local-storage.service';
import { PruebaUsuarioService } from 'app/services/prueba-usuario.service';
import { UsuarioService } from 'app/services/usuario.service';

@Component({
  selector: 'app-prueba',
  templateUrl: './prueba.component.html',
  styleUrls: ['./prueba.component.scss']
})
export class PruebaComponent implements OnInit {

  pruebaUsuario: PruebaUsuario;
  preguntas: DetallePruebaUsuario[];
  fechaLimite: Date;
  horas: number;
  minutos: number;
  segundos: number;

  usuario: Usuario;

  constructor(private detallePruebaUsuarioService: DetallePruebaUsuarioService,
    private pruebaUsuarioService: PruebaUsuarioService,
    private localStorage: LocalStorageService,
    private usuarioService: UsuarioService,
    private snackBar: MatSnackBar,
    private router: Router,
    private cdRef: ChangeDetectorRef,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.pruebaUsuario = this.localStorage.getFromLocal('pruebaUsuario');
    this.getPreguntas();
    this.fechaLimite = new Date(new Date(this.pruebaUsuario.fechaInicio).getTime() + (this.pruebaUsuario.tiempoDisponible * 60 * 1000));
    this.usuario = this.usuarioService.getUsuario();
    this.actualizarTiempo();
  }

  getPreguntas() {
    this.detallePruebaUsuarioService.getPreguntasByPruebaUsuario(this.pruebaUsuario.prusId).subscribe((preguntas: DetallePruebaUsuario[]) => {
      this.preguntas = preguntas;
    })
  }

  actualizarTiempo() {
    const utc2 = this.fechaLimite.getTime();
    const utc1 = new Date().getTime();
    const segundos = Math.floor((utc2 - utc1) / 1000);
    const minutos = Math.floor((utc2 - utc1) / (1000 * 60));
    const horas = Math.floor((utc2 - utc1) / (1000 * 60 * 60));
    if (segundos > 0 && this.pruebaUsuario.nombreEstadoPrueba !== 'FINALIZADA') {
      this.horas = horas;
      this.minutos = minutos - (horas * 60);
      this.segundos = segundos - (minutos * 60);
      setTimeout(() => this.actualizarTiempo(), 1000);
    } else {
      if (this.pruebaUsuario.nombreEstadoPrueba == 'INICIADA') {
        let request: PruebaUsuario = new PruebaUsuario();
        request.prusId = this.pruebaUsuario.prusId;
        request.usuCreador = this.usuario.usuaId;
        this.pruebaUsuarioService.finalizarPrueba(request).subscribe(() => {
          this.pruebaUsuarioService.getPruebas(this.usuario.usuaId, -1).subscribe((pruebas: PruebaUsuario[]) => {
            let prueba = pruebas[0];
            this.pruebaUsuario = prueba;
            this.localStorage.putInLocal('pruebaUsuario', prueba);
            this.snackBar.open('La prueba ha finalizado', 'x', { verticalPosition: 'top', duration: 100000 });
            this.getPreguntas();
          }, error => {
            this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
          });
        });
      }
      this.horas = 0;
      this.minutos = 0;
      this.segundos = 0;
    }

  }

  seleccionarRespuesta(pregunta: DetallePruebaUsuario, respuesta: Respuesta) {
    if (respuesta) {
      if (pregunta.respId !== respuesta.respId) {
        pregunta.respId = respuesta.respId;
        this.guardarRespuesta(pregunta);
      }
    }
  }

  guardarRespuesta(pregunta: DetallePruebaUsuario) {
    if (pregunta.respId) {
      let request: DetallePruebaUsuario = new DetallePruebaUsuario();
      request.dpruId = pregunta.dpruId;
      request.respId = pregunta.respId;
      request.usuCreador = this.usuario.usuaId;
      this.detallePruebaUsuarioService.responder(request).subscribe(() => {
      });
    }
  }

  finalizarPrueba() {
    if (this.preguntas) {
      for (let i = 0; i < this.preguntas.length; i++) {
        const pregunta = this.preguntas[i];
        if (!pregunta.respId) {
          this.snackBar.open('Se deben responder todas las preguntas', 'x', { verticalPosition: 'top', duration: 10000 });
          return;
        }
      }

      let confirmDialogRef = this.dialog.open(FuseConfirmDialogComponent, {
        disableClose: false
      });

      confirmDialogRef.componentInstance.confirmMessage = '¿Está seguro de finalizar la prueba?';

      confirmDialogRef.afterClosed().subscribe(result => {
        if (result) {
          let request: PruebaUsuario = new PruebaUsuario();
          request.prusId = this.pruebaUsuario.prusId;
          request.usuCreador = this.usuario.usuaId;
          this.pruebaUsuarioService.finalizarPrueba(request).subscribe(() => {
            this.pruebaUsuarioService.getPruebas(this.usuario.usuaId, -1).subscribe((pruebas: PruebaUsuario[]) => {
              let prueba = pruebas[0];
              this.pruebaUsuario = prueba;
              this.localStorage.putInLocal('pruebaUsuario', prueba);
              this.snackBar.open('La prueba ha finalizado', 'x', { verticalPosition: 'top', duration: 100000 });
              this.getPreguntas();
            }, error => {
              this.snackBar.open(error.error, 'x', { verticalPosition: 'top', duration: 10000 });
            });
          });
        }
        confirmDialogRef = null;
      });
    }
  }

  pausarPrueba() {
    if (this.preguntas) {
      let confirmDialogRef = this.dialog.open(FuseConfirmDialogComponent, {
        disableClose: false
      });

      confirmDialogRef.componentInstance.confirmMessage = '¿Está seguro de pausar la prueba?';

      confirmDialogRef.afterClosed().subscribe(result => {
        if (result) {
          let request: PruebaUsuario = new PruebaUsuario();
          request.prusId = this.pruebaUsuario.prusId;
          request.usuCreador = this.usuario.usuaId;
          this.pruebaUsuarioService.pausarPrueba(request).subscribe(() => {
            this.snackBar.open('Se ha pausado la prueba', 'x', { verticalPosition: 'top', duration: 100000 })
            this.router.navigate(['/estudiante']);
          });
        }
        confirmDialogRef = null;
      });
    }
  }

}
