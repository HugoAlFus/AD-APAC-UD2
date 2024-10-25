package es.cheste.dao.impl;

import es.cheste.dao.ChefDAO;
import es.cheste.entidad.Chef;
import es.cheste.utilidad.ConexionBD;
import es.cheste.utilidad.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChefDAOImp implements ChefDAO {

    private static final String INSERTAR = "INSERT INTO CHEF (NOMBRE_CHEF, ESPECIALIDAD_CHEF, EXPERIENCIA, TELEFONO_CHEF, DISPONIBLE) " +
            "VALUES (?,?,?,?,?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM CHEF WHERE ID_CHEF=?";
    private static final String OBTENER_DATOS = "SELECT * FROM CHEF";
    private static final String ACTUALIZAR = "UPDATE CHEF SET NOMBRE_CHEF=?, ESPECIALIDAD_CHEF=?, EXPERIENCIA=?, TELEFONO_CHEF=?, DISPONIBLE=? " +
            "WHERE ID_CHEF=?";
    private static final String ELIMINAR = "DELETE FROM CHEF WHERE ID_CHEF=?";

    @Override
    public void insertar(Chef chef) throws DAOException {
        try(Connection connection = obtenerConexion();
        )

    }

    @Override
    public Chef obtenerPorID(int idChef) throws DAOException {
        return null;
    }

    @Override
    public List<Chef> obtenerTodos() throws DAOException {
        return List.of();
    }

    @Override
    public void actualizar(Chef chef) throws DAOException {

    }

    @Override
    public void eliminar(int idChef) throws DAOException {

    }

    @Override
    public Connection obtenerConexion() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        return conexion.getConnection();
    }
}
