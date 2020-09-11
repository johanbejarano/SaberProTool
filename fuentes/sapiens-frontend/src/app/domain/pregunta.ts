import { Respuesta } from './respuesta';

export class Pregunta {
    descripcion: string;
    estadoRegistro: string;
    fechaCreacion: Date;
    fechaModificacion: Date;
    pregId: number;
    retroalimentacion: string;
    usuCreador: number;
    usuModificador: number;
    moduId_Modulo: number;
    tprgId_TipoPregunta: number;
    timoId: number;

    nombreModulo: string;
    tipoPregunta: string;

    respuestasDTO: Respuesta[];

    complejidad: number;
    valorPregunta: number;

    facuId: number;
    facultad: string;
    progId: number;
    programa: string;

    contId: number;
    contexto: string;
}
