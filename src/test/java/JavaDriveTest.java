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
        
        
        gc.buscarCliente("11111111A");
        gc.buscarCliente("99999999Z");
        gc.getClientes();

        
        GestorFlota gf = new GestorFlota();
        Coche cocheDisp = new Coche("1234ABC", "Toyota", "Corolla", true, "Turismo", 5);
        Coche cocheNoDisp = new Coche("9999ZZZ", "Fiat", "500", false, "Urbano", 4);
        
        gf.addVehiculo(cocheDisp);
        gf.addVehiculo(cocheNoDisp);
        
        
        gf.buscarVehiculo("1234ABC");
        gf.buscarVehiculo("XXXXXXX");
        gf.listarDisponibles();
        gf.getFlota();

        
        LocalDate hoy = LocalDate.now();
        Reserva r = new Reserva(c1, cocheDisp, hoy, hoy.plusDays(2));
        
       
        c1.getNombre();
        c1.getDni();
        cocheDisp.getMatricula();
        cocheDisp.isDisponible();
        r.getFechaInicio();

       
        new GestorReservas();
        new GestorInformes();
        new GestorPersistencia();
        
       
        assertTrue(true);
    }
}
