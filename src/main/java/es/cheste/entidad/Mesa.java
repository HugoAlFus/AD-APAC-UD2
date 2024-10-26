package es.cheste.entidad;

import es.cheste.entidad.enums.EstadoMesa;
import es.cheste.entidad.enums.UbicacionMesa;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mesa {

    private int idMesa;
    private int numeroMesa;
    private int capacidad;
    private UbicacionMesa ubicacionMesa;
    private EstadoMesa estadoMesa;

    public Mesa(int numeroMesa, int capacidad, UbicacionMesa ubicacionMesa, EstadoMesa estadoMesa) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.ubicacionMesa = ubicacionMesa;
        this.estadoMesa = estadoMesa;
    }
}
