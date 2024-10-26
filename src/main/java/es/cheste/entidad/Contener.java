package es.cheste.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contener {

    private int idPedido;
    private int idPlato;
    private int cantidad;
    private double subtotal;


}
