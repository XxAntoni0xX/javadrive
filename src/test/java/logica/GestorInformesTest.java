package logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Coche;
import model.Vehiculo;

public class GestorInformesTest {

    @Test
    public void testGenerarInformeXML() {
        List<Vehiculo> flota = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        
        flota.add(new Coche("1234ABC", "Toyota", "Corolla", true, "Híbrido", 5));
        clientes.add(new Cliente("11111111A", "Antonio", "600111222"));
        
        GestorInformes.generarInformeXML(flota, clientes);
        
        File archivoXml = new File("reporte_completo.xml");
        assertTrue(archivoXml.exists());
        
        archivoXml.delete();
    }
}
