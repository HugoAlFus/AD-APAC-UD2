package es.cheste.entidad;

import es.cheste.entidad.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Pedido {

    private int idPedido;
    private Date fechaPedido;
    private double precioTotal;
    private int idCliente;
    private int idMesa;
    private EstadoPedido estadoPedido;

}
