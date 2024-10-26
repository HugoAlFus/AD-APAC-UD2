package es.cheste.entidad;

import es.cheste.entidad.enums.EspecialidadChef;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chef {

    private int idChef;
    private String nombreChef;
    private EspecialidadChef especialidadChef;
    private int experiencia;
    private String telefonoChef;
    private boolean estaDisponible;

    public Chef(String nombreChef, EspecialidadChef especialidadChef, int experiencia, String telefonoChef, boolean estaDisponible) {
        this.nombreChef = nombreChef;
        this.especialidadChef = especialidadChef;
        this.experiencia = experiencia;
        this.telefonoChef = telefonoChef;
        this.estaDisponible = estaDisponible;
    }
}
