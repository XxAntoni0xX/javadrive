package logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Cliente;

public class GestorClientesTest {

    @Test
    public void testAddYBuscarCliente() {
        GestorClientes gc = new GestorClientes();
        Cliente cliente = new Cliente("11111111A", "Antonio", "600111222");
        
        gc.addCliente(cliente);
        
        
        Cliente encontrado = gc.buscarCliente("11111111A");
        assertNotNull(encontrado);
        assertEquals("Antonio", encontrado.getNombre());
        
        
        assertNull(gc.buscarCliente("22222222B"));
    }

    @Test
    public void testGetClientes() {
        GestorClientes gc = new GestorClientes();
        assertNotNull(gc.getClientes());
        assertTrue(gc.getClientes().isEmpty());
    }
}
