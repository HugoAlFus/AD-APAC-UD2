package es.cheste.dao.impl;

import es.cheste.dao.RealizarDAO;
import es.cheste.entidad.Realizar;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;
import es.cheste.utilidad.SentenciasSQL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz RealizarDAO.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Realizar.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class RealizarDAOImpl implements RealizarDAO {

    /**
     * Inserta un nuevo registro de realizar en la base de datos.
     *
     * @param realizar El objeto Realizar a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertar(Realizar realizar) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SentenciasSQL.getSentencia("insertar.realizar"))) {

            ps.setInt(1, realizar.getIdPlato());
            ps.setInt(2, realizar.getIdChef());
            ps.setDate(3, Date.valueOf(realizar.getFecha()));

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de realizar fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Hubo un error al insertar un realizar", e);
        }
    }

    /**
     * Obtiene un registro de realizar por su ID de plato y ID de chef.
     *
     * @param idPlato El ID del plato.
     * @param idChef  El ID del chef.
     * @return El objeto Realizar correspondiente a los IDs proporcionados.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Realizar obtenerPorID(int idPlato, int idChef) throws DAOException {
        Realizar realizar = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.id.realizar"))) {

            ps.setInt(1, idPlato);
            ps.setInt(2, idChef);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    realizar = mappearRealizar(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el realizar por ID.", e);
        }

        return realizar;
    }

    /**
     * Obtiene una lista de todos los registros de realizar.
     *
     * @return Una lista de objetos Realizar.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Realizar> obtenerTodos() throws DAOException {
        List<Realizar> realizarList = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.todos.realizar"));
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Realizar realizar = mappearRealizar(rs);
                realizarList.add(realizar);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los realizar.", e);
        }

        return realizarList;
    }

    /**
     * Actualiza la información de un registro de realizar existente.
     *
     * @param realizar El objeto Realizar con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Realizar realizar) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("actualizar.realizar"))) {

            ps.setDate(1, Date.valueOf(realizar.getFecha()));
            ps.setInt(2, realizar.getIdPlato());
            ps.setInt(3, realizar.getIdChef());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de realizar fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el realizar.", e);
        }
    }

    /**
     * Elimina un registro de realizar por su ID de plato y ID de chef.
     *
     * @param idPlato El ID del plato.
     * @param idChef  El ID del chef.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int idPlato, int idChef) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("eliminar.realizar"))) {

            ps.setInt(1, idPlato);
            ps.setInt(2, idChef);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación del realizar fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el realizar.", e);
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
     * Mapea un ResultSet a un objeto Realizar.
     *
     * @param rs El ResultSet a mapear.
     * @return Un objeto Realizar.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    public Realizar mappearRealizar(ResultSet rs) throws SQLException {
        int idPlato = rs.getInt("ID_PLATO");
        int idChef = rs.getInt("ID_CHEF");
        LocalDate fechaRealizacion = rs.getDate("FECHA").toLocalDate();

        return new Realizar(idPlato, idChef, fechaRealizacion);
    }
}