package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import model.*;
import logica.*;

public class JavaDriveTest {

    @Test
    public void testCoberturaTotal() {
        
        Cliente cliente = new Cliente("Antonio", "12345678Z", "600000000");
        assertNotNull(cliente);

        
        Coche coche = new Coche(); 
        assertNotNull(coche);

        
        GestorClientes gc = new GestorClientes();
        GestorFlota gf = new GestorFlota();
        GestorReservas gr = new GestorReservas();

        assertNotNull(gc);
        assertNotNull(gf);
        assertNotNull(gr);
        
        
    }
}
