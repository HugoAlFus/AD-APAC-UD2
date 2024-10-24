package es.cheste.entidad;

import es.cheste.entidad.enums.CategoriaPlato;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plato {

    private int idPlato;
    private String nombrePlato;
    private String descripcion;
    private double precioPlato;
    private CategoriaPlato categoriaPlato;

}
