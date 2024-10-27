package es.cheste.servicio;

import es.cheste.dao.ClienteDAO;
import es.cheste.dao.impl.ClienteDAOImpl;
import es.cheste.entidad.Cliente;
import es.cheste.utilidad.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con clientes.
 * <p>
 * Proporciona métodos para agregar, obtener, listar, actualizar y eliminar clientes.
 *
 * @version 1.0
 * @autor Hugo Almodóvar Fuster
 */
public class ClienteServicio {

    private static final Logger LOGGER = LogManager.getLogger(ClienteServicio.class);
    private ClienteDAO clienteDAO;

    /**
     * Constructor por defecto que inicializa el DAO de Cliente.
     */
    public ClienteServicio() {
        this.clienteDAO = new ClienteDAOImpl();
    }

    /**
     * Agrega un nuevo cliente.
     *
     * @param nombreCliente     Nombre del cliente.
     * @param telefonoCliente   Teléfono del cliente.
     * @param correoElectronico Correo electrónico del cliente.
     * @param direccion         Dirección del cliente.
     */
    public void agregarCliente(String nombreCliente, String telefonoCliente, String correoElectronico, String direccion) {
        Cliente cliente = new Cliente(nombreCliente, telefonoCliente, correoElectronico, direccion);

        try {
            clienteDAO.insertar(cliente);
            System.out.println("Cliente agregado con ID: " + cliente.getIdCliente());
        } catch (DAOException e) {
            System.err.println("El cliente no se pudo agregar");
            LOGGER.error("No se agrego el Cliente {}", e.getMessage());
        }
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param idCliente ID del cliente.
     * @return El cliente correspondiente al ID proporcionado.
     */
    public Cliente obtenerCliente(int idCliente) {
        try {
            return clienteDAO.obtenerPorID(idCliente);
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener el cliente {}", e.getMessage());
            return null;
        }
    }

    /**
     * Lista todos los clientes.
     *
     * @return Lista de todos los clientes.
     */
    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.obtenerTodos();
        } catch (DAOException e) {
            LOGGER.error("Hubo un error al obtener todos los clientes {}", e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza la información de un cliente.
     *
     * @param idCliente         ID del cliente.
     * @param nombreCliente     Nombre del cliente.
     * @param telefonoCliente   Teléfono del cliente.
     * @param correoElectronico Correo electrónico del cliente.
     * @param direccion         Dirección del cliente.
     */
    public void actualizarCliente(int idCliente, String nombreCliente, String telefonoCliente, String correoElectronico, String direccion) {
        Cliente cliente = new Cliente(idCliente, nombreCliente, telefonoCliente, correoElectronico, direccion);

        try {
            clienteDAO.actualizar(cliente);
            System.out.println("Cliente actualizado: " + cliente);
        } catch (DAOException e) {
            System.err.println("El cliente no se pudo actualizar");
            LOGGER.error("No se actualizo el cliente {}", e.getMessage());
        }
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param idCliente ID del cliente.
     */
    public void eliminarCliente(int idCliente) {
        try {
            clienteDAO.eliminar(idCliente);
            System.out.println("Cliente eliminado con ID " + idCliente);
        } catch (DAOException e) {
            System.err.println("Hubo un error al intentar eliminar el cliente");
            LOGGER.error("Hubo un error al eliminar el cliente {}", e.getMessage());
        }
    }
}