import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Contexto } from 'app/domain/contexto';
import { ContextoService } from 'app/services/contexto.service';
import { EditarContextoComponent } from './editar-contexto/editar-contexto.component';

@Component({
  selector: 'contexto',
  templateUrl: './contexto.component.html',
  styleUrls: ['./contexto.component.scss']
})
export class ContextoComponent implements OnInit {

  moduId: number;
  contextoSelected: Contexto;

  contextos: Contexto[];

  ocultar: boolean = false;

  constructor(@Inject(MAT_DIALOG_DATA) data: any,
    private dialogRef: MatDialogRef<ContextoComponent>,
    private dialog: MatDialog,
    private contextoService: ContextoService) {
    this.moduId = data.modulo;
    this.contextoSelected = data.contexto;
  }

  ngOnInit() {
    this.getContextos();
  }

  getContextos() {
    this.contextoService.getByModulo(this.moduId).subscribe((result: Contexto[]) => {
      this.contextos = result;
      if (!this.contextos || this.contextos.length == 0) {
        this.nuevo();
      }
    })
  }

  seleccionar(contexto: Contexto) {
    if (this.contextoSelected.contId == contexto.contId) {
      this.contextoSelected = new Contexto();
    } else {
      this.contextoSelected = contexto;
    }
  }

  nuevo() {
    let contextoTmp = new Contexto();
    contextoTmp.moduId = this.moduId;
    this.editar(contextoTmp);
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
    this.dialogRef.close(this.contextoSelected);
  }

}
