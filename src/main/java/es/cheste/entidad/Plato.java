package es.cheste.entidad;

import es.cheste.entidad.enums.CategoriaPlato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un Plato.
 * <p>
 * Contiene información sobre el plato, incluyendo su ID, nombre, descripción, precio y categoría.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plato {

    private int idPlato;
    private String nombrePlato;
    private String descripcion;
    private double precioPlato;
    private CategoriaPlato categoriaPlato;

    /**
     * Constructor con parámetros para la clase Plato.
     *
     * @param nombrePlato    Nombre del plato.
     * @param descripcion    Descripción del plato.
     * @param precioPlato    Precio del plato.
     * @param categoriaPlato Categoría del plato.
     */
    public Plato(String nombrePlato, String descripcion, double precioPlato, CategoriaPlato categoriaPlato) {
        this.nombrePlato = nombrePlato;
        this.descripcion = descripcion;
        this.precioPlato = precioPlato;
        this.categoriaPlato = categoriaPlato;
    }
}
