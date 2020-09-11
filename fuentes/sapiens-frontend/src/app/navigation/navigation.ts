import { FuseNavigation } from '@fuse/types';

export const navigation: FuseNavigation[] = [];

export const navigationProfesor: FuseNavigation[] = [
    {
        id: 'navigationProfesor',
        title: 'Preguntas',
        translate: 'NAV.APPLICATIONS',
        type: 'group',
        children: [
            {
                id: 'mnuPreguntas',
                title: 'Preguntas',
                translate: 'NAV.PROFESOR.PREGUNTAS',
                type: 'item',
                icon: 'question_answer',
                url: '/gestionPreguntas',
            },
            {
                id: 'mnuSimulacion',
                title: 'Pruebas',
                translate: 'NAV.PROFESOR.SIMULACION',
                type: 'item',
                icon: 'assignment',
                url: '/gestionPruebas',
            }
        ]
    },
    {
        id: 'cambiarClave',
        title: 'CambiarClave',
        translate: 'NAV.CAMBIAR_CLAVE',
        type: 'item',
        icon: 'person',
        url: '/init/cambiarClave',
    }
];

export const navigationDirector: FuseNavigation[] = [
    {
        id: 'navigationDirector',
        title: 'Preguntas',
        translate: 'NAV.APPLICATIONS',
        type: 'group',
        children: [
            {
                id: 'mnuPreguntas',
                title: 'Preguntas',
                translate: 'NAV.DIRECTOR.PREGUNTAS',
                type: 'item',
                icon: 'question_answer',
                url: '/gestionPreguntas',
            },
            {
                id: 'gestionUsuarios',
                title: 'gestionUsuarios',
                translate: 'NAV.DIRECTOR.USUARIOS',
                type: 'item',
                icon: 'people',
                url: '/gestionUsuarios',
            },
            {
                id: 'gestionModulos',
                title: 'gestionModulos',
                translate: 'NAV.DIRECTOR.MODULOS',
                type: 'item',
                icon: 'library_books',
                url: '/gestionModulos',
            },
            {
                id: 'descargarInformes',
                title: 'descargarInformes',
                translate: 'NAV.DIRECTOR.INFORMES',
                type: 'item',
                icon: 'picture_as_pdf',
                url: '/reportes',
            },
        ]
    },
    {
        id: 'cambiarClave',
        title: 'CambiarClave',
        translate: 'NAV.CAMBIAR_CLAVE',
        type: 'item',
        icon: 'person',
        url: '/init/cambiarClave',
    },
];

export const navigationEstudiante: FuseNavigation[] = [
    {
        id: 'navigationEstudiante',
        title: 'Preguntas',
        translate: 'NAV.APPLICATIONS',
        type: 'group',
        children: [
            {
                id: 'misPruebas',
                title: 'misPruebas',
                translate: 'NAV.ESTUDIANTE.MISPRUEBAS',
                type: 'item',
                icon: 'device_unknown',
                url: '/estudiante/misPruebas',
            },
        ]
    },
    {
        id: 'cambiarClave',
        title: 'CambiarClave',
        translate: 'NAV.CAMBIAR_CLAVE',
        type: 'item',
        icon: 'person',
        url: '/init/cambiarClave',
    }
];