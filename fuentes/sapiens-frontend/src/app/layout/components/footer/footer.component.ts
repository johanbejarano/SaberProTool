import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
    selector: 'footer',
    templateUrl: './footer.component.html',
    styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
    @Input() textoCancelar;
    @Input() textoGuardar;
    @Input() atras;
    @Input() adelante;

    @Output() onCancelar = new EventEmitter();
    @Output() onGuardar = new EventEmitter();
    @Output() onAtras = new EventEmitter();
    @Output() onAdelante = new EventEmitter();
    constructor() {
    }

    guardarEmit() {
        this.onGuardar.emit();
    }

    cancelarEmit() {
        this.onCancelar.emit();
    }

    atrasEmit() {
        this.onAtras.emit();
    }

    adelanteEmit() {
        this.onAdelante.emit();
    }
}
