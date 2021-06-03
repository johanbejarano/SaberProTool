import { Respuesta } from './respuesta';

export class Pregunta {
    descripcion: string;
    estadoRegistro: string;
    fechaCreacion: Date;
    fechaModificacion: Date;
    pregId: number;
    retroalimentacion: string;
    usuCreador: number;
    codigoUsuario: string;
    usuModificador: number;
    moduId_Modulo: number;
    tprgId_TipoPregunta: number;
    timoId: number;

    nombreModulo: string;
    tipoPregunta: string;

    respuestasDTO: Respuesta[];

    complejidad: number;
    valorPregunta: number;
    orden: number;

    facuId: number;
    facultad: string;
    progId: number;
    programa: string;

    contId: number;
    nombreContexto: string;
    contexto: string;
    tienePruebas: boolean;

    nombreTipoPregunta: string;
}
