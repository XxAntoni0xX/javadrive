/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import model.Cliente;
import model.Coche;
import model.Furgoneta;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Fran
 */
public class GestorPersistencia {

    public void cargarDatos(GestorFlota gestorFlota, GestorClientes gestorClientes) {
      
        File fClientes = new File("clientes.txt");
        if (fClientes.exists()) {
            try (Scanner lector = new Scanner(fClientes)) {
                while (lector.hasNextLine()) {
                    String[] datos = lector.nextLine().split(";");
                    if (datos.length == 3) {
                        gestorClientes.addCliente(new Cliente(datos[0], datos[1], datos[2]));
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
                        gestorFlota.addVehiculo(new Coche(mat, marca, modelo, disp, d[5], Integer.parseInt(d[6])));
                    } else if (tipo.equalsIgnoreCase("FURGONETA")) {
                        gestorFlota.addVehiculo(new Furgoneta(mat, marca, modelo, disp, Boolean.parseBoolean(d[5]), Integer.parseInt(d[6])));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al cargar vehículos: " + e.getMessage());
            }
        }
    }
}
