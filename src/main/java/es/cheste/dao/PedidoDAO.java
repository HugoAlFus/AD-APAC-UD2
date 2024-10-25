package es.cheste.dao;

import es.cheste.entidad.Pedido;
import es.cheste.utilidad.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PedidoDAO {

    void insertar(Pedido pedido) throws DAOException;

    Pedido obtenerPorID(int idPedido) throws DAOException;

    List<Pedido> obtenerTodos() throws DAOException;

    void actualizar(Pedido pedido) throws DAOException;

    void eliminar(int idPedido) throws DAOException;
}
