/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import model.Vehiculo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Fran
 */
public class GestorFlota {
    private List<Vehiculo> flota = new ArrayList<>();

    public void addVehiculo(Vehiculo v) {
        flota.add(v);
    }

    public List<Vehiculo> getFlota() {
        return flota;
    }

    public Vehiculo buscarVehiculo(String mat) {
        for (Vehiculo v : flota) {
            if (v.getMatricula().equalsIgnoreCase(mat)) return v;
        }
        return null;
    }

    public void listarDisponibles() {
        System.out.println("\n--- VEHÍCULOS DISPONIBLES ---");
        for (Vehiculo v : flota) {
            if (v.isDisponible()) System.out.println(v);
        }
    }
}