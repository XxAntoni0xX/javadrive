package logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Coche;
import model.Furgoneta;

public class GestorFlotaTest {

    @Test
    public void testGestionVehiculos() {
        GestorFlota gf = new GestorFlota();
        Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, "Híbrido", 5);
        Furgoneta furgo = new Furgoneta("5678DEF", "Ford", "Transit", false, true, 1000);
        
        gf.addVehiculo(coche);
        gf.addVehiculo(furgo);
        
        assertNotNull(gf.buscarVehiculo("1234ABC"));
        assertNull(gf.buscarVehiculo("9999XYZ"));
        assertEquals(2, gf.getFlota().size());
    }

    @Test
    public void testListarDisponibles() {
        GestorFlota gf = new GestorFlota();
        Coche cocheDisp = new Coche("1234ABC", "Toyota", "Corolla", true, "Híbrido", 5);
        gf.addVehiculo(cocheDisp);
        
        gf.listarDisponibles();
        assertTrue(cocheDisp.isDisponible());
    }
}
