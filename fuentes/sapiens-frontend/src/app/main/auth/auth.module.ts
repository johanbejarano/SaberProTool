import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { TranslateModule } from '@ngx-translate/core';
import { MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule, MatSnackBarModule, MatProgressBarModule } from '@angular/material';
import { FuseSharedModule } from '@fuse/shared.module';


@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    TranslateModule,
    AuthRoutingModule,

    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatSnackBarModule,
    MatProgressBarModule,

    FuseSharedModule
  ]
})
export class AuthModule { }
