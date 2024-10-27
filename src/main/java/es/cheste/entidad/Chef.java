package es.cheste.entidad;

import es.cheste.entidad.enums.EspecialidadChef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa a un Chef.
 * <p>
 * Contiene información sobre el chef, incluyendo su ID, nombre, especialidad, experiencia, teléfono y disponibilidad.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chef {

    private int idChef;
    private String nombreChef;
    private EspecialidadChef especialidadChef;
    private int experiencia;
    private String telefonoChef;
    private boolean estaDisponible;

    /**
     * Constructor con parámetros para la clase Chef.
     *
     * @param nombreChef       Nombre del chef.
     * @param especialidadChef Especialidad del chef.
     * @param experiencia      Años de experiencia del chef.
     * @param telefonoChef     Teléfono del chef.
     * @param estaDisponible   Disponibilidad del chef.
     */
    public Chef(String nombreChef, EspecialidadChef especialidadChef, int experiencia, String telefonoChef, boolean estaDisponible) {
        this.nombreChef = nombreChef;
        this.especialidadChef = especialidadChef;
        this.experiencia = experiencia;
        this.telefonoChef = telefonoChef;
        this.estaDisponible = estaDisponible;
    }
}
