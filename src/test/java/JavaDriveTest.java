package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import model.*;
import logica.*;

public class JavaDriveTest {

    @Test
    public void testSistemaCompleto() {
        
        GestorClientes gc = new GestorClientes();
        Cliente c1 = new Cliente("Antonio", "11111111A", "600000001");
        
        gc.addCliente(c1);
        
        
        assertNotNull(gc.buscarCliente("11111111A"));
        assertNull(gc.buscarCliente("99999999Z"));
        assertNotNull(gc.getClientes());

        
        GestorFlota gf = new GestorFlota();
        
        Coche cocheDisp = new Coche("1234ABC", "Toyota", "Corolla", true, "Turismo", 5);
        Coche cocheNoDisp = new Coche("9999ZZZ", "Fiat", "500", false, "Urbano", 4);
        
        gf.addVehiculo(cocheDisp);
        gf.addVehiculo(cocheNoDisp);
        
        
        assertNotNull(gf.buscarVehiculo("1234ABC"));
        assertNull(gf.buscarVehiculo("XXXXXXX"));
        
        
        gf.listarDisponibles();
        assertNotNull(gf.getFlota());

        
        LocalDate hoy = LocalDate.now();
        Reserva r = new Reserva(c1, cocheDisp, hoy, hoy.plusDays(2));
        assertNotNull(r);

        
        assertNotNull(new GestorReservas());
        assertNotNull(new GestorInformes());
        assertNotNull(new GestorPersistencia());
    }
}
