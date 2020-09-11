import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule, MatProgressBarModule, MatSnackBarModule } from '@angular/material';
import { FuseWidgetModule } from '@fuse/components';
import { FuseSharedModule } from '@fuse/shared.module';
import { TranslateModule } from '@ngx-translate/core';
import { AuthRoutingModule } from './auth-routing.module';
import { CambiarClaveComponent } from './cambiar-clave/cambiar-clave.component';
import { LoginComponent } from './login/login.component';
import { RecuperarClaveComponent } from './recuperar-clave/recuperar-clave.component';

@NgModule({
  declarations: [LoginComponent, RecuperarClaveComponent, CambiarClaveComponent],
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

    FuseSharedModule,
    FuseWidgetModule
  ]
})
export class AuthModule { }
