package es.cheste.dao.impl;

import es.cheste.dao.PlatoDAO;
import es.cheste.entidad.Plato;
import es.cheste.entidad.enums.CategoriaPlato;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;
import es.cheste.utilidad.SentenciasSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PlatoDAO.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la entidad Plato.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class PlatoDAOImpl implements PlatoDAO {

    /**
     * Inserta un nuevo plato en la base de datos.
     *
     * @param plato El objeto Plato a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertar(Plato plato) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SentenciasSQL.getSentencia("insertar.plato"), Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, plato.getNombrePlato());
            ps.setString(2, plato.getDescripcion());
            ps.setDouble(3, plato.getPrecioPlato());
            ps.setString(4, String.valueOf(plato.getCategoriaPlato()));

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de plato fallida, no se afectaron filas.", null);
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    plato.setIdPlato(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Hubo un error al insertar un plato", e);
        }
    }

    /**
     * Obtiene un plato por su ID.
     *
     * @param idPlato El ID del plato a obtener.
     * @return El objeto Plato correspondiente al ID proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Plato obtenerPorID(int idPlato) throws DAOException {
        Plato plato = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.id.plato"))) {

            ps.setInt(1, idPlato);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    plato = mappearPlato(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el plato por ID.", e);
        }

        return plato;
    }

    /**
     * Obtiene una lista de todos los platos.
     *
     * @return Una lista de objetos Plato.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Plato> obtenerTodos() throws DAOException {
        List<Plato> platos = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.todos.plato"));
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Plato plato = mappearPlato(rs);
                platos.add(plato);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los platos.", e);
        }

        return platos;
    }

    /**
     * Actualiza la información de un plato existente.
     *
     * @param plato El objeto Plato con la información actualizada.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Plato plato) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("actualizar.plato"))) {

            ps.setString(1, plato.getNombrePlato());
            ps.setString(2, plato.getDescripcion());
            ps.setDouble(3, plato.getPrecioPlato());
            ps.setString(4, String.valueOf(plato.getCategoriaPlato()));
            ps.setInt(5, plato.getIdPlato());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de plato fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el plato.", e);
        }
    }

    /**
     * Elimina un plato por su ID.
     *
     * @param idPlato El ID del plato a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int idPlato) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("eliminar.plato"))) {

            ps.setInt(1, idPlato);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación del plato fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el plato.", e);
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
     * Mapea un ResultSet a un objeto Plato.
     *
     * @param rs El ResultSet a mapear.
     * @return Un objeto Plato.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    public Plato mappearPlato(ResultSet rs) throws SQLException {
        int idPlato = rs.getInt("ID_PLATO");
        String nombrePlato = rs.getString("NOMBRE_PLATO");
        String descripcion = rs.getString("DESCRIPCION");
        Double precioPlato = rs.getDouble("PRECIO_PLATO");
        CategoriaPlato categoriaPlato = CategoriaPlato.valueOf(rs.getString("CATEGORIA_PLATO").toUpperCase());

        return new Plato(idPlato, nombrePlato, descripcion, precioPlato, categoriaPlato);
    }
}