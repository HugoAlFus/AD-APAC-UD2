package es.cheste.servicio;

import es.cheste.dao.PlatoDAO;
import es.cheste.dao.impl.PlatoDAOImpl;
import es.cheste.entidad.Plato;
import es.cheste.entidad.enums.CategoriaPlato;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PlatoServicio {

    private static final Logger LOGGER = LogManager.getLogger(PlatoServicio.class);
    private PlatoDAO platoDAO;

    public PlatoServicio() {
        this.platoDAO = new PlatoDAOImpl();
    }

    public void agregarPlato(String nombrePlato, String descripcion, double precioPlato, CategoriaPlato categoriaPlato) {
        Plato plato = new Plato(nombrePlato, descripcion, precioPlato, categoriaPlato);

        try {
            platoDAO.insertar(plato);
            System.out.println("Plato agregado con ID: " + plato.getIdPlato());
        } catch (DAOException e) {
            System.err.println("El plato no se pudo agregar");
            LOGGER.error("No se agregó el Plato: {}", e.getMessage());
        }
    }

    public Plato obtenerPlato(int idPlato) {
        try {
            return platoDAO.obtenerPorID(idPlato);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener el plato: {}", e.getMessage());
            return null;
        }
    }

    public List<Plato> listarPlatos() {
        try {
            return platoDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todos los platos: {}", e.getMessage());
            return null;
        }
    }

    public void actualizarPlato(int idPlato, String nombrePlato, String descripcion, double precioPlato, CategoriaPlato categoriaPlato) {
        Plato plato = new Plato(idPlato, nombrePlato, descripcion, precioPlato, categoriaPlato);

        try {
            platoDAO.actualizar(plato);
            System.out.println("Plato actualizado: " + plato);
        } catch (DAOException e) {
            System.err.println("El plato no se pudo actualizar");
            LOGGER.error("No se actualizó el plato: {}", e.getMessage());
        }
    }

    public void eliminarPlato(int idPlato) {
        try {
            platoDAO.eliminar(idPlato);
            System.out.println("Plato eliminado con ID " + idPlato);
        } catch (DAOException e) {
            System.err.println("Hubo un error al intentar eliminar el plato");
            LOGGER.error("Hubo un error al eliminar el plato: {}", e.getMessage());
        }
    }
}
