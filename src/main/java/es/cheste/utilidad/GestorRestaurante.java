package es.cheste.utilidad;

import es.cheste.entidad.Chef;
import es.cheste.entidad.Cliente;
import es.cheste.entidad.enums.EspecialidadChef;
import es.cheste.servicio.ChefServicio;
import es.cheste.servicio.ClienteServicio;
import es.cheste.servicio.ContenerServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
                break;
            case 4:
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
                id = obtenerID(TABLAS[2]);
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
                id = obtenerID(TABLA);
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
                id = obtenerID(TABLA);
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
                id = obtenerID("Cliente");
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
                id = obtenerID("Cliente");
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

        switch (sentencia) {
            case 1:
                pedidoId = obtenerPedidoId();
                platoId = obtenerPlatoId();
                cantidad = obtenerCantidad();

                if (pedidoId != -1 && platoId != -1 && cantidad > 0) {
                    servicio.agregarContener(pedidoId, platoId, cantidad);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 2:
                id = obtenerID();
                if (id != -1) {
                    System.out.println(servicio.obtenerContener(id));
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            case 3:
                List<Contener> listaContener = servicio.listarContener();
                listaContener.forEach(System.out::println);
                break;
            case 4:
                id = obtenerID();
                pedidoId = obtenerPedidoId();
                platoId = obtenerPlatoId();
                cantidad = obtenerCantidad();

                if (id != -1 && pedidoId != -1 && platoId != -1 && cantidad > 0) {
                    servicio.actualizarContener(id, pedidoId, platoId, cantidad);
                } else {
                    mostrarError("iniciar.salida.error");
                }
                break;
            default:
                id = obtenerID();
                if (id != -1) {
                    servicio.eliminarContener(id);
                } else {
                    mostrarError("iniciar.salida.error");
                }
        }
    }



    private void mostrarError(String key) {
        System.err.println(Mensajes.getMensaje(key));
        seSale = Boolean.TRUE;
        sc.close();
    }

}
