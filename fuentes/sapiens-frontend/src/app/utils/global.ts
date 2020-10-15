export const global = {
    HTTP_OK: "200",
    HTTP_BAD_REQUEST: "400",

    //TIPOS DE VALIDACIONES DE PERMISOS PARA ACCEDER A RUTAS
    GUARDS: {
        NO_AUTH: 'NO_AUTH',
        AUTH: 'AUTH',
        PROFESOR: 'PROFESOR',
        DIRECTOR: 'DIRECTOR',
        ESTUDIANTE: 'ESTUDIANTE',
    },

    SESSION_ITEMS: {
        USUARIO: 'USUARIO'
    },

    EXTENSIONES: ["xlsx", "xls"],

    TIPOS_USUARIO: {
        PROFESOR: 1,
        DIRECTOR: 2,
        ESTUDIANTE: 3,
        ADMINISTRADOR: 4,
    }
}