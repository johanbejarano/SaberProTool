import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { FuseConfirmDialogModule, FuseWidgetModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';
import { TranslateModule } from '@ngx-translate/core';
import { FooterModule } from 'app/layout/components/footer/footer.module';
import { GestionPreguntaRoutingModule } from './gestion-pregunta-routing.module';
import { CrearPreguntaComponent } from './pregunta/crear-pregunta/crear-pregunta.component';
import { PreguntaListComponent } from './pregunta/pregunta-list/pregunta-list.component';
import { PreguntaComponent } from './pregunta/pregunta.component';



@NgModule({
  declarations: [PreguntaComponent, PreguntaListComponent, CrearPreguntaComponent],
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
  ]
})
export class GestionPreguntaModule { }
