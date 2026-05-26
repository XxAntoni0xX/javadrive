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
        
        new File("ticket_1234ABC.txt").delete();
    }

    @Test
    public void testProcesarReservaFalloCondiciones() {
        GestorReservas gr = new GestorReservas();
        Cliente cl = new Cliente("11111111A", "Antonio", "600111222");
        Coche coNoDisp = new Coche("9999XYZ", "Fiat", "500", false, "Urbano", 4);
        Coche coDisp = new Coche("1234ABC", "Toyota", "Corolla", true, "Híbrido", 5);
        
       
        assertFalse(gr.procesarReserva(cl, coNoDisp));
        
        
        assertFalse(gr.procesarReserva(null, coDisp));
    }

    @Test
    public void testProcesarReservaFalloIOException() {
        GestorReservas gr = new GestorReservas();
        Cliente cl = new Cliente("11111111A", "Antonio", "600111222");
        
        
        Coche coInvalido = new Coche("invalido\0/char", "Ford", "Fiesta", true, "Gasolina", 5);
        
        boolean resultado = gr.procesarReserva(cl, coInvalido);
        
        
        assertFalse(resultado);
    }
}
