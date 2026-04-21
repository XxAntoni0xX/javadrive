/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import model.Cliente;
import model.Reserva;
import model.Vehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
/**
 *
 * @author Fran
 */
public class GestorReservas {

    public boolean procesarReserva(Cliente c, Vehiculo v) {
        if (c != null && v != null && v.isDisponible()) {
            v.setDisponible(false); 
            Reserva r = new Reserva(c, v, LocalDate.now(), LocalDate.now().plusDays(5));
            
            try (PrintWriter pw = new PrintWriter("ticket_" + v.getMatricula() + ".txt")) {
                pw.println(r.generarLineaTicket());
                System.out.println("¡Reserva realizada! Ticket generado: ticket_" + v.getMatricula() + ".txt");
                return true;
            } catch (IOException e) {
                System.out.println("Error al generar el ticket.");
            }
        } else {
            System.out.println("Error: Cliente no existe o vehículo no disponible.");
        }
        return false;
    }
}
