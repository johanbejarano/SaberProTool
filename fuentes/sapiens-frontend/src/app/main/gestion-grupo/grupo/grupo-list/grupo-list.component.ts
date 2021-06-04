import { Router } from '@angular/router';
import { LocalStorageService } from './../../../../services/local-storage.service';
import { GrupoService } from '../../../../services/grupo.service';
import { Subscription } from 'rxjs';
import { Grupo } from '../../../../domain/grupo';
import { MatTableDataSource, MatSnackBar } from '@angular/material';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-grupo-list',
  templateUrl: './grupo-list.component.html',
  styleUrls: ['./grupo-list.component.scss']
})
export class GrupoListComponent implements OnInit {

  subscription: Subscription;

  data: Grupo[] = [];
  datasource: MatTableDataSource<Grupo> = new MatTableDataSource<Grupo>();
  public displayedColumns = ['grupId', 'nombreGrupo', 'descripcion'];

  constructor(
    private grupoService: GrupoService,
    private snackBar: MatSnackBar,
    private localStorage: LocalStorageService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.getData();
  }

  ngOnDestroy() {
    if (this.subscription !== null && this.subscription !== undefined)
      this.subscription.unsubscribe();
  }

  getData() {
    console.log("GetData");
    
    this.subscription = this.grupoService.consultarGrupos().subscribe((grupos: Grupo[]) => {

        this.data = grupos;
        this.datasource = new MatTableDataSource<Grupo>(this.data);
        
        // this.datasource.paginator = this.paginator;
      }, 
      error => {
        this.snackBar.open(error.error.error, 'x', {verticalPosition: 'top', duration: 10000});
      });
  }

  seleccionar(item: Grupo) {
    this.localStorage.putInLocal('idGrupo', item.grupId);

    this.router.navigate(["/gestionGrupos/crearGrupo"]);

  }

}
