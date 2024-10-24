package es.cheste.entidad;

import es.cheste.entidad.enums.EspecialidadChef;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chef {

    private int id_Chef;
    private String nombreChef;
    private EspecialidadChef especialidadChef;
    private int experiencia;
    private String telefonoChef;
    private boolean estaDisponible;
}
