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

public class PlatoDAOImpl implements PlatoDAO {

    @Override
    public void insertar(Plato plato) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SentenciasSQL.getSentencia("insertar.plato"), Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, plato.getNombrePlato());
            ps.setString(2, plato.getDescripcion());
            ps.setDouble(3,plato.getPrecioPlato());
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

    @Override
    public void actualizar(Plato plato) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("actualizar.plato"))) {

            ps.setString(1, plato.getNombrePlato());
            ps.setString(2, plato.getDescripcion());
            ps.setDouble(3,plato.getPrecioPlato());
            ps.setString(4, String.valueOf(plato.getCategoriaPlato()));
            ps.setInt(5,plato.getIdPlato());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de plato fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el plato.", e);
        }
    }

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

    public Connection obtenerConexion() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        return conexion.getConnection();
    }

    public Plato mappearPlato(ResultSet rs) throws SQLException {
        int idPlato = rs.getInt("ID_PLATO");
        String nombrePlato = rs.getString("NOMBRE_PLATO");
        String descripcion = rs.getString("DESCRIPCION");
        Double precioPlato = rs.getDouble("PRECIO_PLATO");
        CategoriaPlato categoriaPlato = CategoriaPlato.valueOf(rs.getString("CATEGORIA_PLATO").toUpperCase());

        return new Plato(idPlato, nombrePlato, descripcion, precioPlato, categoriaPlato);
    }
}