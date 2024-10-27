package es.cheste.utilidad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GestorRestaurante {

    private static final Logger LOGGER = LoggerFactory.getLogger(GestorRestaurante.class);
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
        int opcion;
        do {
            opcion = seleccionarOpcion();

            switch (opcion) {
                case 1, 2, 3, 4, 5, 6, 7:
                    ejecutarSentenciaTabla(opcion);
                    break;
                case 8:

                    break;

                case 9:
                    System.out.println(Mensajes.getMensaje("iniciar.salida.exitosa"));
                    seSale = Boolean.TRUE;
                    sc.close();
                    break;
                default:
                    System.err.println(Mensajes.getMensaje("iniciar.salida.error"));
                    seSale = Boolean.TRUE;
                    sc.close();

            }

        } while (!seSale && opcion != 0);
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

    private void ejecutarSentenciaTabla(int numTabla) {
        int sentencia = seleccionarSentencia();

        switch (sentencia) {
            case 1:
                break;
            case 2:
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
                System.err.println(Mensajes.getMensaje("iniciar.salida.error"));
                seSale = Boolean.TRUE;
                sc.close();
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

    private void insertarDato(int numTabla){
        //TODO implemetar forma para llamar a las tablas

    }

}
