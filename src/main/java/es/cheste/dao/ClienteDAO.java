package es.cheste.dao;

import es.cheste.entidad.Chef;
import es.cheste.entidad.Cliente;
import es.cheste.utilidad.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    void insertar(Cliente cliente) throws DAOException;

    Cliente obtenerPorID(int idCliente) throws DAOException;

    List<Cliente> obtenerTodos() throws DAOException;

    void actualizar(Cliente cliente) throws DAOException;

    void eliminar(int idCliente) throws DAOException;
}
