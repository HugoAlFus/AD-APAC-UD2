package es.cheste.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Realizar {

    private int idPlato;
    private int idChef;
    private Date date;
}
