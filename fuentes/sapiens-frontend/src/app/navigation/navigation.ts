import { FuseNavigation } from '@fuse/types';

export const navigation: FuseNavigation[] = [
    {
        id       : 'navigationProfesor',
        title    : 'Preguntas',
        translate: 'NAV.APPLICATIONS',
        type     : 'group',
        children : [
            {
                id       : 'mnuPreguntas',
                title    : 'Preguntas',
                translate: 'NAV.PROFESOR.PREGUNTAS',
                type     : 'item',
                icon     : 'device_unknown',
                url      : '/gestionPreguntas',
            },
            {
                id       : 'mnuSimulacion',
                title    : 'Simulacion',
                translate: 'NAV.PROFESOR.SIMULACION',
                type     : 'item',
                icon     : 'playlist_add_check',
                url      : '/gestionPruebas',
            },
            
            
        ]
    }
];

export const navigationProfesor: FuseNavigation[] = [
    {
        id       : 'navigationProfesor',
        title    : 'Preguntas',
        translate: 'NAV.APPLICATIONS',
        type     : 'group',
        children : [
            {
                id       : 'mnuPreguntas',
                title    : 'Preguntas',
                translate: 'NAV.PROFESOR.PREGUNTAS',
                type     : 'item',
                icon     : 'device_unknown',
                url      : '/gestionPreguntas',
            },
            {
                id       : 'mnuSimulacion',
                title    : 'Simulacion',
                translate: 'NAV.PROFESOR.SIMULACION',
                type     : 'item',
                icon     : 'playlist_add_check',
                url      : '/gestionPruebas',
            },
            
            
        ]
    }
];