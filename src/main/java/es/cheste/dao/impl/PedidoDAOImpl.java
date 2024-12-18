package es.cheste.dao.impl;

import es.cheste.dao.PedidoDAO;
import es.cheste.entidad.Pedido;
import es.cheste.entidad.enums.EstadoPedido;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;
import es.cheste.utilidad.SentenciasSQL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PedidoDAO.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Pedido.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class PedidoDAOImpl implements PedidoDAO {

    /**
     * Inserta un nuevo pedido en la base de datos.
     *
     * @param pedido El objeto Pedido a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertar(Pedido pedido) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SentenciasSQL.getSentencia("insertar.pedido"), Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(pedido.getFechaPedido()));
            ps.setDouble(2, pedido.getPrecioTotal());
            ps.setString(3, String.valueOf(pedido.getEstadoPedido()));
            ps.setInt(4, pedido.getIdCliente());
            ps.setInt(5, pedido.getIdMesa());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de pedido fallida, no se afectaron filas.", null);
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setIdPedido(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Hubo un error al insertar un pedido", e);
        }
    }

    /**
     * Obtiene un pedido por su ID.
     *
     * @param idPedido El ID del pedido a obtener.
     * @return El objeto Pedido correspondiente al ID proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Pedido obtenerPorID(int idPedido) throws DAOException {
        Pedido pedido = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.id.pedido"))) {

            ps.setInt(1, idPedido);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pedido = mappearPedido(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el pedido por ID.", e);
        }

        return pedido;
    }

    /**
     * Obtiene una lista de todos los pedidos.
     *
     * @return Una lista de objetos Pedido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Pedido> obtenerTodos() throws DAOException {
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.todos.pedido"));
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = mappearPedido(rs);
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los pedidos.", e);
        }

        return pedidos;
    }

    /**
     * Actualiza la información de un pedido existente.
     *
     * @param pedido El objeto Pedido con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Pedido pedido) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("actualizar.pedido"))) {

            ps.setDate(1, Date.valueOf(pedido.getFechaPedido()));
            ps.setDouble(2, pedido.getPrecioTotal());
            ps.setString(3, String.valueOf(pedido.getEstadoPedido()));
            ps.setInt(4, pedido.getIdCliente());
            ps.setInt(5, pedido.getIdMesa());
            ps.setInt(6, pedido.getIdPedido());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de pedido fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el pedido.", e);
        }
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param idPedido El ID del pedido a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int idPedido) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("eliminar.pedido"))) {

            ps.setInt(1, idPedido);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación del pedido fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el pedido.", e);
        }
    }

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return Un objeto Connection.
     * @throws SQLException Si ocurre un error al obtener la conexión.
     */
    public Connection obtenerConexion() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        return conexion.getConnection();
    }

    /**
     * Mapea un ResultSet a un objeto Pedido.
     *
     * @param rs El ResultSet a mapear.
     * @return Un objeto Pedido.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    public Pedido mappearPedido(ResultSet rs) throws SQLException {
        int idPedido = rs.getInt("ID_PEDIDO");
        LocalDate fechaPedido = rs.getDate("FECHA_PEDIDO").toLocalDate();
        double precioTotal = rs.getDouble("PRECIO_TOTAL");
        int idCliente = rs.getInt("ID_CLIENTE");
        int idMesa = rs.getInt("ID_MESA");
        EstadoPedido estadoPedido = EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO").toUpperCase());

        return new Pedido(idPedido, fechaPedido, precioTotal, idCliente, idMesa, estadoPedido);
    }
}