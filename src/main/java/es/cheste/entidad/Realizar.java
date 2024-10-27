package es.cheste.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Clase que representa la relación entre un plato y un chef.
 * <p>
 * Contiene información sobre el ID del plato, el ID del chef y la fecha en que se realizó.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Realizar {

    private int idPlato;
    private int idChef;
    private LocalDate fecha;

}
