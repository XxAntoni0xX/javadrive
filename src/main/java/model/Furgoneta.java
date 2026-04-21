/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anton
 */
public class Furgoneta extends Vehiculo{
    private boolean esDeCarga; 
    private int capacidad; 

    public Furgoneta(String matricula, String marca, String modelo, boolean disponible, boolean esDeCarga, int capacidad) {
        super(matricula, marca, modelo); 
        this.setDisponible(disponible);
        this.esDeCarga = esDeCarga;
        this.capacidad = capacidad;
    }

    @Override
    public String obtenerDetalles() {
        if (esDeCarga) {
            return "Furgoneta de Carga (" + capacidad + " kg)"; 
        } else {
            return "Furgoneta de Pasajeros (" + capacidad + " personas)"; 
        }
    }
}
