package es.cheste.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class Realizar {

    private int idPlato;
    private int idChef;
    private LocalDate fecha;
}
