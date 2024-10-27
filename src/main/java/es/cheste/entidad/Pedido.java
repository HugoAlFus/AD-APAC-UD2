package es.cheste.entidad;

import es.cheste.entidad.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Clase que representa un Pedido.
 * <p>
 * Contiene información sobre el pedido, incluyendo su ID, fecha, precio total, ID del cliente, ID de la mesa y estado.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private int idPedido;
    private LocalDate fechaPedido;
    private double precioTotal;
    private int idCliente;
    private int idMesa;
    private EstadoPedido estadoPedido;

    /**
     * Constructor con parámetros para la clase Pedido.
     *
     * @param fechaPedido  Fecha del pedido.
     * @param precioTotal  Precio total del pedido.
     * @param idCliente    ID del cliente que realizó el pedido.
     * @param idMesa       ID de la mesa asociada al pedido.
     * @param estadoPedido Estado del pedido.
     */
    public Pedido(LocalDate fechaPedido, double precioTotal, int idCliente, int idMesa, EstadoPedido estadoPedido) {
        this.fechaPedido = fechaPedido;
        this.precioTotal = precioTotal;
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.estadoPedido = estadoPedido;
    }
}