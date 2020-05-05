import { NgModule } from '@angular/core';
import { ObservationDialogComponent } from './observation-dialog.component';
import { MatDialogModule, MatButtonModule, MatFormFieldModule, MatInputModule, MatDividerModule, MatIconModule } from '@angular/material';
import { TranslateModule } from '@ngx-translate/core';
import { CommonModule } from '@angular/common';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseWidgetModule } from '../widget/widget.module';

@NgModule({
  declarations: [
    ObservationDialogComponent
  ],
  imports: [
    CommonModule,
    TranslateModule,

    MatButtonModule,
    MatDividerModule,
    MatFormFieldModule,
    MatIconModule,
    MatDialogModule,
    MatInputModule,
    
    FuseSharedModule,
    FuseWidgetModule
  ], entryComponents: [
    ObservationDialogComponent
  ]
})
export class ObservationDialogModule { }
