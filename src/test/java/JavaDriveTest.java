package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import model.Cliente; 

public class JavaDriveTest {

    @Test
    public void testCobertura() {
       
        Cliente c = new Cliente("Nombre", "DNI", "Telefono");
        
       
        assertNotNull(c);
    }
}
