package es.cheste.dao.impl;

import es.cheste.dao.MesaDAO;
import es.cheste.entidad.Mesa;
import es.cheste.entidad.enums.EstadoMesa;
import es.cheste.entidad.enums.UbicacionMesa;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;
import es.cheste.utilidad.SentenciasSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz MesaDAO.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Mesa.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class MesaDAOImpl implements MesaDAO {

    /**
     * Inserta una nueva mesa en la base de datos.
     *
     * @param mesa El objeto Mesa a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertar(Mesa mesa) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SentenciasSQL.getSentencia("insertar.mesa"), Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, mesa.getNumeroMesa());
            ps.setInt(2, mesa.getCapacidad());
            ps.setString(3, String.valueOf(mesa.getUbicacionMesa()));
            ps.setString(4, String.valueOf(mesa.getEstadoMesa()));

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de mesa fallida, no se afectaron filas.", null);
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    mesa.setIdMesa(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Hubo un error al insertar una mesa", e);
        }
    }

    /**
     * Obtiene una mesa por su ID.
     *
     * @param idMesa El ID de la mesa a obtener.
     * @return El objeto Mesa correspondiente al ID proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Mesa obtenerPorID(int idMesa) throws DAOException {
        Mesa mesa = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.id.mesa"))) {

            ps.setInt(1, idMesa);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mesa = mappearMesa(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener la mesa por ID.", e);
        }

        return mesa;
    }

    /**
     * Obtiene una lista de todas las mesas.
     *
     * @return Una lista de objetos Mesa.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Mesa> obtenerTodos() throws DAOException {
        List<Mesa> mesas = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.todos.mesa"));
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mesa mesa = mappearMesa(rs);
                mesas.add(mesa);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todas las mesas.", e);
        }

        return mesas;
    }

    /**
     * Actualiza la información de una mesa existente.
     *
     * @param mesa El objeto Mesa con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Mesa mesa) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("actualizar.mesa"))) {

            ps.setInt(1, mesa.getNumeroMesa());
            ps.setInt(2, mesa.getCapacidad());
            ps.setString(3, String.valueOf(mesa.getUbicacionMesa()));
            ps.setString(4, String.valueOf(mesa.getEstadoMesa()));
            ps.setInt(5, mesa.getIdMesa());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de mesa fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar la mesa.", e);
        }
    }

    /**
     * Elimina una mesa por su ID.
     *
     * @param idMesa El ID de la mesa a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int idMesa) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("eliminar.mesa"))) {

            ps.setInt(1, idMesa);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de la mesa fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la mesa.", e);
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
     * Mapea un ResultSet a un objeto Mesa.
     *
     * @param rs El ResultSet a mapear.
     * @return Un objeto Mesa.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    public Mesa mappearMesa(ResultSet rs) throws SQLException {
        int idMesa = rs.getInt("ID_MESA");
        int numeroMesa = rs.getInt("NUMERO_MESA");
        int capacidad = rs.getInt("CAPACIDAD");
        UbicacionMesa ubicacionMesa = UbicacionMesa.valueOf(rs.getString("UBICACION_MESA").toUpperCase());
        EstadoMesa estadoMesa = EstadoMesa.valueOf(rs.getString("ESTADO_MESA").toUpperCase());

        return new Mesa(idMesa, numeroMesa, capacidad, ubicacionMesa, estadoMesa);
    }
}