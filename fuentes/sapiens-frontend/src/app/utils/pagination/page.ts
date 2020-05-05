import { Sort } from "./sort";
import { Pageable } from "./pageable";

/**
 * Clase del paquete org.springframework.data.domain para poder 
 * paginar con spring-data.
 * @author Andrés Felipe Vargas López
 * @version Dec 06, 2018
 * @since 6.2.4
 */
export class Page {

    public content: Array<any>;
    public pageable: Pageable;
    public last: Boolean;
    public totalPages: Number;
    public totalElements: Number;
    public size: Number;
    public number: Number;
    public sort: Sort;
    public first: Boolean;
    public numberOfElements: Number;
    public empty: Boolean;
}
