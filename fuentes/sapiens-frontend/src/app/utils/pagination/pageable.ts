import { Sort } from "./sort";

/**
 * Clase integrante de la paginacion. Aqui se detalle la informacion de la pagina consultada para mostrar.
 * @author Andrés Felipe Vargas López
 * @version Dec 04, 2018
 * @since 6.2.4
 */
export class Pageable {

    public sort: Sort;
    public offset: Number;
    public pageNumber: Number;
    public pageSize: Number;
    public paged: Boolean;
    public unpaged: Boolean;

    constructor(){}
}
