package es.cheste.dao.impl;

import es.cheste.dao.ChefDAO;
import es.cheste.entidad.Chef;
import es.cheste.entidad.enums.EspecialidadChef;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;
import es.cheste.utilidad.SentenciasSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChefDAOImpl implements ChefDAO {

    @Override
    public void insertar(Chef chef) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SentenciasSQL.getSentencia("insertar.chef"), Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, chef.getNombreChef());
            ps.setString(2, String.valueOf(chef.getEspecialidadChef()));
            ps.setInt(3, chef.getExperiencia());
            ps.setString(4,chef.getTelefonoChef());
            ps.setBoolean(5, chef.isEstaDisponible());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de chef fallida, no se afectaron filas.", null);
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    chef.setIdChef(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Hubo un error al insertar un chef", e);
        }

    }

    @Override
    public Chef obtenerPorID(int idChef) throws DAOException {
        Chef chef = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.id.chef"))) {

            ps.setInt(1, idChef);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    chef = mappearChef(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el chef por ID.", e);
        }

        return chef;
    }

    @Override
    public List<Chef> obtenerTodos() throws DAOException {
        List<Chef> chefs = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("obtener.todos.chef"));
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Chef chef = mappearChef(rs);
                chefs.add(chef);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todas los chefs.", e);
        }

        return chefs;
    }

    @Override
    public void actualizar(Chef chef) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("actualizar.chef"))) {

            ps.setString(1, chef.getNombreChef());
            ps.setString(2, String.valueOf(chef.getEspecialidadChef()));
            ps.setInt(3, chef.getExperiencia());
            ps.setString(4,chef.getTelefonoChef());
            ps.setBoolean(5, chef.isEstaDisponible());
            ps.setInt(6,chef.getIdChef());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de chef fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el chef.", e);
        }
    }

    @Override
    public void eliminar(int idChef) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SentenciasSQL.getSentencia("eliminar.chef"))) {

            ps.setInt(1, idChef);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación del chef fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el chef.", e);
        }
    }

    public Connection obtenerConexion() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        return conexion.getConnection();
    }

    public Chef mappearChef(ResultSet rs) throws SQLException {
        int idChef = rs.getInt("ID_CHEF");
        String nombreChef = rs.getNString("NOMBRE_CHEF");
        EspecialidadChef especialidad = EspecialidadChef.valueOf(rs.getNString("ESPECIALIAD_CHEF").toUpperCase());
        int experiencia = rs.getInt("EXPERIENCIA");
        String telefonoChef = rs.getString("TELEFONO_CHEF");
        boolean disponible = rs.getBoolean("DISPONIBLE");

        return new Chef(idChef,nombreChef,especialidad,experiencia,telefonoChef,disponible);
    }
}
