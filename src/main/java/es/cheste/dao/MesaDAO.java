package es.cheste.dao;

import es.cheste.entidad.Mesa;
import es.cheste.utilidad.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MesaDAO {

    void insertar(Mesa mesa) throws DAOException;

    Mesa obtenerPorDNI(int idMesa) throws DAOException;

    List<Mesa> obtenerTodos() throws DAOException;

    void actualizar(Mesa mesa) throws DAOException;

    void eliminar(int idMesa) throws DAOException;

    Connection obtenerConexion() throws SQLException;
}
