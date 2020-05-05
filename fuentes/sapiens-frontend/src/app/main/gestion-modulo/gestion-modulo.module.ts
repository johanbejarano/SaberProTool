import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatDividerModule, MatFormFieldModule, MatIconModule, MatInputModule, MatMenuModule, MatPaginatorModule, MatRadioModule, MatSelectModule, MatSnackBarModule, MatTableModule, MatToolbarModule } from '@angular/material';
import { FuseConfirmDialogModule, FuseWidgetModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';
import { TranslateModule } from '@ngx-translate/core';
import { FooterModule } from 'app/layout/components/footer/footer.module';
import { GestionModuloRoutingModule } from './gestion-modulo-routing.module';
import { ModuloDialogComponent } from './modulo/modulo-dialog/modulo-dialog.component';
import { ModuloListComponent } from './modulo/modulo-list/modulo-list.component';
import { ModuloComponent } from './modulo/modulo.component';

@NgModule({
  declarations: [ModuloComponent, ModuloListComponent, ModuloDialogComponent],
  imports: [
    CommonModule,
    GestionModuloRoutingModule,
    TranslateModule,

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
    ModuloDialogComponent,
  ]
})
export class GestionModuloModule { }
