package es.cheste.entidad;

import es.cheste.entidad.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Pedido {

    private int idPedido;
    private LocalDate fechaPedido;
    private double precioTotal;
    private int idCliente;
    private int idMesa;
    private EstadoPedido estadoPedido;

    public Pedido(LocalDate fechaPedido, double precioTotal, int idCliente, int idMesa, EstadoPedido estadoPedido) {
        this.fechaPedido = fechaPedido;
        this.precioTotal = precioTotal;
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.estadoPedido = estadoPedido;
    }
}
