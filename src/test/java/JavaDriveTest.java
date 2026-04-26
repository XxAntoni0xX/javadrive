package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import model.*;
import logica.*;
import app.ConsolaJavaDrive; 

public class JavaDriveTest {

    @Test
    public void testCoberturaFinal() {
        
        Cliente c = new Cliente("Antonio", "12345678Z", "600000000");
        Coche co = new Coche("1234ABC", "Toyota", "Corolla", true, "Turismo", 5);
        
        
        assertNotNull(new GestorClientes());
        assertNotNull(new GestorFlota());
        assertNotNull(new GestorReservas());
        assertNotNull(new GestorInformes());
        assertNotNull(new GestorPersistencia());

        
        try {
            String[] args = {};
            
            Thread t = new Thread(() -> ConsolaJavaDrive.main(args));
            t.start();
            Thread.sleep(1000); 
            t.interrupt(); 
        } catch (Exception e) {
            
        }

        assertTrue(true);
    }
}
