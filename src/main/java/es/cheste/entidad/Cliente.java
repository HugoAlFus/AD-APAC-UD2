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

    public Cliente(String nombreCliente, String telefonoCliente, String correoElectronico, String direccion) {
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }
}
