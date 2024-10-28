package es.cheste.utilidad;

import es.cheste.entidad.*;
import es.cheste.entidad.enums.*;
import es.cheste.servicio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GestorRestaurante {

    private static final Logger LOGGER = LoggerFactory.getLogger(GestorRestaurante.class);
    private static final String[] TABLAS = {"PLATO", "CHEF", "CLIENTE", "MESA", "PEDIDO", "CONTENER", "REALIZAR"};
    private boolean estaIniciado = Boolean.TRUE;
    private boolean seSale = Boolean.FALSE;
    private Scanner sc = new Scanner(System.in);

    public GestorRestaurante() {
        if (!GestorBD.iniciarBaseDatos()) {
            estaIniciado = Boolean.FALSE;
        }
    }

    public void iniciarGestorRestaurante() {
        if (!estaIniciado) {
            System.err.println(Mensajes.getMensaje("iniciar.salida.error"));
            return;
        }
        while (!seSale) {
            int numTabla = seleccionarOpcion();
            if (numTabla == 9 || numTabla == 0) {
                mostrarMensajeSalida(numTabla);
                break;
            }
            if (numTabla != 8) {
                int sentencia = seleccionarSentencia();
                if (sentencia == 6 || sentencia == 0) {
                    mostrarMensajeSalida(sentencia);
                    break;
                }
                ejecutarSentenciaTabla(numTabla, sentencia);
            } else ejecutarSentenciaCompleja();
        }
    }

    private int seleccionarOpcion() {
        return seleccionarValor("iniciar.seleccionar.opcion", 1, 9);
    }

    private int seleccionarSentencia() {
        return seleccionarValor("iniciar.seleccionar.sentencia", 1, 6);
    }

    private int seleccionarValor(String mensaje, int min, int max) {
        int valor;
        do {
            System.out.println(Mensajes.getMensaje(mensaje));
            try {
                valor = sc.nextInt();
                if (valor < min || valor > max) {
                    System.out.println(Mensajes.getMensaje("error.valor.valido"));
                }
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                valor = 0;
                sc.nextLine();
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Error al insertar valor: {}", e.getMessage());
                return 0;
            }
        } while (valor < min || valor > max);
        return valor;
    }

    private void ejecutarSentenciaCompleja(){

    }

    private void ejecutarSentenciaTabla(int numTabla, int numSentencia) {
        switch (numTabla) {
            case 1 -> ejecutarSentenciaChef(numSentencia);
            case 2 -> ejecutarSentenciasClientes(numSentencia);
            case 3 -> ejecutarSentenciasContener(numSentencia);
            case 4 -> ejecutarSentenciasMesa(numSentencia);
            case 5 -> ejecutarSentenciasPedido(numSentencia);
            case 6 -> ejecutarSentenciasPlato(numSentencia);
            default -> ejecutarSentenciasRealizar(numSentencia);
        }
    }

    private void ejecutarSentenciaChef(int sentencia) {
        ChefServicio servicio = new ChefServicio();
        switch (sentencia) {
            case 1 -> servicio.agregarChef(obtenerNombre(), obtenerEspecialidadChef(), obtenerExperienciaChef(), obtenerTelefono(), obtenerDisponibilidadChef());
            case 2 -> System.out.println(servicio.obtenerChef(obtenerID(TABLAS[1])));
            case 3 -> servicio.lsitarChefs().forEach(System.out::println);
            case 4 -> servicio.actualizarChef(obtenerID(TABLAS[1]), obtenerNombre(), obtenerEspecialidadChef(), obtenerExperienciaChef(), obtenerTelefono(), obtenerDisponibilidadChef());
            default -> servicio.eliminarChef(obtenerID(TABLAS[1]));
        }
    }

    private void ejecutarSentenciasClientes(int sentencia) {
        ClienteServicio servicio = new ClienteServicio();
        switch (sentencia) {
            case 1 -> servicio.agregarCliente(obtenerNombre(), obtenerTelefono(), obtenerCorreoElectronico(), obtenerDireccion());
            case 2 -> System.out.println(servicio.obtenerCliente(obtenerID(TABLAS[2])));
            case 3 -> servicio.listarClientes().forEach(System.out::println);
            case 4 -> servicio.actualizarCliente(obtenerID(TABLAS[2]), obtenerNombre(), obtenerTelefono(), obtenerCorreoElectronico(), obtenerDireccion());
            default -> servicio.eliminarCliente(obtenerID(TABLAS[2]));
        }
    }

    private void ejecutarSentenciasContener(int sentencia) {
        ContenerServicio servicio = new ContenerServicio();
        switch (sentencia) {
            case 1 -> servicio.agregarContener(obtenerID(TABLAS[4]), obtenerID(TABLAS[0]), obtenerCantidad(), obtenerSubtotal());
            case 2 -> System.out.println(servicio.obtenerContener(obtenerID(TABLAS[4]), obtenerID(TABLAS[0])));
            case 3 -> servicio.listarContener().forEach(System.out::println);
            case 4 -> servicio.actualizarContener(obtenerID(TABLAS[4]), obtenerID(TABLAS[0]), obtenerCantidad(), obtenerSubtotal());
            default -> servicio.eliminarContener(obtenerID(TABLAS[4]), obtenerID(TABLAS[0]));
        }
    }

    private void ejecutarSentenciasMesa(int sentencia) {
        MesaServicio servicio = new MesaServicio();
        switch (sentencia) {
            case 1 -> servicio.agregarMesa(obtenerNumeroMesa(), obtenerCapacidadMesa(), obtenerUbicacionMesa(), obtenerEstadoMesa());
            case 2 -> System.out.println(servicio.obtenerMesa(obtenerID(TABLAS[3])));
            case 3 -> servicio.listarMesas().forEach(System.out::println);
            case 4 -> servicio.actualizarMesa(obtenerID(TABLAS[3]), obtenerNumeroMesa(), obtenerCapacidadMesa(), obtenerUbicacionMesa(), obtenerEstadoMesa());
            default -> servicio.eliminarMesa(obtenerID(TABLAS[3]));
        }
    }

    private void ejecutarSentenciasPedido(int sentencia) {
        PedidoServicio servicio = new PedidoServicio();
        switch (sentencia) {
            case 1 -> servicio.agregarPedido(obtenerFecha(), obtenerPrecioTotal(), obtenerID(TABLAS[2]), obtenerID(TABLAS[3]), obtenerEstadoPedido());
            case 2 -> System.out.println(servicio.obtenerPedido(obtenerID(TABLAS[4])));
            case 3 -> servicio.listarPedidos().forEach(System.out::println);
            case 4 -> servicio.actualizarPedido(obtenerID(TABLAS[4]), obtenerFecha(), obtenerPrecioTotal(), obtenerID(TABLAS[2]), obtenerID(TABLAS[3]), obtenerEstadoPedido());
            default -> servicio.eliminarPedido(obtenerID(TABLAS[4]));
        }
    }

    private void ejecutarSentenciasPlato(int sentencia) {
        PlatoServicio servicio = new PlatoServicio();
        switch (sentencia) {
            case 1 -> servicio.agregarPlato(obtenerNombre(), obtenerDescripcionPlato(), obtenerPrecioPlato(), obtenerCategoriaPlato());
            case 2 -> System.out.println(servicio.obtenerPlato(obtenerID(TABLAS[0])));
            case 3 -> servicio.listarPlatos().forEach(System.out::println);
            case 4 -> servicio.actualizarPlato(obtenerID(TABLAS[0]), obtenerNombre(), obtenerDescripcionPlato(), obtenerPrecioPlato(), obtenerCategoriaPlato());
            default -> servicio.eliminarPlato(obtenerID(TABLAS[0]));
        }
    }

    private void ejecutarSentenciasRealizar(int sentencia) {
        RealizarServicio servicio = new RealizarServicio();
        switch (sentencia) {
            case 1 -> servicio.agregarRealizar(obtenerID(TABLAS[0]), obtenerID(TABLAS[1]), obtenerFecha());
            case 2 -> System.out.println(servicio.obtenerRealizar(obtenerID(TABLAS[0]), obtenerID(TABLAS[1])));
            case 3 -> servicio.listarRealizaciones().forEach(System.out::println);
            case 4 -> servicio.actualizarRealizar(obtenerID(TABLAS[0]), obtenerID(TABLAS[1]), obtenerFecha());
            default -> servicio.eliminarRealizar(obtenerID(TABLAS[0]), obtenerID(TABLAS[1]));
        }
    }

    private int obtenerID(String tabla) {
        return obtenerValor("insertar.ID" + tabla, 0);
    }

    private int obtenerNumeroMesa() {
        return obtenerValor("insertar.numero.mesa", 1);
    }

    private int obtenerCapacidadMesa() {
        return obtenerValor("insertar.capacidad.mesa", 1);
    }

    private int obtenerCantidad() {
        return obtenerValor("insertar.cantidad", 1);
    }

    private int obtenerExperienciaChef() {
        return obtenerValor("insertar.experiencia.chef", 0);
    }

    private double obtenerSubtotal() {
        return obtenerValorDouble("insertar.subtotal", 0);
    }

    private double obtenerPrecioTotal() {
        return obtenerValorDouble("insertar.precio.total", 0);
    }

    private double obtenerPrecioPlato(){
        return obtenerValorDouble("insertar.precio.plato",0);
    }

    private int obtenerValor(String mensaje, int min) {
        int valor;
        do {
            try {
                System.out.println(Mensajes.getMensaje(mensaje));
                valor = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                valor = -1;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Error al obtener valor: {}", e.getMessage());
                return -1;
            }
        } while (valor < min);
        return valor;
    }

    private double obtenerValorDouble(String mensaje, double min) {
        double valor;
        do {
            try {
                System.out.println(Mensajes.getMensaje(mensaje));
                valor = sc.nextDouble();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                valor = -1;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Error al obtener valor: {}", e.getMessage());
                return -1;
            }
        } while (valor < min);
        return valor;
    }

    private String obtenerNombre() {
        return obtenerTexto("insertar.nombre");
    }

    private String obtenerTelefono() {
        return obtenerTexto("insertar.telefono");
    }

    private String obtenerCorreoElectronico() {
        return obtenerTexto("insertar.correo.electronico");
    }

    private String obtenerDireccion() {
        return obtenerTexto("insertar.direccion");
    }

    private String obtenerDescripcionPlato() {
        return obtenerTexto("insertar.descripcion.plato");
    }

    private String obtenerTexto(String mensaje) {
        try {
            System.out.println(Mensajes.getMensaje(mensaje));
            return sc.nextLine();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            LOGGER.error("Error al obtener texto: {}", e.getMessage());
            return null;
        }
    }

    private EspecialidadChef obtenerEspecialidadChef() {
        return obtenerEnum("insertar.especialidad.chef", EspecialidadChef.values());
    }

    private UbicacionMesa obtenerUbicacionMesa() {
        return obtenerEnum("insertar.ubicacion.mesa", UbicacionMesa.values());
    }

    private EstadoMesa obtenerEstadoMesa() {
        return obtenerEnum("insertar.estado.mesa", EstadoMesa.values());
    }

    private EstadoPedido obtenerEstadoPedido() {
        return obtenerEnum("insertar.estado.pedido", EstadoPedido.values());
    }

    private CategoriaPlato obtenerCategoriaPlato() {
        return obtenerEnum("insertar.categoria.plato", CategoriaPlato.values());
    }

    private <T extends Enum<T>> T obtenerEnum(String mensaje, T[] valores) {
        int opcion;
        do {
            System.out.println(Mensajes.getMensaje(mensaje));
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                opcion = 0;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Error al obtener opción enum {}", e.getMessage());
                return null;
            }
        } while (opcion < 1 || opcion > valores.length);
        return valores[opcion - 1];
    }

    private LocalDate obtenerFecha() {
        try {
            System.out.println(Mensajes.getMensaje("insertar.fecha"));
            return LocalDate.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println(Mensajes.getMensaje("error.valor.valido"));
            return null;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            LOGGER.error("Error al obtener fecha: {}", e.getMessage());
            return null;
        }
    }

    private boolean obtenerDisponibilidadChef() {
        char respuesta;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.disponibilidad.chef"));
                respuesta = sc.next().toLowerCase().charAt(0);
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Error al obtener disponibilidad: {}", e.getMessage());
                return Boolean.FALSE;
            }
        } while (respuesta != 's' && respuesta != 'n');
        return respuesta == 's';
    }

    private void mostrarMensajeSalida(int opcion) {
        if (opcion == 9 || opcion == 6) {
            System.out.println(Mensajes.getMensaje("iniciar.salida.exitosa"));
        } else {
            System.err.println(Mensajes.getMensaje("iniciar.salida.error"));
        }
        seSale = Boolean.TRUE;
        sc.close();
    }
}