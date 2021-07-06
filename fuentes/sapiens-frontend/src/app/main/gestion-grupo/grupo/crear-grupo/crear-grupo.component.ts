import { Component, OnInit } from '@angular/core';
import { LocalStorageService } from 'app/services/local-storage.service';
import { UsuarioService } from 'app/services/usuario.service';

@Component({
  selector: 'app-crear-grupo',
  templateUrl: './crear-grupo.component.html',
  styleUrls: ['./crear-grupo.component.scss']
})
export class CrearGrupoComponent implements OnInit {

  idGrupo : number;
  usuariosSeleccionados: number[] = [];

  constructor(
    private localStorage: LocalStorageService,
    private usuarioService: UsuarioService,
  ) { }

  ngOnInit() {
    this.idGrupo = this.localStorage.getFromLocal('idGrupo');
    
    if(this.idGrupo){
      this.usuarioService.getUsuariosPorGrupo(this.idGrupo).subscribe(d=>{
        if(d){
          console.log(d);
        }
      })
    }

  }

  guardarGrupo(){
    if(this.idGrupo){
      console.log('actualizar');
    }else{
      console.log('guardar');
      console.log(this.usuariosSeleccionados);
      
    }
    
    
  }

  ngOnDestroy(): void {
    this.localStorage.removeFromLocal('idGrupo');
  }

}
