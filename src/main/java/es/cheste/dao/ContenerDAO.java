package es.cheste.dao;

import es.cheste.entidad.Contener;
import es.cheste.utilidad.DAOException;

import java.util.List;

public interface ContenerDAO {

    void insertar(Contener contener) throws DAOException;

    Contener obtenerPorID(int idPedido, int idPlato) throws DAOException;

    List<Contener> obtenerTodos() throws DAOException;

    void actualizar(Contener contener) throws DAOException;

    void eliminar(int idPedido, int idPlato) throws DAOException;
}
