import { Respuesta } from './respuesta';

export class DetallePruebaUsuario {
    dpruId: number;
    prusId: number;
    respId: number;
    usuCreador: number;
    respuestaAbierta: string;

    nombreModulo: string;
    descripcionPregunta: string;
    descripcionContexto: string;
    retroalimentacionPregunta: Date;
    respuestas: Respuesta[];
}
