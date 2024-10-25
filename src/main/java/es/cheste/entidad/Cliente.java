package es.cheste.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {

    private int idCliente;
    private String nombreCliente;
    private String telefonoCliente;
    private String correoElectronico;
    private String direccion;

}
