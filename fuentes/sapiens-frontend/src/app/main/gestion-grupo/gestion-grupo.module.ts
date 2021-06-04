import { MatSliderModule } from '@angular/material/slider';
import { FooterModule } from './../../layout/components/footer/footer.module';
import { NgxMatNativeDateModule, NgxMatDatetimePickerModule } from '@angular-material-components/datetime-picker';
import { FuseSharedModule } from './../../../@fuse/shared.module';
import { FuseWidgetModule } from './../../../@fuse/components/widget/widget.module';
import { FuseConfirmDialogModule } from './../../../@fuse/components/confirm-dialog/confirm-dialog.module';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { MatButtonModule, MatDividerModule, MatFormFieldModule, MatIconModule, MatMenuModule, MatTabsModule, MatCheckboxModule, MatRadioModule, MatDatepickerModule, MatPaginatorModule, MatInputModule, MatToolbarModule, MatDialogModule, MatSnackBarModule, MatTableModule, MatSelectModule } from '@angular/material';
import { TranslateModule } from '@ngx-translate/core';
import { EstudianteRoutingModule } from './../estudiante/estudiante-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionGrupoRoutingModule } from './gestion-grupo-routing.module';
import { GrupoComponent } from './grupo/grupo.component';
import { CrearGrupoComponent } from './grupo/crear-grupo/crear-grupo.component';
import { GrupoListComponent } from './grupo/grupo-list/grupo-list.component';


@NgModule({
  declarations: [GrupoComponent, CrearGrupoComponent, GrupoListComponent],
  imports: [
    CommonModule,
    GestionGrupoRoutingModule,
    CommonModule,
    GestionGrupoRoutingModule,

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
    MatSliderModule,
    FooterModule,
    MatTabsModule,
    NgxMatDatetimePickerModule,
    NgxMatNativeDateModule,

    FuseSharedModule,
    FuseWidgetModule,
    FuseConfirmDialogModule,
    CKEditorModule
  ]
})
export class GestionGrupoModule { }
