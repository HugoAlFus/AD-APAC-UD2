package es.cheste.dao.impl;

import es.cheste.dao.ClienteDAO;
import es.cheste.entidad.Cliente;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;
import es.cheste.utilidad.SentenciasSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {



    @Override
    public void insertar(Cliente cliente) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SentenciasSQL.getSentencia("insertar.cliente"), Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getTelefonoCliente());
            ps.setString(3, cliente.getCorreoElectronico());
            ps.setString(4, cliente.getDireccion());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de cliente fallida, no se afectaron filas.", null);
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Hubo un error al insertar un cliente", e);
        }
    }

    @Override
    public Cliente obtenerPorID(int idCliente) throws DAOException {
        Cliente cliente = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.id.cliente"))) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = mappearCliente(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el cliente por ID.", e);
        }

        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodos() throws DAOException {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obetner.todos.cliente"));
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = mappearCliente(rs);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los clientes.", e);
        }

        return clientes;
    }

    @Override
    public void actualizar(Cliente cliente) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("actualizar.cliente"))) {

            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getTelefonoCliente());
            ps.setString(3, cliente.getCorreoElectronico());
            ps.setString(4, cliente.getDireccion());
            ps.setInt(5, cliente.getIdCliente());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de cliente fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el cliente.", e);
        }
    }

    @Override
    public void eliminar(int idCliente) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("eliminar.cliente"))) {

            ps.setInt(1, idCliente);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación del cliente fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el cliente.", e);
        }
    }

    public Connection obtenerConexion() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        return conexion.getConnection();
    }

    public Cliente mappearCliente(ResultSet rs) throws SQLException {

        int idCliente = rs.getInt("ID_CLIENTE");
        String nombreCliente = rs.getString("NOMBRE_CLIENTE");
        String telefonoCliente = rs.getString("TELEFONO_CLIENTE");
        String correoElectronico = rs.getString("CORREO_ELECTRONICO");
        String direccion = rs.getString("DIRECCION");

        return new Cliente(idCliente, nombreCliente, telefonoCliente, correoElectronico, direccion);
    }
}
