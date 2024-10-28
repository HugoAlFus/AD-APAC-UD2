package es.cheste.utilidad;

import es.cheste.entidad.Chef;
import es.cheste.entidad.Cliente;
import es.cheste.entidad.Contener;
import es.cheste.entidad.Mesa;
import es.cheste.entidad.enums.EspecialidadChef;
import es.cheste.entidad.enums.EstadoMesa;
import es.cheste.entidad.enums.UbicacionMesa;
import es.cheste.servicio.ChefServicio;
import es.cheste.servicio.ClienteServicio;
import es.cheste.servicio.ContenerServicio;
import es.cheste.servicio.MesaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            if (numTabla == 9) {
                System.out.println(Mensajes.getMensaje("iniciar.salida.exitosa"));
                seSale = Boolean.TRUE;
                sc.close();
                break;
            }

            if (numTabla == 0) {
                System.err.println(Mensajes.getMensaje("iniciar.salida.error"));
                seSale = Boolean.TRUE;
                sc.close();
                break;
            }

            if (numTabla != 8) {
                int sentencia = seleccionarSentencia();
                if (sentencia == 6) {
                    System.out.println(Mensajes.getMensaje("iniciar.salida.exitosa"));
                    seSale = Boolean.TRUE;
                    sc.close();
                    break;
                }

                if (sentencia == 0) {
                    System.err.println(Mensajes.getMensaje("iniciar.salida.error"));
                    seSale = Boolean.TRUE;
                    sc.close();
                    break;
                }

                ejecutarSentenciaTabla(numTabla, sentencia);
            }
        }
    }

    private int seleccionarOpcion() {

        int opcion;

        do {
            System.out.println(Mensajes.getMensaje("iniciar.seleccionar.opcion"));
            try {
                opcion = sc.nextInt();

                if (opcion <= 0 || opcion > 9) {
                    System.out.println(Mensajes.getMensaje("error.valor.valido"));
                }
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                opcion = 0;
                sc.nextLine();
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al insertar la opcion de tabla {}", e.getMessage());
                return 0;
            }
        } while (opcion <= 0 || opcion > 9);

        return opcion;
    }

    private void ejecutarSentenciaTabla(int numTabla, int numSentencia) {

        switch (numTabla) {
            case 1:
                ejecutarSentenciaChef(numSentencia);
                break;
            case 2:
                ejecutarSentenciasClientes(numSentencia);
                break;
            case 3:
                ejecutarSentenciasContener(numSentencia);
                break;
            case 4:
                ejecutarSentenciasMesa(numSentencia);
                break;
            case 5:
                break;
            case 6:
                System.out.println(Mensajes.getMensaje("iniciar.salida.exitosa"));
                seSale = Boolean.TRUE;
                sc.close();
                break;
            default:
                mostrarError("iniciar.salida.error");
        }

    }

    private void ejecutarSentenciaChef(int sentencia) {
        ChefServicio servicio = new ChefServicio();
        int id;
        String nombre;
        EspecialidadChef especialidad;
        int experiencia;
        String telefono;
        boolean disponibilidad;

        switch (sentencia) {
            case 1:
                nombre = obtenerNombre();
                especialidad = obtenerEspecialidadChef();
                experiencia = obtenerExperienciaChef();
                telefono = obtenerTelefono();
                disponibilidad = obtenerDisponibilidadChef();

                if (nombre != null && especialidad != null && experiencia != -1 && telefono != null) {
                    servicio.agregarChef(nombre, especialidad, experiencia, telefono, disponibilidad);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 2:
                id = obtenerID(TABLAS[1]);
                if (id != -1) {
                    System.out.println(servicio.obtenerChef(id));
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 3:
                List<Chef> listaChefs = servicio.lsitarChefs();
                listaChefs.forEach(System.out::println);
                break;
            case 4:
                id = obtenerID(TABLAS[1]);
                nombre = obtenerNombre();
                especialidad = obtenerEspecialidadChef();
                experiencia = obtenerExperienciaChef();
                telefono = obtenerTelefono();
                disponibilidad = obtenerDisponibilidadChef();

                if (id != -1 && nombre != null && especialidad != null && experiencia != -1 && telefono != null) {
                    servicio.actualizarChef(id, nombre, especialidad, experiencia, telefono, disponibilidad);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            default:
                id = obtenerID(TABLAS[1]);
                if (id != -1) {
                    servicio.eliminarChef(id);
                } else {
                    mostrarError("iniciar.salida.error");
                }
        }
    }

    private int seleccionarSentencia() {
        int opcion;

        do {
            System.out.println(Mensajes.getMensaje("iniciar.seleccionar.sentencia"));
            try {
                opcion = sc.nextInt();

                if (opcion <= 0 || opcion > 6) {
                    System.out.println(Mensajes.getMensaje("error.valor.valido"));
                }
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                opcion = 0;
                sc.nextLine();
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al insertar la opcion de la sentencia {}", e.getMessage());
                return 0;
            }
        } while (opcion <= 0 || opcion > 6);

        return opcion;
    }

    private int obtenerID(String tabla) {
        int ID;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.ID") + tabla);
                ID = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                ID = -1;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener el ID {}", e.getMessage());
                return -1;
            }
        } while (ID < 0);
        return ID;

    }

    private String obtenerNombre() {
        String nombre;
        try {
            System.out.println(Mensajes.getMensaje("insertar.nombre"));
            nombre = sc.nextLine();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            LOGGER.error("Hubo un error al obetner el nombre {}", e.getMessage());
            return null;
        }
        return nombre;
    }

    private EspecialidadChef obtenerEspecialidadChef() {
        int opcion;

        do {
            System.out.println(Mensajes.getMensaje("insertar.especialidad.chef"));
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                opcion = 0;
                sc.nextLine();
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener la especialiad del chef {}", e.getMessage());
                return null;
            }
        } while (opcion < 1 || opcion > 10);
        return EspecialidadChef.values()[opcion - 1];
    }

    private int obtenerExperienciaChef() {
        int experiencia;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.experiencia.chef"));
                experiencia = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                experiencia = -1;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener la experiencia del chef {}", e.getMessage());
                return -1;
            }
        } while (experiencia < 0);
        return experiencia;
    }

    private String obtenerTelefono() {
        String telefono;
        try {
            System.out.println(Mensajes.getMensaje("insertar.telefono"));
            telefono = sc.next();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            LOGGER.error("Hubo un error al obtener el teléfono {}", e.getMessage());
            return null;
        }
        return telefono;
    }

    private boolean obtenerDisponibilidadChef() {
        char respuesta;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.disponibilidad.chef"));
                respuesta = sc.next().toLowerCase().charAt(0);
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener la disponibilidad del chef {}", e.getMessage());
                return false;
            }
        } while (respuesta != 's' && respuesta != 'n');
        return respuesta == 's';
    }

    private void ejecutarSentenciasClientes(int sentencia) {
        ClienteServicio servicio = new ClienteServicio();
        int id;
        String nombre;
        String telefono;
        String correoElectronico;
        String direccion;

        switch (sentencia) {
            case 1:
                nombre = obtenerNombre();
                telefono = obtenerTelefono();
                correoElectronico = obtenerCorreoElectronico();
                direccion = obtenerDireccion();

                if (nombre != null && telefono != null && correoElectronico != null && direccion != null) {
                    servicio.agregarCliente(nombre, telefono, correoElectronico, direccion);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 2:
                id = obtenerID(TABLAS[2]);
                if (id != -1) {
                    System.out.println(servicio.obtenerCliente(id));
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 3:
                List<Cliente> listaClientes = servicio.listarClientes();
                listaClientes.forEach(System.out::println);
                break;
            case 4:
                id = obtenerID(TABLAS[2]);
                nombre = obtenerNombre();
                telefono = obtenerTelefono();
                correoElectronico = obtenerCorreoElectronico();
                direccion = obtenerDireccion();

                if (id != -1 && nombre != null && telefono != null && correoElectronico != null && direccion != null) {
                    servicio.actualizarCliente(id, nombre, telefono, correoElectronico, direccion);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            default:
                id = obtenerID("Cliente");
                if (id != -1) {
                    servicio.eliminarCliente(id);
                } else {
                    mostrarError("iniciar.salida.error");
                }
        }
    }

    private String obtenerCorreoElectronico() {
        String correoElectronico;
        try {
            System.out.println(Mensajes.getMensaje("insertar.correo.electronico"));
            correoElectronico = sc.nextLine();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            LOGGER.error("Hubo un error al obtener el correo electrónico {}", e.getMessage());
            return null;
        }
        return correoElectronico;
    }

    private String obtenerDireccion() {
        String direccion;
        try {
            System.out.println(Mensajes.getMensaje("insertar.direccion"));
            direccion = sc.nextLine();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            LOGGER.error("Hubo un error al obtener la dirección {}", e.getMessage());
            return null;
        }
        return direccion;
    }

    private void ejecutarSentenciasContener(int sentencia) {
        ContenerServicio servicio = new ContenerServicio();
        int pedidoId;
        int platoId;
        int cantidad;
        double subtotal;

        switch (sentencia) {
            case 1:
                pedidoId = obtenerID(TABLAS[4]);
                platoId = obtenerID(TABLAS[0]);
                cantidad = obtenerCantidad();
                subtotal = obtenerSubtotal();

                if (pedidoId != -1 && platoId != -1 && cantidad >= 0) {
                    servicio.agregarContener(pedidoId, platoId, cantidad, subtotal);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 2:
                pedidoId = obtenerID(TABLAS[4]);
                platoId = obtenerID(TABLAS[0]);
                if (pedidoId != -1 && platoId != -1) {
                    System.out.println(servicio.obtenerContener(pedidoId, platoId));
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 3:
                List<Contener> listaContener = servicio.listarContener();
                listaContener.forEach(System.out::println);
                break;
            case 4:
                pedidoId = obtenerID(TABLAS[4]);
                platoId = obtenerID(TABLAS[0]);
                cantidad = obtenerCantidad();
                subtotal = obtenerSubtotal();

                if (pedidoId != -1 && platoId != -1 && cantidad > 0) {
                    servicio.actualizarContener(pedidoId, platoId, cantidad, subtotal);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            default:
                pedidoId = obtenerID(TABLAS[4]);
                platoId = obtenerID(TABLAS[0]);

                if (pedidoId != -1 && platoId != -1) {
                    servicio.eliminarContener(pedidoId, platoId);
                } else {
                    mostrarError("iniciar.salida.error");
                }
        }
    }

    private int obtenerCantidad() {
        int cantidad;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.cantidad"));
                cantidad = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                cantidad = -1;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener la cantidad {}", e.getMessage());
                return -1;
            }
        } while (cantidad <= 0);
        return cantidad;
    }

    private double obtenerSubtotal() {
        double subtotal;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.subtotal"));
                subtotal = sc.nextDouble();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                subtotal = -1;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener el subtotal {}", e.getMessage());
                return -1;
            }
        } while (subtotal < 0);
        return subtotal;
    }

    private void ejecutarSentenciasMesa(int sentencia) {
        MesaServicio servicio = new MesaServicio();
        int id;
        int numero;
        int capacidad;
        UbicacionMesa ubicacion;
        EstadoMesa estado;

        switch (sentencia) {
            case 1:
                numero = obtenerNumeroMesa();
                capacidad = obtenerCapacidadMesa();
                ubicacion = obtenerUbicacionMesa();
                estado = obtenerEstadoMesa();

                if (numero != -1 && capacidad != -1 && ubicacion != null && estado != null) {
                    servicio.agregarMesa(numero, capacidad, ubicacion, estado);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 2:
                id = obtenerID(TABLAS[3]);
                if (id != -1) {
                    System.out.println(servicio.obtenerMesa(id));
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 3:
                List<Mesa> listaMesas = servicio.listarMesas();
                listaMesas.forEach(System.out::println);
                break;
            case 4:
                id = obtenerID(TABLAS[3]);
                numero = obtenerNumeroMesa();
                capacidad = obtenerCapacidadMesa();
                ubicacion = obtenerUbicacionMesa();
                estado = obtenerEstadoMesa();

                if (id != -1 && numero != -1 && capacidad != -1 && ubicacion != null && estado != null) {
                    servicio.actualizarMesa(id, numero, capacidad, ubicacion, estado);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            default:
                id = obtenerID(TABLAS[3]);
                if (id != -1) {
                    servicio.eliminarMesa(id);
                } else {
                    mostrarError("iniciar.salida.error");
                }
        }
    }

    private int obtenerNumeroMesa() {
        int numero;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.numero.mesa"));
                numero = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                numero = -1;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener el número de la mesa {}", e.getMessage());
                return -1;
            }
        } while (numero <= 0);
        return numero;
    }

    private int obtenerCapacidadMesa() {
        int capacidad;
        do {
            try {
                System.out.println(Mensajes.getMensaje("insertar.capacidad.mesa"));
                capacidad = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                capacidad = -1;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener la capacidad de la mesa {}", e.getMessage());
                return -1;
            }
        } while (capacidad <= 0);
        return capacidad;
    }

    private UbicacionMesa obtenerUbicacionMesa() {
        int opcion;
        do {
            System.out.println(Mensajes.getMensaje("insertar.ubicacion.mesa"));
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                opcion = 0;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener la ubicación de la mesa {}", e.getMessage());
                return null;
            }
        } while (opcion < 1 || opcion > UbicacionMesa.values().length);
        return UbicacionMesa.values()[opcion - 1];
    }

    private EstadoMesa obtenerEstadoMesa() {
        int opcion;
        do {
            System.out.println(Mensajes.getMensaje("insertar.estado.mesa"));
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Mensajes.getMensaje("error.valor.valido"));
                sc.nextLine();
                opcion = 0;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                LOGGER.error("Hubo un error al obtener el estado de la mesa {}", e.getMessage());
                return null;
            }
        } while (opcion < 1 || opcion > EstadoMesa.values().length);
        return EstadoMesa.values()[opcion - 1];
    }

    private void mostrarError(String key) {
        System.err.println(Mensajes.getMensaje(key));
        seSale = Boolean.TRUE;
        sc.close();
    }

}
