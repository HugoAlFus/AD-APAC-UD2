package es.cheste.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la relación entre un pedido y un plato.
 * <p>
 * Contiene información sobre el ID del pedido, el ID del plato, la cantidad y el subtotal.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contener {

    private int idPedido;
    private int idPlato;
    private int cantidad;
    private double subtotal;

}