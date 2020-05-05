import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { locale as espanol } from './i18n/es';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-observation-dialog',
  templateUrl: './observation-dialog.component.html',
  styleUrls: ['./observation-dialog.component.scss']
})
export class ObservationDialogComponent implements OnInit {

  public confirmMessage: string;
  form: FormGroup;
  confirmDialogRef: MatDialogRef<ObservationDialogComponent>;

  /**
   * Constructor
   *
   * @param {MatDialogRef<ObservationDialogComponent>} dialogRef
   */
  constructor(
    public dialogRef: MatDialogRef<ObservationDialogComponent>,
    private _fuseTranslationLoaderService: FuseTranslationLoaderService,
    private formBuilder: FormBuilder,
  ) {
    this._fuseTranslationLoaderService.loadTranslations(espanol);
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      'observacion': ['', Validators.compose([Validators.maxLength(500)])]
    });
  }

  onFormSubmit() {
    if (this.form.valid) {

      const respuesta = this.form.controls.observacion.value ? this.form.controls.observacion.value : '';
      this.dialogRef.close(respuesta);
    }
  }
}
