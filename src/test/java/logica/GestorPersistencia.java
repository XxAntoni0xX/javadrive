package logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GestorPersistenciaTest {

    @Test
    public void testCargarDatosExito() throws Exception {
        Files.write(Paths.get("clientes.txt"), "22222222B;Juan;666777888".getBytes());
        Files.write(Paths.get("vehiculos.txt"), "COCHE;8888BBB;Seat;Leon;true;Compacto;5\nFURGONETA;7777CCC;Renault;Master;false;true;1500".getBytes());
        
        GestorFlota gf = new GestorFlota();
        GestorClientes gc = new GestorClientes();
        GestorPersistencia gp = new GestorPersistencia();
        
        gp.cargarDatos(gf, gc);
        
        assertEquals(1, gc.getClientes().size());
        assertEquals(2, gf.getFlota().size());
        
        new File("clientes.txt").delete();
        new File("vehiculos.txt").delete();
    }

    @Test
    public void testCargarDatosSinArchivos() {
        new File("clientes.txt").delete();
        new File("vehiculos.txt").delete();
        
        GestorFlota gf = new GestorFlota();
        GestorClientes gc = new GestorClientes();
        GestorPersistencia gp = new GestorPersistencia();
        
        assertDoesNotThrow(() -> gp.cargarDatos(gf, gc));
    }
}
