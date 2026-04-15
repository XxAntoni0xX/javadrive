/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadriveapp;

/**
 *
 * @author anton
 */
public class Cliente {
    private String dni; 
    private String nombre; 
    private String telefono; 

    public Cliente(String dni, String nombre, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getDni() { return dni; } 
    public String getNombre() { return nombre; } 
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return nombre + " (DNI: " + dni + ", Telf: " + telefono + ")"; 
    }
}
