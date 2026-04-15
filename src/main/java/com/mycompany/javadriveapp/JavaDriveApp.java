/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.javadriveapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anton
 */
public class JavaDriveApp {

private static List<Vehiculo> flota = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        cargarDatos();
        
        int opcion = -1;
        do {
            try {
                System.out.println("\n--- SISTEMA JAVADRIVE ---");
                System.out.println("1. Listar vehículos disponibles");
                System.out.println("2. Realizar reserva (Generar Ticket)");
                System.out.println("3. Generar Informe XML (Bonus)");
                System.out.println("0. Salir y Guardar");
                System.out.print("Seleccione una opción: ");
                
                opcion = sc.nextInt();
                sc.nextLine(); 

                switch (opcion) {
                    case 1 -> listarDisponibles();
                    case 2 -> realizarReserva();
                    case 3 -> generarInformeXML();
                    case 0 -> guardarYSalir();
                    default -> System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Introduce un número válido.");
                sc.nextLine(); 
            }
        } while (opcion != 0);
    }

    
    public static void cargarDatos() {
        
        File fClientes = new File("clientes.txt");
        if (fClientes.exists()) {
            try (Scanner lector = new Scanner(fClientes)) {
                while (lector.hasNextLine()) {
                    String[] datos = lector.nextLine().split(";");
                    if (datos.length == 3) {
                        clientes.add(new Cliente(datos[0], datos[1], datos[2]));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("No se pudo leer el fichero de clientes.");
            }
        }

        
        File fVehiculos = new File("vehiculos.txt");
        if (fVehiculos.exists()) {
            try (Scanner lector = new Scanner(fVehiculos)) {
                while (lector.hasNextLine()) {
                    String[] d = lector.nextLine().split(";");
                    String tipo = d[0];
                    String mat = d[1];
                    String marca = d[2];
                    String modelo = d[3];
                    boolean disp = Boolean.parseBoolean(d[4]);

                    if (tipo.equalsIgnoreCase("COCHE")) {
                        flota.add(new Coche(mat, marca, modelo, disp, d[5], Integer.parseInt(d[6])));
                    } else if (tipo.equalsIgnoreCase("FURGONETA")) {
                        flota.add(new Furgoneta(mat, marca, modelo, disp, Boolean.parseBoolean(d[5]), Integer.parseInt(d[6])));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al cargar vehículos: " + e.getMessage());
            }
        }
    }

    public static void listarDisponibles() {
        System.out.println("\n--- VEHÍCULOS DISPONIBLES ---");
        for (Vehiculo v : flota) {
            if (v.isDisponible()) System.out.println(v);
        }
    }

    public static void realizarReserva() {
        System.out.print("DNI del cliente: ");
        String dni = sc.nextLine();
        Cliente c = buscarCliente(dni);

        System.out.print("Matrícula del vehículo: ");
        String mat = sc.nextLine();
        Vehiculo v = buscarVehiculo(mat);

        if (c != null && v != null && v.isDisponible()) {
            v.setDisponible(false); 
            Reserva r = new Reserva(c, v, LocalDate.now(), LocalDate.now().plusDays(5));
            
            
            try (PrintWriter pw = new PrintWriter("ticket_" + mat + ".txt")) {
                pw.println(r.generarLineaTicket());
                System.out.println("¡Reserva realizada! Ticket generado: ticket_" + mat + ".txt");
            } catch (IOException e) {
                System.out.println("Error al generar el ticket.");
            }
        } else {
            System.out.println("Error: Cliente no existe o vehículo no disponible.");
        }
    }

    
    public static void generarInformeXML() {
        try (PrintWriter pw = new PrintWriter("reporte_completo.xml")) {
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<?xml-stylesheet type=\"text/xsl\" href=\"estilo_javadrive.xsl\"?>");
            pw.println("<javadrive>");
            
            pw.println("  <empresa>");
            pw.println("    <nombre>JavaDrive SL</nombre>");
            pw.println("    <ubicacion>Sede Central Madrid</ubicacion>");
            pw.println("  </empresa>");

            pw.println("  <flota>");
            for (Vehiculo v : flota) {
                String tipoObj = (v instanceof Coche) ? "Coche" : "Furgoneta";
                pw.println("    <vehiculo tipo=\"" + tipoObj + "\">");
                pw.println("      <matricula>" + v.getMatricula() + "</matricula>");
                pw.println("      <marca_modelo>" + v.getMarca() + " " + v.getModelo() + "</marca_modelo>");
                pw.println("      <especificaciones>" + v.obtenerDetalles() + "</especificaciones>");
                pw.println("    </vehiculo>");
            }
            pw.println("  </flota>");

            pw.println("  <clientes>");
            for (Cliente cl : clientes) {
                pw.println("    <cliente>");
                pw.println("      <dni>" + cl.getDni() + "</dni>");
                pw.println("      <nombre>" + cl.getNombre() + "</nombre>");
                pw.println("      <telefono>" + cl.getTelefono() + "</telefono>");
                pw.println("    </cliente>");
            }
            pw.println("  </clientes>");
            
            pw.println("</javadrive>");
            System.out.println("Informe XML generado con éxito: reporte_completo.xml");
        } catch (IOException e) {
            System.out.println("Error al crear el XML.");
        }
    }

    private static Cliente buscarCliente(String dni) {
        for (Cliente c : clientes) if (c.getDni().equalsIgnoreCase(dni)) return c;
        return null;
    }

    private static Vehiculo buscarVehiculo(String mat) {
        for (Vehiculo v : flota) if (v.getMatricula().equalsIgnoreCase(mat)) return v;
        return null;
    }

    public static void guardarYSalir() {
       
        System.out.println("Saliendo del sistema...");
    }
}
    
