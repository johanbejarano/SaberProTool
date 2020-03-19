import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionPruebaRoutingModule } from './gestion-prueba-routing.module';
import { PruebaComponent } from './prueba/prueba.component';
import { PruebaListComponent } from './prueba/prueba-list/prueba-list.component';
import { CrearPruebaComponent } from './prueba/crear-prueba/crear-prueba.component';

import { TranslateModule } from '@ngx-translate/core';
import { FooterModule } from 'app/layout/components/footer/footer.module';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseWidgetModule, FuseConfirmDialogModule } from '@fuse/components';
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
import {MatSliderModule} from '@angular/material/slider';
import { ListaEstudiantesComponent } from './prueba/lista-estudiantes/lista-estudiantes.component';



@NgModule({
  declarations: [PruebaComponent, PruebaListComponent, CrearPruebaComponent, ListaEstudiantesComponent],
  imports: [
    CommonModule,
    GestionPruebaRoutingModule,

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

    FuseSharedModule,
    FuseWidgetModule,
    FuseConfirmDialogModule,
  ]
})
export class GestionPruebaModule { }
