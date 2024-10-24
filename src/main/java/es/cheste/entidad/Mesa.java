package es.cheste.entidad;

import es.cheste.entidad.enums.EstadoMesa;
import es.cheste.entidad.enums.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mesa {

    private int idMesa;
    private int numeroMesa;
    private int capacidad;
    private Ubicacion ubicacion;
    private EstadoMesa estadoMesa;
}
