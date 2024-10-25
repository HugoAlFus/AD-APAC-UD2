package es.cheste.dao.impl;

import es.cheste.dao.ChefDAO;
import es.cheste.entidad.Chef;
import es.cheste.utilidad.DAOException;

import java.util.List;

public class ChefDAOImp implements ChefDAO {
    @Override
    public void insertar(Chef chef) throws DAOException {

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
}
