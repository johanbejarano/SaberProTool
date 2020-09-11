import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';
import { FuseConfigService } from '@fuse/services/config.service';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { FuseNavigation } from '@fuse/types';
import { Usuario } from 'app/domain/usuario';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { locale as espanol } from '../i18n/es';

@Component({
  selector: 'app-recuperar-clave',
  templateUrl: './recuperar-clave.component.html',
  styleUrls: ['./recuperar-clave.component.scss'],
  encapsulation: ViewEncapsulation.None,
  animations: fuseAnimations
})
export class RecuperarClaveComponent implements OnInit {

  loginForm: FormGroup;
  subscription: Subscription;
  bloquear: boolean = false;
  navigation: FuseNavigation[];
  /**
   * Constructor
   *
   * @param {FuseConfigService} _fuseConfigService
   * @param {FormBuilder} _formBuilder
   */
  constructor(
    private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private _fuseConfigService: FuseConfigService,
    private _formBuilder: FormBuilder,
    private usuarioService: UsuarioService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this._fuseConfigService.config = {
      layout: {
        navbar: { hidden: true },
        toolbar: { hidden: true },
        footer: { hidden: true },
        sidepanel: { hidden: true }
      }
    };
    this._fuseTranslationLoaderService.loadTranslations(espanol);
  }

  // -----------------------------------------------------------------------------------------------------
  // @ Lifecycle hooks
  // -----------------------------------------------------------------------------------------------------

  /**
   * On init
   */
  ngOnInit(): void {
    this.loginForm = this._formBuilder.group({
      username: ['', Validators.required]
    });
  }

  solicitarClave(): void {
    if (this.loginForm.valid) {
      this.bloquear = true;

      this.subscription = this.usuarioService.solicitarClave(this.loginForm.controls.username.value).subscribe((usuario: Usuario) => {
        this.loginForm.controls.username.reset();
        if (usuario.correo) {
          this.snackBar.open('Se ha enviado un email de confirmación al correo electrónico ' + usuario.correo, 'x', { verticalPosition: 'top', duration: 10000 });
        }
        this.router.navigate(['/auth/login']);

        this.bloquear = false;
      }, error => {
        this.snackBar.open(error, 'x', { verticalPosition: 'top', duration: 10000 });
        this.bloquear = false;
      });
    }
  }

}
