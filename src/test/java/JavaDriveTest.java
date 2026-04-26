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
    private Cliente c2;
    private Coche cocheDisp;
    private Coche cocheNoDisp;
    private Furgoneta furgonetaCarga;
    private Furgoneta furgonetaPasajeros;
 
    @BeforeEach
    public void setUp() {
        gc = new GestorClientes();
        gf = new GestorFlota();
 
        c1 = new Cliente("11111111A", "Antonio", "600000001");
        c2 = new Cliente("22222222B", "Maria", "600000002");
        gc.addCliente(c1);
        gc.addCliente(c2);
 
        cocheDisp         = new Coche("1234ABC", "Toyota",   "Corolla", true,  "Turismo", 5);
        cocheNoDisp       = new Coche("9999ZZZ", "Fiat",     "500",     false, "Urbano",  4);
        furgonetaCarga    = new Furgoneta("FURG001", "Mercedes", "Sprinter", true,  true,  1000);
        furgonetaPasajeros= new Furgoneta("FURG002", "VW",       "Crafter",  true,  false, 9);
 
        gf.addVehiculo(cocheDisp);
        gf.addVehiculo(cocheNoDisp);
        gf.addVehiculo(furgonetaCarga);
        gf.addVehiculo(furgonetaPasajeros);
    }
 
    // =====================
    // Cliente
    // =====================
 
    @Test
    public void testClienteGetDni() {
        assertEquals("11111111A", c1.getDni());
    }
 
    @Test
    public void testClienteGetNombre() {
        assertEquals("Antonio", c1.getNombre());
    }
 
    @Test
    public void testClienteGetTelefono() {
        assertEquals("600000001", c1.getTelefono());
    }
 
    @Test
    public void testClienteToString() {
        String s = c1.toString();
        assertTrue(s.contains("Antonio"));
        assertTrue(s.contains("11111111A"));
        assertTrue(s.contains("600000001"));
    }
 
    // =====================
    // GestorClientes
    // =====================
 
    @Test
    public void testAddYGetClientes() {
        assertEquals(2, gc.getClientes().size());
    }
 
    @Test
    public void testBuscarClienteExistente() {
        Cliente encontrado = gc.buscarCliente("11111111A");
        assertNotNull(encontrado);
        assertEquals("Antonio", encontrado.getNombre());
    }
 
    @Test
    public void testBuscarClienteMayusculasMinusculas() {
        assertNotNull(gc.buscarCliente("11111111a"));
    }
 
    @Test
    public void testBuscarClienteNoExistente() {
        assertNull(gc.buscarCliente("99999999Z"));
    }
 
    // =====================
    // Vehiculo / Coche
    // =====================
 
    @Test
    public void testCocheGetMatricula() {
        assertEquals("1234ABC", cocheDisp.getMatricula());
    }
 
    @Test
    public void testCocheGetMarca() {
        assertEquals("Toyota", cocheDisp.getMarca());
    }
 
    @Test
    public void testCocheGetModelo() {
        assertEquals("Corolla", cocheDisp.getModelo());
    }
 
    @Test
    public void testCocheIsDisponibleTrue() {
        assertTrue(cocheDisp.isDisponible());
    }
 
    @Test
    public void testCocheIsDisponibleFalse() {
        assertFalse(cocheNoDisp.isDisponible());
    }
 
    @Test
    public void testCocheSetDisponible() {
        cocheDisp.setDisponible(false);
        assertFalse(cocheDisp.isDisponible());
        cocheDisp.setDisponible(true);
        assertTrue(cocheDisp.isDisponible());
    }
 
    @Test
    public void testCocheObtenerDetalles() {
        String detalles = cocheDisp.obtenerDetalles();
        assertTrue(detalles.contains("Coche"));
        assertTrue(detalles.contains("5"));
    }
 
    @Test
    public void testCocheToStringDisponible() {
        String s = cocheDisp.toString();
        assertTrue(s.contains("1234ABC"));
        assertTrue(s.contains("Disponible"));
    }
 
    @Test
    public void testCocheToStringReservado() {
        String s = cocheNoDisp.toString();
        assertTrue(s.contains("Reservado"));
    }
 
    // =====================
    // Furgoneta
    // =====================
 
    @Test
    public void testFurgonetaCargaGetters() {
        assertEquals("FURG001", furgonetaCarga.getMatricula());
        assertEquals("Mercedes", furgonetaCarga.getMarca());
        assertEquals("Sprinter", furgonetaCarga.getModelo());
        assertTrue(furgonetaCarga.isDisponible());
    }
 
    @Test
    public void testFurgonetaObtenerDetallesCarga() {
        String detalles = furgonetaCarga.obtenerDetalles();
        assertTrue(detalles.contains("Carga"));
        assertTrue(detalles.contains("1000"));
    }
 
    @Test
    public void testFurgonetaObtenerDetallesPasajeros() {
        String detalles = furgonetaPasajeros.obtenerDetalles();
        assertTrue(detalles.contains("Pasajeros"));
        assertTrue(detalles.contains("9"));
    }
 
    @Test
    public void testFurgonetaToString() {
        String s = furgonetaCarga.toString();
        assertTrue(s.contains("FURG001"));
    }
 
    // =====================
    // GestorFlota
    // =====================
 
    @Test
    public void testGetFlota() {
        assertEquals(4, gf.getFlota().size());
    }
 
    @Test
    public void testBuscarVehiculoExistente() {
        assertNotNull(gf.buscarVehiculo("1234ABC"));
    }
 
    @Test
    public void testBuscarVehiculoMayusculasMinusculas() {
        assertNotNull(gf.buscarVehiculo("1234abc"));
    }
 
    @Test
    public void testBuscarVehiculoNoExistente() {
        assertNull(gf.buscarVehiculo("XXXXXXX"));
    }
 
    @Test
    public void testListarDisponibles() {
        assertDoesNotThrow(() -> gf.listarDisponibles());
    }
 
    // =====================
    // Reserva
    // =====================
 
    @Test
    public void testReservaNoNula() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertNotNull(r);
    }
 
    @Test
    public void testReservaGenerarTicketContieneNombre() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(r.generarLineaTicket().contains("Antonio"));
    }
 
    @Test
    public void testReservaGenerarTicketContieneDni() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(r.generarLineaTicket().contains("11111111A"));
    }
 
    @Test
    public void testReservaGenerarTicketContieneMarca() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(r.generarLineaTicket().contains("Toyota"));
    }
 
    @Test
    public void testReservaGenerarTicketContieneModelo() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(r.generarLineaTicket().contains("Corolla"));
    }
 
    @Test
    public void testReservaGenerarTicketContieneJavaDrive() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(5));
        assertTrue(r.generarLineaTicket().contains("JAVADRIVE"));
    }
 
    @Test
    public void testReservaConFurgoneta() {
        Reserva r = new Reserva(c2, furgonetaCarga, LocalDate.now(), LocalDate.now().plusDays(1));
        String ticket = r.generarLineaTicket();
        assertTrue(ticket.contains("Maria"));
        assertTrue(ticket.contains("Mercedes")); // marca en lugar de matrícula
    }
 
    // =====================
    // GestorReservas
    // =====================
 
    @Test
    public void testProcesarReservaExitosa() {
        GestorReservas gr = new GestorReservas();
        assertTrue(gr.procesarReserva(c1, cocheDisp));
        assertFalse(cocheDisp.isDisponible());
    }
 
    @Test
    public void testProcesarReservaClienteNulo() {
        GestorReservas gr = new GestorReservas();
        assertFalse(gr.procesarReserva(null, cocheDisp));
    }
 
    @Test
    public void testProcesarReservaVehiculoNulo() {
        GestorReservas gr = new GestorReservas();
        assertFalse(gr.procesarReserva(c1, null));
    }
 
    @Test
    public void testProcesarReservaVehiculoNoDisponible() {
        GestorReservas gr = new GestorReservas();
        assertFalse(gr.procesarReserva(c1, cocheNoDisp));
    }
 
    @Test
    public void testProcesarReservaAmbosNulos() {
        GestorReservas gr = new GestorReservas();
        assertFalse(gr.procesarReserva(null, null));
    }
 
    // =====================
    // GestorInformes
    // =====================
 
    @Test
    public void testGenerarInformeXMLSinExcepcion() {
        assertDoesNotThrow(() ->
            GestorInformes.generarInformeXML(gf.getFlota(), gc.getClientes())
        );
    }
 
    @Test
    public void testGenerarInformeXMLListasVacias() {
        assertDoesNotThrow(() ->
            GestorInformes.generarInformeXML(
                new GestorFlota().getFlota(),
                new GestorClientes().getClientes()
            )
        );
    }
 
    @Test
    public void testGenerarInformeXMLConFurgoneta() {
        GestorFlota gfSolo = new GestorFlota();
        gfSolo.addVehiculo(furgonetaCarga);
        assertDoesNotThrow(() ->
            GestorInformes.generarInformeXML(gfSolo.getFlota(), gc.getClientes())
        );
    }
 
    // =====================
    // GestorPersistencia
    // =====================
 
    @Test
    public void testCargarDatosSinFicheros() {
        GestorPersistencia gp = new GestorPersistencia();
        assertDoesNotThrow(() ->
            gp.cargarDatos(new GestorFlota(), new GestorClientes())
        );
    }
}
