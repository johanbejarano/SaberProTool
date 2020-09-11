import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Usuario } from 'app/domain/usuario';
import { UsuarioService } from 'app/services/usuario.service';

@Component({
  selector: 'app-cambiar-clave',
  templateUrl: './cambiar-clave.component.html',
  styleUrls: ['./cambiar-clave.component.scss']
})
export class CambiarClaveComponent implements OnInit {


  form: FormGroup;
  usuario: Usuario;

  constructor(private formBuilder: FormBuilder,
    private usuarioService: UsuarioService,
    private snackBar: MatSnackBar,
    private router: Router) {
    this.usuario = this.usuarioService.getUsuario();
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      claveActual: ['', Validators.compose([Validators.required, Validators.minLength(6), Validators.maxLength(15)])],
      claveNueva: ['', Validators.compose([Validators.required, Validators.minLength(6), Validators.maxLength(15)])]
    });
  }

  onFormSubmit() {
    if (this.form.valid) {
      let request: Usuario = new Usuario();
      
      request.usuaId = this.usuario.usuaId;
      request.password = this.form.controls.claveActual.value;
      request.passwordNueva = this.form.controls.claveNueva.value;
      if(request.password.trim() === request.passwordNueva.trim()){
        this.snackBar.open('Las claves no deben ser iguales', 'x', { verticalPosition: 'top', duration: 10000 });
        return;
      }

      this.usuarioService.cambiarClave(request).subscribe(() => {
        this.snackBar.open('Se ha realizado el cambio de clave', 'x', { verticalPosition: 'top', duration: 10000 });
        this.router.navigate(['/init']);
      });
    }
  }

}
