package es.cheste.servicio;

import es.cheste.dao.ChefDAO;
import es.cheste.dao.impl.ChefDAOImpl;
import es.cheste.entidad.Chef;
import es.cheste.entidad.enums.EspecialidadChef;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChefServicio {

    private static final Logger LOGGER = LogManager.getLogger(ChefServicio.class);
    private ChefDAO chefDAO;

    public ChefServicio() {
        this.chefDAO = new ChefDAOImpl();
    }

    public void agregarChef(String nombreChef, EspecialidadChef especialidadChef, int experiencia, String telefono, boolean disponible) {

        Chef chef = new Chef(nombreChef,especialidadChef,experiencia,telefono,disponible);

        try {
            chefDAO.insertar(chef);
            System.out.println("Chef agregado con ID: " + chef.getIdChef());
        } catch (DAOException e) {
            System.err.println("El chef no se pudo agregar");
            LOGGER.error("No se agrego el Chef {}", e.getMessage());
        }
    }

    public Chef obtenerChef(int idChef){
        try{
            return chefDAO.obtenerPorID(idChef);
        }catch (DAOException e){
            LOGGER.error("Hubo un error al obtener el chef {}", e.getMessage());
            return null;
        }
    }

    public List<Chef> lsitarChefs(){
        try{
            return chefDAO.obtenerTodos();
        }catch (DAOException e){
            LOGGER.error("Hubo un error al obtener todos los chefs {}", e.getMessage());
            return null;
        }
    }

    public void actualizarChef(int idChef, String nombreChef, EspecialidadChef especialidadChef, int experiencia, String telefonoChef, boolean disponible){



        try {
            clienteDAO.actualizar(cliente);
            System.out.println("Cliente actualizado: " + cliente);
        } catch (DAOException e){
            System.err.println("El cliente no se pudo actualizar");
            LOGGER.error("No se actualizo el cliente {}", e.getMessage());
        }
    }

    public void eliminarCliente(String dni){
        try{
            clienteDAO.eliminar(dni);
            System.out.println("Cliente eliminado con dni: " + dni);
        }catch (DAOException e){
            System.err.println("Hubo un error al intentar eliminar el cliente");
            LOGGER.error("Hubo un error al eliminar el cliente {}",e.getMessage());
        }
    }
}
