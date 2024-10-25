package es.cheste.dao;

import es.cheste.entidad.Chef;
import es.cheste.utilidad.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ChefDAO {

    void insertar(Chef chef) throws DAOException;

    Chef obtenerPorID(int idChef) throws DAOException;

    List<Chef> obtenerTodos() throws DAOException;

    void actualizar(Chef chef) throws DAOException;

    void eliminar(int idChef) throws DAOException;

}
