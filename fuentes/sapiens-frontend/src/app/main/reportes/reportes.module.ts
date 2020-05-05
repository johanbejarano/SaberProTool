import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatDividerModule, MatFormFieldModule, MatIconModule, MatInputModule, MatMenuModule, MatPaginatorModule, MatRadioModule, MatSelectModule, MatSnackBarModule, MatTableModule, MatToolbarModule } from '@angular/material';
import { FuseConfirmDialogModule, FuseWidgetModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';
import { FooterModule } from 'app/layout/components/footer/footer.module';
import { DialogoFiltroComponent } from './dialogo-filtro/dialogo-filtro.component';
import { ReporteConFiltrosComponent } from './reporte-con-filtros/reporte-con-filtros.component';
import { ReportesRoutingModule } from './reportes-routing.module';
import { ReportesComponent } from './reportes.component';



@NgModule({
  declarations: [ReportesComponent, ReporteConFiltrosComponent, DialogoFiltroComponent],
  imports: [
    CommonModule,
    ReportesRoutingModule,

    MatButtonModule,
    MatDividerModule,
    MatFormFieldModule,
    MatIconModule,
    MatMenuModule,
    MatSelectModule,
    MatTableModule,
    MatSnackBarModule,
    MatDialogModule,
    MatToolbarModule,
    MatInputModule,
    MatPaginatorModule,
    MatDatepickerModule,
    MatRadioModule,
    MatCheckboxModule,
    FooterModule,

    FuseSharedModule,
    FuseWidgetModule,
    FuseConfirmDialogModule,
  ], entryComponents: [
    DialogoFiltroComponent
  ]
})
export class ReportesModule { }
