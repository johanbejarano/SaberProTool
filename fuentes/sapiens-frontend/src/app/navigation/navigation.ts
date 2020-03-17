import { FuseNavigation } from '@fuse/types';

export const navigation: FuseNavigation[] = [
    {
        id       : 'applications',
        title    : 'Applications',
        translate: 'NAV.APPLICATIONS',
        type     : 'group',
        children : [
            {
                id       : 'sample',
                title    : 'Sample',
                translate: 'NAV.SAMPLE.TITLE',
                type     : 'item',
                icon     : 'home',
                url      : '/gestionPreguntas',
                badge    : {
                    title    : '25',
                    translate: 'NAV.SAMPLE.BADGE',
                    bg       : '#F44336',
                    fg       : '#FFFFFF'
                }
            }
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
                icon     : 'device_unknown',
                url      : '/gestionPreguntas/registrarPregunta',
            },
            
            
        ]
    }
];