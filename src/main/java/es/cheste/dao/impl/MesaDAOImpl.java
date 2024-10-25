package es.cheste.dao.impl;

import es.cheste.dao.MesaDAO;
import es.cheste.entidad.Mesa;
import es.cheste.entidad.enums.EstadoMesa;
import es.cheste.entidad.enums.UbicacionMesa;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MesaDAOImpl implements MesaDAO {

    private static final String INSERTAR = "INSERT INTO MESA (NUMERO_MESA, CAPACIDAD, UBICACION_MESA, ESTADO_MESA) VALUES (?,?,?,?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM MESA WHERE ID_MESA=?";
    private static final String OBTENER_TODOS = "SELECT * FROM MESA";
    private static final String ACTUALIZAR = "UPDATE MESA SET NUMERO_MESA=?, CAPACIDAD=?, UBICACION_MESA=?, ESTADO_MESA=? WHERE ID_MESA=?";
    private static final String ELIMINAR = "DELETE FROM MESA WHERE ID_MESA=?";

    @Override
    public void insertar(Mesa mesa) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

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

    @Override
    public Mesa obtenerPorID(int idMesa) throws DAOException {
        Mesa mesa = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_POR_ID)) {

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

    @Override
    public List<Mesa> obtenerTodos() throws DAOException {
        List<Mesa> mesas = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_TODOS);
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

    @Override
    public void actualizar(Mesa mesa) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ACTUALIZAR)) {

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

    @Override
    public void eliminar(int idMesa) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ELIMINAR)) {

            ps.setInt(1, idMesa);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de la mesa fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la mesa.", e);
        }
    }

    public Connection obtenerConexion() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        return conexion.getConnection();
    }

    public Mesa mappearMesa(ResultSet rs) throws SQLException {
        int idMesa = rs.getInt("ID_MESA");
        int numeroMesa = rs.getInt("NUMERO_MESA");
        int capacidad = rs.getInt("CAPACIDAD");
        UbicacionMesa ubicacionMesa = UbicacionMesa.valueOf(rs.getString("UBICACION_MESA").toUpperCase());
        EstadoMesa estadoMesa = EstadoMesa.valueOf(rs.getString("ESTADO_MESA").toUpperCase());

        return new Mesa(idMesa, numeroMesa, capacidad, ubicacionMesa, estadoMesa);
    }
}