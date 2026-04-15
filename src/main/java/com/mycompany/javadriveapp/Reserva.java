/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadriveapp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author anton
 */
public class Reserva {
    private String idReserva; 
    private Cliente cliente; 
    private Vehiculo vehiculo; 
    private LocalDate fechaInicio; 
    private LocalDate fechaFin; 

    public Reserva(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idReserva = "R" + System.currentTimeMillis() % 1000000; 
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String generarLineaTicket() {
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin); 
        return "===========================================\n" +
               "CONTRATO DE ALQUILER JAVADRIVE\n" +
               "ID RESERVA: " + idReserva + "\n" +
               "FECHA EMISIÓN: " + LocalDate.now() + "\n" +
               "DATOS DEL CLIENTE:\nNombre: " + cliente.getNombre() + "\nDNI: " + cliente.getDni() + "\n" +
               "DATOS DEL VEHÍCULO:\nMarca: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "\n" +
               "Detalles: " + vehiculo.obtenerDetalles() + "\n" + 
               "PERIODO: " + fechaInicio + " al " + fechaFin + " (" + dias + " días)\n" +
               "==========================================="; 
    }
}
