/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadriveapp;

/**
 *
 * @author anton
 */
public class Coche extends Vehiculo{
    private String tipo; 
    private int numPlazas; 

    public Coche(String matricula, String marca, String modelo, boolean disponible, String tipo, int numPlazas) {
        super(matricula, marca, modelo); 
        this.setDisponible(disponible);
        this.tipo = tipo;
        this.numPlazas = numPlazas;
    }

    @Override
    public String obtenerDetalles() {
        return "Coche " + tipo + ", Plazas: " + numPlazas; 
    }
}
