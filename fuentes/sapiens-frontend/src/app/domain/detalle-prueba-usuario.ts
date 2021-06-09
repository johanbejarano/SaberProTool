import { Respuesta } from './respuesta';

export class DetallePruebaUsuario {
    dpruId: number;
    prusId: number;
    respId: number;
    usuCreador: number;
    respuestaAbierta: string;
    seleccionMultiple: boolean;

    nombreModulo: string;
    descripcionPregunta: string;
    descripcionContexto: string;
    retroalimentacionPregunta: string;
    respuestas: Respuesta[];
}
