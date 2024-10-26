package es.cheste.dao.impl;

import es.cheste.dao.RealizarDAO;
import es.cheste.entidad.Realizar;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RealizarDAOImpl implements RealizarDAO {

    private static final String INSERTAR = "INSERT INTO REALIZAR (ID_PLATO, ID_CHEF, FECHA) VALUES (?,?,?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM REALIZAR WHERE ID_PLATO=? AND ID_CHEF=?";
    private static final String OBTENER_TODOS = "SELECT * FROM REALIZAR";
    private static final String ACTUALIZAR = "UPDATE REALIZAR SET FECHA=? WHERE ID_PLATO=? AND ID_CHEF=?";
    private static final String ELIMINAR = "DELETE FROM REALIZAR WHERE ID_PLATO=? AND ID_CHEF=?";

    @Override
    public void insertar(Realizar realizar) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(INSERTAR)) {

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

    @Override
    public Realizar obtenerPorID(int idPlato, int idChef) throws DAOException {
        Realizar realizar = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_POR_ID)) {

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

    @Override
    public List<Realizar> obtenerTodos() throws DAOException {
        List<Realizar> realizarList = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_TODOS);
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

    @Override
    public void actualizar(Realizar realizar) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ACTUALIZAR)) {

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

    @Override
    public void eliminar(int idPlato, int idChef) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ELIMINAR)) {

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

    public Connection obtenerConexion() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        return conexion.getConnection();
    }

    public Realizar mappearRealizar(ResultSet rs) throws SQLException {
        int idPlato = rs.getInt("ID_PLATO");
        int idChef = rs.getInt("ID_CHEF");
        LocalDate fechaRealizacion = rs.getDate("FECHA").toLocalDate();

        return new Realizar(idPlato, idChef, fechaRealizacion);
    }
}
