import { NgModule } from '@angular/core';
import { MatButtonModule, MatDialogModule } from '@angular/material';

import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { TranslateModule } from '@ngx-translate/core';
import { FlexModule } from '@angular/flex-layout';

@NgModule({
    declarations: [
        FuseConfirmDialogComponent
    ],
    imports: [
        MatDialogModule,
        MatButtonModule,
        TranslateModule,
        FlexModule
    ],
    entryComponents: [
        FuseConfirmDialogComponent
    ],
})
export class FuseConfirmDialogModule
{
}
