package es.cheste.entidad;

import es.cheste.entidad.enums.EstadoMesa;
import es.cheste.entidad.enums.UbicacionMesa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una Mesa.
 * <p>
 * Contiene información sobre la mesa, incluyendo su ID, número, capacidad, ubicación y estado.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mesa {

    private int idMesa;
    private int numeroMesa;
    private int capacidad;
    private UbicacionMesa ubicacionMesa;
    private EstadoMesa estadoMesa;

    /**
     * Constructor con parámetros para la clase Mesa.
     *
     * @param numeroMesa    Número de la mesa.
     * @param capacidad     Capacidad de la mesa.
     * @param ubicacionMesa Ubicación de la mesa.
     * @param estadoMesa    Estado de la mesa.
     */
    public Mesa(int numeroMesa, int capacidad, UbicacionMesa ubicacionMesa, EstadoMesa estadoMesa) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.ubicacionMesa = ubicacionMesa;
        this.estadoMesa = estadoMesa;
    }
}