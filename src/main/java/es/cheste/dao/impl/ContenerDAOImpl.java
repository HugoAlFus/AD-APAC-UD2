package es.cheste.dao.impl;

import es.cheste.dao.ContenerDAO;
import es.cheste.entidad.Contener;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContenerDAOImpl implements ContenerDAO {

    private static final String INSERTAR = "INSERT INTO CONTENER (ID_PEDIDO, ID_PLATO, CANTIDAD, SUBTOTAL) VALUES (?,?,?,?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM CONTENER WHERE ID_PEDIDO=? AND ID_PLATO=?";
    private static final String OBTENER_TODOS = "SELECT * FROM CONTENER";
    private static final String ACTUALIZAR = "UPDATE CONTENER SET CANTIDAD=?, SUBTOTAL=? WHERE ID_PEDIDO=? AND ID_PLATO=?";
    private static final String ELIMINAR = "DELETE FROM CONTENER WHERE ID_PEDIDO=? AND ID_PLATO=?";

    @Override
    public void insertar(Contener contener) throws DAOException {
        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(INSERTAR)) {

            ps.setInt(1, contener.getIdPedido());
            ps.setInt(2, contener.getIdPlato());
            ps.setInt(3, contener.getCantidad());
            ps.setDouble(4,contener.getSubtotal());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de contener fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Hubo un error al insertar un contener", e);
        }
    }

    @Override
    public Contener obtenerPorID(int idPedido, int idPlato) throws DAOException {
        Contener contener = null;
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_POR_ID)) {

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

    @Override
    public List<Contener> obtenerTodos() throws DAOException {
        List<Contener> contenerList = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_TODOS);
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

    @Override
    public void actualizar(Contener contener) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ACTUALIZAR)) {

            ps.setInt(1, contener.getCantidad());
            ps.setInt(2, contener.getIdPedido());
            ps.setDouble(3,contener.getSubtotal());
            ps.setInt(4, contener.getIdPlato());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de contener fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el contener.", e);
        }
    }

    @Override
    public void eliminar(int idPedido, int idPlato) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ELIMINAR)) {

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

    public Connection obtenerConexion() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        return conexion.getConnection();
    }

    public Contener mappearContener(ResultSet rs) throws SQLException {
        int idPedido = rs.getInt("ID_PEDIDO");
        int idPlato = rs.getInt("ID_PLATO");
        int cantidad = rs.getInt("CANTIDAD");
        double subotal = rs.getDouble("SUBTOTAL");

        return new Contener(idPedido, idPlato, cantidad,subotal);
    }
}
