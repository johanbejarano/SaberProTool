/**
 * Clase integrante de la paginacion. Aqui se define si la consulta paginada fue ordenada por spring-data o no.
 * @author Andrés Felipe Vargas López
 * @version Dec 04, 2018
 * @since 6.2.4
 */
export class Sort {

    public unsorted: Boolean;
    public sorted: Boolean;
    public empty: Boolean;

    constructor() { }

}
