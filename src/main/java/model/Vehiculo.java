/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anton
 */
public abstract class Vehiculo {
    private String matricula; 
    private String marca; 
    private String modelo; 
    private boolean disponible; 

    public Vehiculo(String matricula, String marca, String modelo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.disponible = true; 
    }

    public String getMatricula() { return matricula; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public abstract String obtenerDetalles(); 

    @Override
    public String toString() {
        return "[" + matricula + "] " + marca + " " + modelo + " - " + (disponible ? "Disponible" : "Reservado"); 
    }
}
//ejemplo de commit