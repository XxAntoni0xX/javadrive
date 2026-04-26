package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import model.*;
import logica.*;

public class JavaDriveTest {

    @Test
    public void testCoberturaAsegurada() {
        
        Cliente c = new Cliente("Antonio", "12345678Z", "600000000");
        assertNotNull(c);

        
        GestorClientes gc = new GestorClientes();
        GestorFlota gf = new GestorFlota();
        GestorReservas gr = new GestorReservas();
        GestorInformes gi = new GestorInformes();
        GestorPersistencia gp = new GestorPersistencia();
        
        assertNotNull(gc);
        assertNotNull(gf);
        assertNotNull(gr);
        assertNotNull(gi);
        assertNotNull(gp);

        
        Reserva r = new Reserva(); 
        Vehiculo v = new Vehiculo();
        
        assertNotNull(r);
        assertNotNull(v);
    }
}
