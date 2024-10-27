package es.cheste.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa a un Cliente.
 * <p>
 * Contiene información sobre el cliente, incluyendo su ID, nombre, teléfono, correo electrónico y dirección.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private int idCliente;
    private String nombreCliente;
    private String telefonoCliente;
    private String correoElectronico;
    private String direccion;

    /**
     * Constructor con parámetros para la clase Cliente.
     *
     * @param nombreCliente     Nombre del cliente.
     * @param telefonoCliente   Teléfono del cliente.
     * @param correoElectronico Correo electrónico del cliente.
     * @param direccion         Dirección del cliente.
     */
    public Cliente(String nombreCliente, String telefonoCliente, String correoElectronico, String direccion) {
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }
}
