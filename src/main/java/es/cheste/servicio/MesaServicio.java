package es.cheste.servicio;

import es.cheste.dao.MesaDAO;
import es.cheste.dao.impl.MesaDAOImpl;
import es.cheste.entidad.Mesa;
import es.cheste.entidad.enums.EstadoMesa;
import es.cheste.entidad.enums.UbicacionMesa;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MesaServicio {

    private static final Logger LOGGER = LogManager.getLogger(MesaServicio.class);
    private MesaDAO mesaDAO;

    public MesaServicio() {
        this.mesaDAO = new MesaDAOImpl();
    }

    public void agregarMesa(int numeroMesa, int capacidad, UbicacionMesa ubicacionMesa, EstadoMesa estadoMesa) {
        Mesa mesa = new Mesa(numeroMesa, capacidad, ubicacionMesa, estadoMesa);

        try {
            mesaDAO.insertar(mesa);
            System.out.println("Mesa agregada con ID: " + mesa.getIdMesa());
        } catch (DAOException e) {
            System.err.println("La mesa no se pudo agregar");
            LOGGER.error("No se agregó la Mesa: {}", e.getMessage());
        }
    }

    public Mesa obtenerMesa(int idMesa) {
        try {
            return mesaDAO.obtenerPorID(idMesa);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener la mesa: {}", e.getMessage());
            return null;
        }
    }

    public List<Mesa> listarMesas() {
        try {
            return mesaDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todas las mesas: {}", e.getMessage());
            return null;
        }
    }

    public void actualizarMesa(int idMesa, int numeroMesa, int capacidad, UbicacionMesa ubicacionMesa, EstadoMesa estadoMesa) {
        Mesa mesa = new Mesa(idMesa, numeroMesa, capacidad, ubicacionMesa, estadoMesa);

        try {
            mesaDAO.actualizar(mesa);
            System.out.println("Mesa actualizada: " + mesa);
        } catch (DAOException e) {
            System.err.println("La mesa no se pudo actualizar");
            LOGGER.error("No se actualizó la mesa: {}", e.getMessage());
        }
    }

    public void eliminarMesa(int idMesa) {
        try {
            mesaDAO.eliminar(idMesa);
            System.out.println("Mesa eliminada con ID " + idMesa);
        } catch (DAOException e) {
            System.err.println("Hubo un error al intentar eliminar la mesa");
            LOGGER.error("Hubo un error al eliminar la mesa: {}", e.getMessage());
        }
    }
}
