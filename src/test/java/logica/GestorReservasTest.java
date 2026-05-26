package logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import model.Cliente;
import model.Coche;

public class GestorReservasTest {

    @Test
    public void testProcesarReservaExito() {
        GestorReservas gr = new GestorReservas();
        Cliente cl = new Cliente("11111111A", "Antonio", "600111222");
        Coche co = new Coche("1234ABC", "Toyota", "Corolla", true, "Híbrido", 5);
        
        boolean resultado = gr.procesarReserva(cl, co);
        
       
        assertTrue(resultado);
        assertFalse(co.isDisponible()); 
        
        
        File ticket = new File("ticket_1234ABC.txt");
        if (ticket.exists()) {
            ticket.delete();
        }
    }

    @Test
    public void testProcesarReservaFalloVehiculoNoDisponible() {
        GestorReservas gr = new GestorReservas();
        Cliente cl = new Cliente("11111111A", "Antonio", "600111222");
        Coche coNoDisp = new Coche("9999XYZ", "Fiat", "500", false, "Urbano", 4);
        
        
        assertFalse(gr.procesarReserva(cl, coNoDisp));
    }

    @Test
    public void testProcesarReservaFalloParametrosNulos() {
        GestorReservas gr = new GestorReservas();
        Cliente cl = new Cliente("11111111A", "Antonio", "600111222");
        Coche coDisp = new Coche("1234ABC", "Toyota", "Corolla", true, "Híbrido", 5);
        
       
        assertFalse(gr.procesarReserva(null, coDisp));
        
        
        assertFalse(gr.procesarReserva(cl, null));
        
        
        assertFalse(gr.procesarReserva(null, null));
    }
}
