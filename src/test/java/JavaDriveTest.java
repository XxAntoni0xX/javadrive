package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import model.*;
import logica.*;

public class JavaDriveTest {

    @Test
    public void testCoberturaTotal() {
        
        GestorClientes gc = new GestorClientes();
        Cliente c1 = new Cliente("Antonio", "11111111A", "600000001");
        Cliente c2 = new Cliente("Juan", "22222222B", "600000002");
        
        gc.addCliente(c1);
        gc.addCliente(c2);
        
        
        assertNotNull(gc.buscarCliente("11111111A"));
        
        assertNull(gc.buscarCliente("99999999Z"));
        
        assertEquals(2, gc.getClientes().size());

        
        GestorFlota gf = new GestorFlota();
        Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, "Turismo", 5);
        
        Coche cocheNoDisp = new Coche("9999ZZZ", "Fiat", "500", false, "Urbano", 4);
        
        gf.addVehiculo(coche);
        gf.addVehiculo(cocheNoDisp);
        
        
        assertNotNull(gf.buscarVehiculo("1234ABC"));
        assertNull(gf.buscarVehiculo("0000XXX"));
        
        )
        gf.listarDisponibles();
        assertEquals(2, gf.getFlota().size());

        
        LocalDate hoy = LocalDate.now();
        Reserva r = new Reserva(c1, coche, hoy, hoy.plusDays(3));
        assertNotNull(r);

      
        assertNotNull(new GestorReservas());
        assertNotNull(new GestorInformes());
        assertNotNull(new GestorPersistencia());
    }
}
