package es.cheste.dao.impl;

import es.cheste.dao.ContenerDAO;
import es.cheste.entidad.Contener;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;
import es.cheste.utilidad.SentenciasSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ContenerDAO.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Contener.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class ContenerDAOImpl implements ContenerDAO {

    /**
     * Inserta un nuevo registro de contener en la base de datos.
     *
     * @param contener El objeto Contener a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertar(Contener contener) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SentenciasSQL.getSentencia("insertar.contener"))) {

            ps.setInt(1, contener.getIdPedido());
            ps.setInt(2, contener.getIdPlato());
            ps.setInt(3, contener.getCantidad());
            ps.setDouble(4, contener.getSubtotal());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de contener fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Hubo un error al insertar un contener", e);
        }
    }

    /**
     * Obtiene un registro de contener por su ID de pedido y ID de plato.
     *
     * @param idPedido El ID del pedido.
     * @param idPlato  El ID del plato.
     * @return El objeto Contener correspondiente a los IDs proporcionados.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Contener obtenerPorID(int idPedido, int idPlato) throws DAOException {
        Contener contener = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.id.contener"))) {

            ps.setInt(1, idPedido);
            ps.setInt(2, idPlato);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    contener = mappearContener(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el contener por ID.", e);
        }

        return contener;
    }

    /**
     * Obtiene una lista de todos los registros de contener.
     *
     * @return Una lista de objetos Contener.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Contener> obtenerTodos() throws DAOException {
        List<Contener> contenerList = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.todos.contener"));
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contener contener = mappearContener(rs);
                contenerList.add(contener);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los contener.", e);
        }

        return contenerList;
    }

    /**
     * Actualiza la información de un registro de contener existente.
     *
     * @param contener El objeto Contener con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Contener contener) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("actualizar.contener"))) {

            ps.setInt(1, contener.getCantidad());
            ps.setInt(2, contener.getIdPedido());
            ps.setDouble(3, contener.getSubtotal());
            ps.setInt(4, contener.getIdPlato());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de contener fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el contener.", e);
        }
    }

    /**
     * Elimina un registro de contener por su ID de pedido y ID de plato.
     *
     * @param idPedido El ID del pedido.
     * @param idPlato  El ID del plato.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int idPedido, int idPlato) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("eliminar.contener"))) {

            ps.setInt(1, idPedido);
            ps.setInt(2, idPlato);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación del contener fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el contener.", e);
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
     * Mapea un ResultSet a un objeto Contener.
     *
     * @param rs El ResultSet a mapear.
     * @return Un objeto Contener.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    public Contener mappearContener(ResultSet rs) throws SQLException {
        int idPedido = rs.getInt("ID_PEDIDO");
        int idPlato = rs.getInt("ID_PLATO");
        int cantidad = rs.getInt("CANTIDAD");
        double subtotal = rs.getDouble("SUBTOTAL");

        return new Contener(idPedido, idPlato, cantidad, subtotal);
    }
}