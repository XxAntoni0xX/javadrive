package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.*;
import logica.*;

public class JavaDriveTest {

    @Test
    public void testCoberturaEfectiva() {
        
        Cliente cliente = new Cliente("Antonio", "12345678Z", "600000000");
        
       
        Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, "Turismo", 5);

        
        GestorClientes gc = new GestorClientes();
        GestorFlota gf = new GestorFlota();
        GestorReservas gr = new GestorReservas();

        
        gc.agregarCliente(cliente); 
        gf.agregarVehiculo(coche);
        
       
        assertNotNull(gc.obtenerClientes());
        assertNotNull(gf.obtenerVehiculos());
        
        
        Reserva reserva = new Reserva(cliente, coche);
        assertNotNull(reserva);
    }
}
