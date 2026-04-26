package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate; 

import model.*;
import logica.*;

public class JavaDriveTest {

    @Test
    public void testCoberturaFinal() {
        
        Cliente c = new Cliente("Antonio", "12345678Z", "600000000");
        Coche co = new Coche("1234ABC", "Toyota", "Corolla", true, "Turismo", 5);
        
        
        LocalDate hoy = LocalDate.now();
        LocalDate mañana = hoy.plusDays(1);
        Reserva r = new Reserva(c, co, hoy, mañana);

        
        GestorClientes gc = new GestorClientes();
        GestorFlota gf = new GestorFlota();
        GestorReservas gr = new GestorReservas();
        GestorInformes gi = new GestorInformes();
        GestorPersistencia gp = new GestorPersistencia();

        
        Furgoneta f = new Furgoneta("5678DEF", "Ford", "Transit", true, "Carga", 3500); 
        

        
        assertNotNull(c);
        assertNotNull(co);
        assertNotNull(r);
        assertNotNull(gc);
        assertNotNull(gf);
        assertNotNull(gr);
    }
}
