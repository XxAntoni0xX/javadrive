package test;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import model.*;
import logica.*;
 
public class JavaDriveTest {
 
    private GestorClientes gc;
    private GestorFlota gf;
    private Cliente c1;
    private Coche cocheDisp;
    private Coche cocheNoDisp;
    private Furgoneta furgoneta;
 
    @BeforeEach
    public void setUp() {
        gc = new GestorClientes();
        gf = new GestorFlota();
 
        c1 = new Cliente("11111111A", "Antonio", "600000001");
        gc.addCliente(c1);
 
        cocheDisp = new Coche("1234ABC", "Toyota", "Corolla", true, "Turismo", 5);
        cocheNoDisp = new Coche("9999ZZZ", "Fiat", "500", false, "Urbano", 4);
        furgoneta = new Furgoneta("FURG001", "Mercedes", "Sprinter", true, true, 1000);
 
        gf.addVehiculo(cocheDisp);
        gf.addVehiculo(cocheNoDisp);
        gf.addVehiculo(furgoneta);
    }
 
    
 
    @Test
    public void testClienteGetters() {
        assertEquals("11111111A", c1.getDni());
        assertEquals("Antonio", c1.getNombre());
        assertEquals("600000001", c1.getTelefono());
    }
 
    @Test
    public void testClienteToString() {
        String s = c1.toString();
        assertNotNull(s);
        assertTrue(s.contains("Antonio"));
    }
 
   
 
    @Test
    public void testBuscarClienteExistente() {
        Cliente encontrado = gc.buscarCliente("11111111A");
        assertNotNull(encontrado);
        assertEquals("Antonio", encontrado.getNombre());
    }
 
    @Test
    public void testBuscarClienteNoExistente() {
        Cliente noEncontrado = gc.buscarCliente("99999999Z");
        assertNull(noEncontrado);
    }
 
    @Test
    public void testGetClientes() {
        assertEquals(1, gc.getClientes().size());
    }
 
    
 
    @Test
    public void testCocheGetters() {
        assertEquals("1234ABC", cocheDisp.getMatricula());
        assertEquals("Toyota", cocheDisp.getMarca());
        assertEquals("Corolla", cocheDisp.getModelo());
        assertTrue(cocheDisp.isDisponible());
    }
 
    @Test
    public void testCocheNoDisponible() {
        assertFalse(cocheNoDisp.isDisponible());
    }
 
    @Test
    public void testCocheObtenerDetalles() {
        String detalles = cocheDisp.obtenerDetalles();
        assertNotNull(detalles);
        assertTrue(detalles.contains("Coche"));
    }
 
    @Test
    public void testVehiculoSetDisponible() {
        cocheDisp.setDisponible(false);
        assertFalse(cocheDisp.isDisponible());
        cocheDisp.setDisponible(true);
        assertTrue(cocheDisp.isDisponible());
    }
 
    @Test
    public void testVehiculoToString() {
        String s = cocheDisp.toString();
        assertNotNull(s);
        assertTrue(s.contains("1234ABC"));
    }
 
    @Test
    public void testFurgonetaCarga() {
        assertEquals("FURG001", furgoneta.getMatricula());
        assertTrue(furgoneta.isDisponible());
        String detalles = furgoneta.obtenerDetalles();
        assertTrue(detalles.contains("Carga"));
    }
 
    @Test
    public void testFurgonetaPasajeros() {
        Furgoneta fp = new Furgoneta("FURG002", "VW", "Crafter", true, false, 9);
        String detalles = fp.obtenerDetalles();
        assertTrue(detalles.contains("Pasajeros"));
    }
 
   
 
    @Test
    public void testBuscarVehiculoExistente() {
        Vehiculo v = gf.buscarVehiculo("1234ABC");
        assertNotNull(v);
    }
 
    @Test
    public void testBuscarVehiculoNoExistente() {
        Vehiculo v = gf.buscarVehiculo("XXXXXXX");
        assertNull(v);
    }
 
    @Test
    public void testGetFlota() {
        assertEquals(3, gf.getFlota().size());
    }
 
    @Test
    public void testListarDisponibles() {
        assertDoesNotThrow(() -> gf.listarDisponibles());
    }
 
    
 
    @Test
    public void testReservaCreacion() {
        LocalDate hoy = LocalDate.now();
        Reserva r = new Reserva(c1, cocheDisp, hoy, hoy.plusDays(3));
        assertNotNull(r);
    }
 
    @Test
    public void testReservaGenerarTicket() {
        LocalDate hoy = LocalDate.now();
        Reserva r = new Reserva(c1, cocheDisp, hoy, hoy.plusDays(3));
        String ticket = r.generarLineaTicket();
        assertNotNull(ticket);
        assertTrue(ticket.contains("JAVADRIVE"));
        assertTrue(ticket.contains("Antonio"));
    }
 
    
 
    @Test
    public void testProcesarReservaExitosa() {
        GestorReservas gr = new GestorReservas();
        boolean resultado = gr.procesarReserva(c1, cocheDisp);
        assertTrue(resultado);
        assertFalse(cocheDisp.isDisponible());
    }
 
    @Test
    public void testProcesarReservaClienteNulo() {
        GestorReservas gr = new GestorReservas();
        boolean resultado = gr.procesarReserva(null, cocheDisp);
        assertFalse(resultado);
    }
 
    @Test
    public void testProcesarReservaVehiculoNoDisponible() {
        GestorReservas gr = new GestorReservas();
        boolean resultado = gr.procesarReserva(c1, cocheNoDisp);
        assertFalse(resultado);
    }
 
    
 
    @Test
    public void testGenerarInformeXML() {
        assertDoesNotThrow(() ->
            GestorInformes.generarInformeXML(gf.getFlota(), gc.getClientes())
        );
    }
 
    
 
    @Test
    public void testCargarDatosSinFicheros() {
        GestorPersistencia gp = new GestorPersistencia();
        GestorFlota gfVacio = new GestorFlota();
        GestorClientes gcVacio = new GestorClientes();
        assertDoesNotThrow(() -> gp.cargarDatos(gfVacio, gcVacio));
    }
}
