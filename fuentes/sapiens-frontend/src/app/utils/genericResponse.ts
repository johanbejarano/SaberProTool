import { Page } from "./pagination/page";
/**
 * Respuesta generica para consumir REST Service en sigamos-cart.
 * @author Andrés Felipe Vargas López
 * @version Dec 06, 2018
 * @since 6.2.4
 */
export class GenericResponse {

  /**
   * Se agrega tipo Page del springframework - spring data, para paginar.
   * @author Andrés Felipe Vargas López
   * @version Dec 06, 2018
   * @since 6.2.4
   */
  public page:Page;
  
  constructor(
    public codigo: string,
    public mensaje: string,
    public lista: Array<any>
  ) {}

}
