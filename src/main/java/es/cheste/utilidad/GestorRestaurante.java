package es.cheste.utilidad;

import es.cheste.entidad.enums.*;
import es.cheste.servicio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase GestorRestaurante
 * <p>
 * Esta clase gestiona las operaciones del restaurante, incluyendo la selección de opciones y la ejecución de sentencias
 * sobre diferentes tablas de la base de datos.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */

public class GestorRestaurante {

    private static final Logger LOGGER = LoggerFactory.getLogger(GestorRestaurante.class);
    private static final String[] TABLAS = {"PLATO", "CHEF", "CLIENTE", "MESA", "PEDIDO", "CONTENER", "REALIZAR"};
    private boolean estaIniciado = Boolean.TRUE;
    private boolean seSale = Boolean.FALSE;
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor de la clase GestorRestaurante.
     * <p>
     * Inicializa la base de datos y establece el estado de inicio.
     */
    public GestorRestaurante() {
        if (!GestorBD.iniciarBaseDatos()) {
            estaIniciado = Boolean.FALSE;
        }
    }

    /**
     * Método para iniciar el gestor del restaurante.
     * <p>
     * Este método muestra el menú principal y permite al usuario seleccionar opciones y ejecutar sentencias.
     */
    public void iniciarGestorRestaurante() {
        if (!estaIniciado) {
            System.err.println(obtenerTexto("iniciar.salida.error"));
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

    /**
     * Método para seleccionar una opción del menú principal.
     *
     * @return el número de la opción seleccionada.
     */
    private int seleccionarOpcion() {
        return seleccionarValor("iniciar.seleccionar.opcion", 1, 9);
    }

    /**
     * Método para seleccionar una sentencia a ejecutar.
     *
     * @return el número de la sentencia seleccionada.
     */
    private int seleccionarSentencia() {
        return seleccionarValor("iniciar.seleccionar.sentencia", 1, 6);
    }

    /**
     * Método para seleccionar un valor dentro de un rango.
     *
     * @param mensaje el mensaje a mostrar al usuario.
     * @param min     el valor mínimo permitido.
     * @param max     el valor máximo permitido.
     * @return el valor seleccionado
     */
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

    /**
     * Método para ejecutar una sentencia compleja.
     * <p>
     * Este método permite seleccionar y ejecutar sentencias complejas específicas.
     */
    private void ejecutarSentenciaCompleja() {

        int opcion = seleccionarValor("iniciar.seleccionar.sentencia.compleja", 1, 3);
        switch (opcion) {
            case 1 -> SentenciasComplejas.ejecutarCalcularTotalGasto();
            case 2 -> {
                LocalDate fechaInicio = obtenerFecha();
                LocalDate fechaFin = obtenerFecha();
                SentenciasComplejas.ejecutarMostrarDetallesPedidos(fechaInicio, fechaFin);
            }
            case 3 -> mostrarMensajeSalida(3);
        }

    }

    /**
     * Método para ejecutar una sentencia sobre una tabla específica.
     *
     * @param numTabla     el número de la tabla.
     * @param numSentencia el número de la sentencia.
     */
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

    /**
     * Método para ejecutar una sentencia sobre la tabla chef.
     *
     * @param sentencia el número de la sentencia.
     */
    private void ejecutarSentenciaChef(int sentencia) {
        ChefServicio servicio = new ChefServicio();
        switch (sentencia) {
            case 1 ->
                    servicio.agregarChef(obtenerNombre(), obtenerEspecialidadChef(), obtenerExperienciaChef(), obtenerTelefono(), obtenerDisponibilidadChef());
            case 2 -> System.out.println(servicio.obtenerChef(obtenerID(TABLAS[1])));
            case 3 -> servicio.lsitarChefs().forEach(System.out::println);
            case 4 ->
                    servicio.actualizarChef(obtenerID(TABLAS[1]), obtenerNombre(), obtenerEspecialidadChef(), obtenerExperienciaChef(), obtenerTelefono(), obtenerDisponibilidadChef());
            default -> servicio.eliminarChef(obtenerID(TABLAS[1]));
        }
    }

    /**
     * Método para ejecutar una sentencia sobre la tabla clientes.
     *
     * @param sentencia el número de la sentencia.
     */
    private void ejecutarSentenciasClientes(int sentencia) {
        ClienteServicio servicio = new ClienteServicio();
        switch (sentencia) {
            case 1 ->
                    servicio.agregarCliente(obtenerNombre(), obtenerTelefono(), obtenerCorreoElectronico(), obtenerDireccion());
            case 2 -> System.out.println(servicio.obtenerCliente(obtenerID(TABLAS[2])));
            case 3 -> servicio.listarClientes().forEach(System.out::println);
            case 4 ->
                    servicio.actualizarCliente(obtenerID(TABLAS[2]), obtenerNombre(), obtenerTelefono(), obtenerCorreoElectronico(), obtenerDireccion());
            default -> servicio.eliminarCliente(obtenerID(TABLAS[2]));
        }
    }

    /**
     * Método para ejecutar una sentencia sobre la tabla contener.
     *
     * @param sentencia el número de la sentencia.
     */
    private void ejecutarSentenciasContener(int sentencia) {
        ContenerServicio servicio = new ContenerServicio();
        switch (sentencia) {
            case 1 ->
                    servicio.agregarContener(obtenerID(TABLAS[4]), obtenerID(TABLAS[0]), obtenerCantidad(), obtenerSubtotal());
            case 2 -> System.out.println(servicio.obtenerContener(obtenerID(TABLAS[4]), obtenerID(TABLAS[0])));
            case 3 -> servicio.listarContener().forEach(System.out::println);
            case 4 ->
                    servicio.actualizarContener(obtenerID(TABLAS[4]), obtenerID(TABLAS[0]), obtenerCantidad(), obtenerSubtotal());
            default -> servicio.eliminarContener(obtenerID(TABLAS[4]), obtenerID(TABLAS[0]));
        }
    }

    /**
     * Método para ejecutar una sentencia sobre la tabla mesa.
     *
     * @param sentencia el número de la sentencia.
     */
    private void ejecutarSentenciasMesa(int sentencia) {
        MesaServicio servicio = new MesaServicio();
        switch (sentencia) {
            case 1 ->
                    servicio.agregarMesa(obtenerNumeroMesa(), obtenerCapacidadMesa(), obtenerUbicacionMesa(), obtenerEstadoMesa());
            case 2 -> System.out.println(servicio.obtenerMesa(obtenerID(TABLAS[3])));
            case 3 -> servicio.listarMesas().forEach(System.out::println);
            case 4 ->
                    servicio.actualizarMesa(obtenerID(TABLAS[3]), obtenerNumeroMesa(), obtenerCapacidadMesa(), obtenerUbicacionMesa(), obtenerEstadoMesa());
            default -> servicio.eliminarMesa(obtenerID(TABLAS[3]));
        }
    }

    /**
     * Método para ejecutar una sentencia sobre la tabla pedido.
     *
     * @param sentencia el número de la sentencia.
     */
    private void ejecutarSentenciasPedido(int sentencia) {
        PedidoServicio servicio = new PedidoServicio();
        switch (sentencia) {
            case 1 ->
                    servicio.agregarPedido(obtenerFecha(), obtenerPrecioTotal(), obtenerID(TABLAS[2]), obtenerID(TABLAS[3]), obtenerEstadoPedido());
            case 2 -> System.out.println(servicio.obtenerPedido(obtenerID(TABLAS[4])));
            case 3 -> servicio.listarPedidos().forEach(System.out::println);
            case 4 ->
                    servicio.actualizarPedido(obtenerID(TABLAS[4]), obtenerFecha(), obtenerPrecioTotal(), obtenerID(TABLAS[2]), obtenerID(TABLAS[3]), obtenerEstadoPedido());
            default -> servicio.eliminarPedido(obtenerID(TABLAS[4]));
        }
    }

    /**
     * Método para ejecutar una sentencia sobre la tabla plato.
     *
     * @param sentencia el número de la sentencia.
     */
    private void ejecutarSentenciasPlato(int sentencia) {
        PlatoServicio servicio = new PlatoServicio();
        switch (sentencia) {
            case 1 ->
                    servicio.agregarPlato(obtenerNombre(), obtenerDescripcionPlato(), obtenerPrecioPlato(), obtenerCategoriaPlato());
            case 2 -> System.out.println(servicio.obtenerPlato(obtenerID(TABLAS[0])));
            case 3 -> servicio.listarPlatos().forEach(System.out::println);
            case 4 ->
                    servicio.actualizarPlato(obtenerID(TABLAS[0]), obtenerNombre(), obtenerDescripcionPlato(), obtenerPrecioPlato(), obtenerCategoriaPlato());
            default -> servicio.eliminarPlato(obtenerID(TABLAS[0]));
        }
    }

    /**
     * Método para ejecutar una sentencia sobre la tabla realizar.
     *
     * @param sentencia el número de la sentencia.
     */
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

    /**
     * Método para obtener el ID de una tabla específica.
     *
     * @param tabla el nombre de la tabla.
     * @return el ID obtenido.
     */
    private int obtenerID(String tabla) {
        return obtenerValor("insertar.ID."+tabla.toLowerCase(), 0);
    }

    /**
     * Método para obtener el número de mesa.
     *
     * @return el número de la mesa.
     */
    private int obtenerNumeroMesa() {
        return obtenerValor("insertar.numero.mesa", 1);
    }

    /**
     * Método para obtener las personas que caben en una mesa.
     *
     * @return la capacidad de una mesa.
     */
    private int obtenerCapacidadMesa() {
        return obtenerValor("insertar.capacidad.mesa", 1);
    }

    /**
     * Método para obtener la cantidad de platos pedidos.
     *
     * @return la cantidad de platos.
     */
    private int obtenerCantidad() {
        return obtenerValor("insertar.cantidad", 1);
    }

    /**
     * Método para obtener la experiencia del chef en meses.
     *
     * @return la experiencia del chef en meses.
     */
    private int obtenerExperienciaChef() {
        return obtenerValor("insertar.experiencia.chef", 0);
    }

    /**
     * Método para obtener el precio de los platos pedidos.
     *
     * @return el valor de los platos en un pedido.
     */
    private double obtenerSubtotal() {
        return obtenerValorDouble("insertar.subtotal", 0);
    }

    /**
     * Método para obtener el precio total de un pedido.
     *
     * @return el valor total del pedido.
     */
    private double obtenerPrecioTotal() {
        return obtenerValorDouble("insertar.precio.total", 0);
    }

    /**
     * Método para obtener el precio de un plato en específico.
     *
     * @return el valor de un plato.
     */
    private double obtenerPrecioPlato() {
        return obtenerValorDouble("insertar.precio.plato", 0);
    }

    /**
     * Método para obtener un valor entero dentro de un rango.
     *
     * @param mensaje el mensaje a mostrar al usuario.
     * @param min     el valor mínimo permitido.
     * @return el valor seleccionado.
     */
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

    /**
     * Método para obtener un valor double dentro de un rango.
     *
     * @param mensaje el mensaje a mostrar al usuario.
     * @param min     el valor mínimo permitido.
     * @return el valor seleccionado.
     */
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
                LOGGER.error("Error al obtener valor double: {}", e.getMessage());
                return -1;
            }
        } while (valor < min);
        return valor;
    }

    /**
     * Método para obtener un nombre.
     *
     * @return el nombre obtenido.
     */
    private String obtenerNombre() {
        return obtenerTexto("insertar.nombre");
    }

    /**
     * Método para obtener un número de teléfono.
     *
     * @return el número de teléfono obtenido.
     */
    private String obtenerTelefono() {
        return obtenerTexto("insertar.telefono");
    }

    /**
     * Método para obtener un correo electrónico.
     *
     * @return el correo electrónico obtenido.
     */
    private String obtenerCorreoElectronico() {
        return obtenerTexto("insertar.correo.electronico");
    }

    /**
     * Método para obtener una dirección.
     *
     * @return la dirección obtenida.
     */
    private String obtenerDireccion() {
        return obtenerTexto("insertar.direccion");
    }

    /**
     * Método para obtener una descripción de un plato.
     *
     * @return la descripción del plato obtenida.
     */
    private String obtenerDescripcionPlato() {
        return obtenerTexto("insertar.descripcion.plato");
    }

    /**
     * Método para obtener la especialidad de un chef.
     *
     * @return la especialidad del chef.
     */
    private EspecialidadChef obtenerEspecialidadChef() {
        return obtenerEnum("insertar.especialidad.chef", EspecialidadChef.values());
    }

    /**
     * Método para obtener la ubicación de una mesa.
     *
     * @return la ubicación de la mesa.
     */
    private UbicacionMesa obtenerUbicacionMesa() {
        return obtenerEnum("insertar.ubicacion.mesa", UbicacionMesa.values());
    }

    /**
     * Método para obtener el estado de una mesa.
     *
     * @return el estado de la mesa.
     */
    private EstadoMesa obtenerEstadoMesa() {
        return obtenerEnum("insertar.estado.mesa", EstadoMesa.values());
    }

    /**
     * Método para obtener el estado de un pedido.
     *
     * @return el estado del pedido.
     */
    private EstadoPedido obtenerEstadoPedido() {
        return obtenerEnum("insertar.estado.pedido", EstadoPedido.values());
    }

    /**
     * Método para obtener la categoría de un plato.
     *
     * @return la categoría del plato.
     */
    private CategoriaPlato obtenerCategoriaPlato() {
        return obtenerEnum("insertar.categoria.plato", CategoriaPlato.values());
    }

    /**
     * Método genérico para obtener un valor de un enum.
     *
     * @param mensaje el mensaje a mostrar al usuario.
     * @param valores los valores del enum.
     * @param <T>     el tipo del enum.
     * @return el valor del enum seleccionado.
     */
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

    /**
     * Método para obtener una fecha.
     *
     * @return la fecha obtenida.
     */
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

    /**
     * Método para obtener la disponibilidad de un chef.
     *
     * @return la disponibilidad del chef.
     */
    private boolean obtenerDisponibilidadChef() {
        char respuesta;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.disponibilidad.chef"));
                respuesta = sc.next().toLowerCase().charAt(0);

                if (respuesta != 's' && respuesta != 'n') {
                    System.out.println(Mensajes.getMensaje("error.valor.valido"));
                }
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Error al obtener disponibilidad: {}", e.getMessage());
                return Boolean.FALSE;
            }
        } while (respuesta != 's' && respuesta != 'n');
        return respuesta == 's';
    }

    /**
     * Método para mostrar un mensaje de salida.
     * <p>
     * Este método muestra un mensaje de salida dependiendo de la opción seleccionada.
     * Si la opción es 9, se procede a eliminar los datos de la base de datos.
     * Finalmente, se establece el estado de salida a verdadero y se cierra el escáner.
     *
     * @param opcion la opción seleccionada.
     */
    private void mostrarMensajeSalida(int opcion) {
        if (opcion == 9 || opcion == 6 || opcion == 3) {
            System.out.println(Mensajes.getMensaje("iniciar.salida.exitosa"));

            if (opcion == 9) {
                eliminarDatosBD();
            }

        } else {
            System.err.println(Mensajes.getMensaje("iniciar.salida.error"));
        }
        seSale = Boolean.TRUE;
        sc.close();
    }

    /**
     * Método para eliminar todos los datos de la base de datos.
     * <p>
     * Este método solicita confirmación al usuario antes de proceder a eliminar todos los datos de la base de datos.
     * Si el usuario confirma, se llama al método `borrarBaseDatos` de la clase `GestorBD`.
     */
    private void eliminarDatosBD() {
        char respuesta;

        do {
            try {
                System.out.println(Mensajes.getMensaje("eliminar.datos.completo"));
                respuesta = sc.next().toLowerCase().charAt(0);

                if (respuesta != 's' && respuesta != 'n') {
                    System.out.println(Mensajes.getMensaje("error.valor.valido"));
                }

            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Error al introducir si se quiere borrar todos los datos: {}", e.getMessage());
                respuesta = ' ';
            }
        } while (respuesta != 's' && respuesta != 'n');

        if (respuesta == 's') {
            boolean exito = GestorBD.borrarBaseDatos();
            System.out.println(Mensajes.getMensaje(exito ? "eliminar.datos.completo.exito" : "iniciar.salida.error"));
        } else
            System.out.println(Mensajes.getMensaje("eliminar.datos.no.exito"));
    }

    /**
     * Método para obtener un texto a partir de un mensaje.
     *
     * @param mensaje el mensaje a mostrar al usuario.
     * @return el texto obtenido.
     */
    private String obtenerTexto(String mensaje) {
        try {
            System.out.println(Mensajes.getMensaje(mensaje));
            return sc.nextLine();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            LOGGER.error("Error al obtener texto: {}", e.getMessage());
            return null;
        }
    }
}