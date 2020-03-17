import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionPreguntaRoutingModule } from './gestion-pregunta-routing.module';
import { PreguntaComponent } from './pregunta/pregunta.component';
import { PreguntaListComponent } from './pregunta/pregunta-list/pregunta-list.component';

import { TranslateModule } from '@ngx-translate/core';
import { FooterModule } from 'app/layout/components/footer/footer.module';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseWidgetModule, FuseConfirmDialogModule } from '@fuse/components';
import { PreguntaDialogComponent } from './pregunta/pregunta-dialog/pregunta-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatStepperModule } from '@angular/material/stepper';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { CrearPreguntaComponent } from './pregunta/crear-pregunta/crear-pregunta.component';

@NgModule({
  declarations: [PreguntaComponent, PreguntaListComponent, PreguntaDialogComponent, CrearPreguntaComponent],
  imports: [
    CommonModule,

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
    MatStepperModule,
    FooterModule,

    FuseSharedModule,
    FuseWidgetModule,
    FuseConfirmDialogModule,

    GestionPreguntaRoutingModule,
    CKEditorModule
  ],
  entryComponents: [
    PreguntaDialogComponent
  ]
})
export class GestionPreguntaModule { }
