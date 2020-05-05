import { Injectable, EventEmitter } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FuseProgressBarService } from '@fuse/components/progress-bar/progress-bar.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { catchError, finalize, tap } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material';

@Injectable()
export class AppInterceptor implements HttpInterceptor {

  private count: number = 0;

  constructor(
    private progressBar: FuseProgressBarService,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.count++;
    this.progressBar.show();

    if (!req.headers.has('Content-Type')) {
      req = req.clone({
        headers: req.headers.set('Content-Type', 'application/json')
      });
    }

    return next.handle(req).pipe(
      tap(
        (event: HttpEvent<any>) => {},
        (error: HttpErrorResponse) => {
          if(error.error instanceof ProgressEvent){
            this.snackBar.open("Ha ocurrido un error conectando con el servidor", '×', { panelClass: 'error', verticalPosition: 'top', duration: 8000 });
          }else{
            this.snackBar.open(error.error, '×', { panelClass: 'error', verticalPosition: 'top', duration: 8000 });
          }
        }
      ),
      finalize(() => {
        this.count--;
        if (this.count == 0) {
          this.progressBar.hide();
        }
      })
    );
  }
}