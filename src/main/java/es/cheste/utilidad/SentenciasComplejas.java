package es.cheste.utilidad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;

public class SentenciasComplejas {

    private static final Logger LOGGER = LogManager.getLogger(SentenciasComplejas.class);

    public static void ejecutarCalcularTotalGasto() {
        try (Connection connection = new ConexionBD().getConnection();
             CallableStatement cs = connection.prepareCall(SentenciasSQL.getSentencia("calcular.total.gastos.cliente"))) {
            StringBuilder sb = new StringBuilder();
            boolean tieneResultados = cs.execute();
            while (tieneResultados) {
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {
                        String nombreCliente = rs.getString("NOMBRE_CLIENTE");
                        sb.append("El nombre del cliente ").append(nombreCliente);
                        double totalGastos = rs.getDouble("TOTAL_GASTOS");
                        sb.append(" y el total de gastos ").append(totalGastos).append("\n");
                    }
                }
                tieneResultados = cs.getMoreResults();
            }
            System.out.println(sb);
        } catch (SQLException e) {
            LOGGER.error("Error al ejecutar calcularTotalGasto: {}", e.getMessage());
            System.err.println("Hubo un error al intentar ejecutar la sentencia");
        }
    }

    public static void ejecutarMostrarDetallesPedidos(LocalDate fechaInicio, LocalDate fechaFin) {
        try (Connection connection = new ConexionBD().getConnection();
             CallableStatement cs = connection.prepareCall(SentenciasSQL.getSentencia("mostrar.detalles.pedidos.fecha"))) {
            StringBuilder sb = new StringBuilder();
            cs.setDate(1, Date.valueOf(fechaInicio));
            cs.setDate(2, Date.valueOf(fechaFin));

            boolean tieneResultados = cs.execute();
            while (tieneResultados) {
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {

                        int idPedido = rs.getInt("ID_PEDIDO");
                        sb.append("El ID del pedido ").append(idPedido);
                        Date fechaPedido = rs.getDate("FECHA_PEDIDO");
                        sb.append(" fecha del pedido ").append(fechaPedido.toString());
                        double precioTotal = rs.getDouble("PRECIO_TOTAL");
                        sb.append(" precio total del pedido ").append(precioTotal);
                        String nombreCliente = rs.getString("NOMBRE_CLIENTE");
                        sb.append(" el nombre del cliente ").append(nombreCliente);
                        int numeroMesa = rs.getInt("NUMERO_MESA");
                        sb.append(" el número de la mesa ").append(numeroMesa).append("\n");
                    }
                }
                tieneResultados = cs.getMoreResults();
            }
            System.out.println(sb);
        } catch (SQLException e) {
            LOGGER.error("Error al ejecutar mostarDetallesPedido: {}", e.getMessage());
            System.err.println("Hubo un error al intentar ejecutar la sentencia");
        }

    }
}
