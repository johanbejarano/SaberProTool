import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ContextoService } from 'app/services/contexto.service';
import { EditarContextoComponent } from './editar-contexto/editar-contexto.component';

@Component({
  selector: 'contexto',
  templateUrl: './contexto.component.html',
  styleUrls: ['./contexto.component.scss']
})
export class ContextoComponent implements OnInit {

  moduId: number;
  contId: number;

  contextos: any[];

  ocultar: boolean = false;

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
    private dialogRef: MatDialogRef<ContextoComponent>,
    private dialog: MatDialog,
    private contextoService: ContextoService) {
    this.moduId = data.modulo;
    this.contId = data.contexto;
  }

  ngOnInit() {
    this.getContextos();
  }

  getContextos() {
    this.contextoService.getByModulo(this.moduId).subscribe((result: any[]) => {
      this.contextos = result;
      if (!this.contextos || this.contextos.length == 0) {
        this.editar(null);
      }
    })
  }

  seleccionar(contId: number) {
    this.contId = contId;
  }

  editar(contexto) {
    this.ocultar = true;
    let dialogRef = this.dialog.open(EditarContextoComponent, {
      data: contexto
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getContextos();
      } else {
        if (this.contextos && this.contextos.length > 0) {
          this.ocultar = false;
        } else {
          this.dialogRef.close();
        }
      }
    })
  }

  guardar() {

  }

}
