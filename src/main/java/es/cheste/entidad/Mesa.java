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
}
