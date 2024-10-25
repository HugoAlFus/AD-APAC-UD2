package es.cheste.dao;

import es.cheste.entidad.Plato;
import es.cheste.utilidad.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlatoDAO {

    void insertar(Plato plato) throws DAOException;

    Plato obtenerPorID(int idPlato) throws DAOException;

    List<Plato> obtenerTodos() throws DAOException;

    void actualizar(Plato plato) throws DAOException;

    void eliminar(int idPlato) throws DAOException;

    Connection obtenerConexion() throws SQLException;
}
