import { CommonModule, DatePipe } from '@angular/common';
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
import { MatSliderModule } from '@angular/material/slider';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FuseConfirmDialogModule, FuseWidgetModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';
import { TranslateModule } from '@ngx-translate/core';
import { FooterModule } from 'app/layout/components/footer/footer.module';
import { EstudianteRoutingModule } from './estudiante-routing.module';
import { MisPruebasComponent } from './mis-pruebas/mis-pruebas.component';
import { PruebaListComponent } from './mis-pruebas/prueba-list/prueba-list.component';
import { PruebaComponent } from './mis-pruebas/prueba/prueba.component';
import { ResumenPruebaComponent } from './mis-pruebas/resumen-prueba/resumen-prueba.component';

@NgModule({
  declarations: [MisPruebasComponent, PruebaListComponent, ResumenPruebaComponent, PruebaComponent],
  imports: [
    CommonModule,
    EstudianteRoutingModule,

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
    MatStepperModule,

    FuseSharedModule,
    FuseWidgetModule,
    FuseConfirmDialogModule,
  ], providers: [
    DatePipe
  ]
})
export class EstudianteModule { }