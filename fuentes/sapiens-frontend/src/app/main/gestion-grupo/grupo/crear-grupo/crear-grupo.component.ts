import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Grupo } from 'app/domain/grupo';
import { Usuario } from 'app/domain/usuario';
import { GrupoService } from 'app/services/grupo.service';
import { LocalStorageService } from 'app/services/local-storage.service';
import { UsuarioService } from 'app/services/usuario.service';

@Component({
  selector: 'app-crear-grupo',
  templateUrl: './crear-grupo.component.html',
  styleUrls: ['./crear-grupo.component.scss']
})
export class CrearGrupoComponent implements OnInit {

  usuario: Usuario;
  grupo: Grupo;

  idGrupo : number;
  usuariosSeleccionados: number[] = [];
  form: FormGroup;

  constructor(
    private localStorage: LocalStorageService,
    private usuarioService: UsuarioService,
    private grupoService: GrupoService,
    private _formBuilder: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {
    this.form = this._formBuilder.group({
      nombre:["",Validators.required],
      descripcion: ["",Validators.required],
    });
  }

  ngOnInit() {
    this.idGrupo = this.localStorage.getFromLocal('idGrupo');
    this.usuario = this.usuarioService.getUsuario();

    if(this.idGrupo){

      this.grupoService.findById(this.idGrupo).subscribe(d=>{
        if(d){
          this.grupo = d;
          this.form.controls.nombre.setValue(this.grupo.nombre);
          this.form.controls.descripcion.setValue(this.grupo.descripcion);
        
        }
      })

      this.usuarioService.getUsuariosPorGrupo(this.idGrupo).subscribe(d=>{
        if(d){
          console.log(d);
          for (let index = 0; index < d.length; index++) {
            this.usuariosSeleccionados.push(d[index].usuaId);
          }
        }
      })
    }

    
  }

  guardarGrupo(){

    if(this.idGrupo){
      if(this.form.valid){
        console.log('actualizar');
        console.log(this.usuariosSeleccionados);

        let grupo: Grupo = new Grupo();
        grupo.grupId = this.idGrupo;
        grupo.usuModificador = this.usuario.usuaId;
        grupo.listUsuarios = this.usuariosSeleccionados;
        grupo.nombre = this.form.controls.nombre.value;
        grupo.descripcion = this.form.controls.descripcion.value;
        
        this.grupoService.actualizarGrupo(grupo).subscribe(d=>{
          if(d){
            console.log(d);
            this.snackBar.open('Se ha actualizado correctamente el grupo', 'x', { verticalPosition: 'top', duration: 10000 });
            this.router.navigate(["/gestionGrupos"]);
          }
        })

      }
    }else{
      if(this.form.valid){
        
        console.log('guardar');
        console.log(this.usuariosSeleccionados);
        let grupo: Grupo = new Grupo();
        grupo.listUsuarios = this.usuariosSeleccionados;
        grupo.nombre = this.form.controls.nombre.value;
        grupo.descripcion = this.form.controls.descripcion.value;
        grupo.usuCreador = this.usuario.usuaId;

        console.log(grupo);
        this.grupoService.guardarGrupo(grupo).subscribe(d=>{
          if(d){
            this.snackBar.open('Se ha almacenado correctamente el grupo', 'x', { verticalPosition: 'top', duration: 10000 });
            this.router.navigate(["/gestionGrupos"]);
            
          }
        })
      }
      
    }
    
    
  }

  ngOnDestroy(): void {
    this.localStorage.removeFromLocal('idGrupo');
  }

}
