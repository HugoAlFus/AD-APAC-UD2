package es.cheste.dao;

import es.cheste.entidad.Realizar;
import es.cheste.utilidad.DAOException;

import java.util.List;

public interface RealizarDAO {

    void insertar(Realizar realizar) throws DAOException;

    Realizar obtenerPorDNI(int idPlato, int idChef) throws DAOException;

    List<Realizar> obtenerTodos() throws DAOException;

    void actualizar(Realizar realizar) throws DAOException;

    void eliminar(int idPlato, int idChef) throws DAOException;
}
