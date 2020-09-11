import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatDividerModule, MatFormFieldModule, MatIconModule, MatInputModule, MatMenuModule, MatPaginatorModule, MatRadioModule, MatSelectModule, MatSnackBarModule, MatTableModule, MatToolbarModule } from '@angular/material';
import { FuseConfirmDialogModule, FuseWidgetModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';
import { TranslateModule } from '@ngx-translate/core';
import { FooterModule } from 'app/layout/components/footer/footer.module';
import { GestionUsuarioRoutingModule } from './gestion-usuario-routing.module';
import { UsuarioDialogComponent } from './usuario/usuario-dialog/usuario-dialog.component';
import { UsuarioListComponent } from './usuario/usuario-list/usuario-list.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { CargarArchivoComponent } from './cargar-archivo/cargar-archivo.component';


@NgModule({
  declarations: [UsuarioComponent, UsuarioListComponent, UsuarioDialogComponent, CargarArchivoComponent],
  imports: [
    CommonModule,
    GestionUsuarioRoutingModule,
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
    UsuarioDialogComponent,
  ]
})
export class GestionUsuarioModule { }
