/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import logica.GestorClientes;
import logica.GestorFlota;
import logica.GestorPersistencia;
import logica.GestorReservas;
import logica.GestorInformes;
import model.Cliente;
import model.Vehiculo;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Fran
 */
public class ConsolaJavaDrive {

  
    private GestorFlota gestorFlota;
    private GestorClientes gestorClientes;
    private GestorPersistencia gestorPersistencia;
    private GestorReservas gestorReservas;
    private Scanner sc;

  
    public ConsolaJavaDrive() {
        this.gestorFlota = new GestorFlota();
        this.gestorClientes = new GestorClientes();
        this.gestorPersistencia = new GestorPersistencia();
        this.gestorReservas = new GestorReservas();
        this.sc = new Scanner(System.in);
    }

   
    public void iniciar() {
       
        gestorPersistencia.cargarDatos(gestorFlota, gestorClientes);

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
                    case 1 -> gestorFlota.listarDisponibles();
                    case 2 -> menuRealizarReserva();
                    case 3 -> GestorInformes.generarInformeXML(gestorFlota.getFlota(), gestorClientes.getClientes());
                    case 0 -> System.out.println("Saliendo del sistema... ¡Hasta pronto!");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Introduce un número válido.");
                sc.nextLine(); 
            }
        } while (opcion != 0);
    }

 
    private void menuRealizarReserva() {
        System.out.print("DNI del cliente: ");
        String dni = sc.nextLine();
        Cliente c = gestorClientes.buscarCliente(dni);

        System.out.print("Matrícula del vehículo: ");
        String mat = sc.nextLine();
        Vehiculo v = gestorFlota.buscarVehiculo(mat);

     
        gestorReservas.procesarReserva(c, v);
    }
}
