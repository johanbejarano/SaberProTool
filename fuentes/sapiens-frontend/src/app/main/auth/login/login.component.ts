import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { fuseAnimations } from '@fuse/animations';
import { FuseNavigationService } from '@fuse/components/navigation/navigation.service';
import { FuseConfigService } from '@fuse/services/config.service';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { FuseNavigation } from '@fuse/types';
import { Usuario } from 'app/domain/usuario';
import { navigation } from 'app/navigation/navigation';
import { LocalStorageService } from 'app/services/local-storage.service';
import { UsuarioService } from 'app/services/usuario.service';
import { Subscription } from 'rxjs';
import { global } from '../../../utils/global';
import { locale as espanol } from '../i18n/es';


@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations: fuseAnimations
})
export class LoginComponent implements OnInit {
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
        private _fuseNavigationService: FuseNavigationService,
        private _fuseConfigService: FuseConfigService,
        private _formBuilder: FormBuilder,
        private usuarioService: UsuarioService,
        private snackBar: MatSnackBar,
        private localStorage: LocalStorageService,
        private router: Router
    ) {
        this.navigation = navigation;
        // Configure the layout
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
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
    }

    autenticar(): void {
        if (this.loginForm.valid) {
            this.bloquear = true;
            let parametro = new Usuario();

            parametro.codigo = this.loginForm.controls.username.value;
            parametro.password = this.loginForm.controls.password.value;

            this.subscription = this.usuarioService.login(parametro).subscribe((usuario: Usuario) => {
                console.log(usuario.tiusId_TipoUsuario);
                if (usuario.tiusId_TipoUsuario == 1) {
                    this.router.navigate(['/gestionPreguntas']);
                    this._fuseNavigationService.setCurrentNavigation('profesor');
                } else if (usuario.tiusId_TipoUsuario == 3) {
                    this.router.navigate(['/estudiante']);
                    this._fuseNavigationService.setCurrentNavigation('estudiante');
                } else {
                    this.router.navigate(['/init']);
                    this._fuseNavigationService.setCurrentNavigation('estudiante');
                }

                this.localStorage.putInLocal(global.SESSION_ITEMS.USUARIO, usuario);
                this.bloquear = false;
            }, error => {
                this.snackBar.open('No se encuentra el usuario. Intente de nuevo', 'x', { verticalPosition: 'top', duration: 10000 });
                this.bloquear = false;
            });
        }
    }
}