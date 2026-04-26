package test;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
 
    // Ficheros temporales que crea GestorPersistencia
    private static final String FICHERO_CLIENTES  = "clientes.txt";
    private static final String FICHERO_VEHICULOS = "vehiculos.txt";
 
    @BeforeEach
    public void setUp() {
        gc = new GestorClientes();
        gf = new GestorFlota();
 
        c1 = new Cliente("11111111A", "Antonio", "600000001");
        c2 = new Cliente("22222222B", "Maria",   "600000002");
        gc.addCliente(c1);
        gc.addCliente(c2);
 
        cocheDisp         = new Coche("1234ABC",  "Toyota",   "Corolla",  true,  "Turismo", 5);
        cocheNoDisp       = new Coche("9999ZZZ",  "Fiat",     "500",      false, "Urbano",  4);
        furgonetaCarga    = new Furgoneta("FURG001", "Mercedes", "Sprinter", true,  true,  1000);
        furgonetaPasajeros= new Furgoneta("FURG002", "VW",       "Crafter",  true,  false, 9);
 
        gf.addVehiculo(cocheDisp);
        gf.addVehiculo(cocheNoDisp);
        gf.addVehiculo(furgonetaCarga);
        gf.addVehiculo(furgonetaPasajeros);
    }
 
    @AfterEach
    public void tearDown() {
        // Limpiar ficheros temporales creados durante los tests
        new File(FICHERO_CLIENTES).delete();
        new File(FICHERO_VEHICULOS).delete();
        new File("reporte_completo.xml").delete();
        new File("ticket_1234ABC.txt").delete();
        new File("ticket_FURG001.txt").delete();
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
        assertTrue(cocheNoDisp.toString().contains("Reservado"));
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
        assertTrue(furgonetaCarga.toString().contains("FURG001"));
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
        assertNotNull(new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3)));
    }
 
    @Test
    public void testReservaTicketContieneNombre() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(r.generarLineaTicket().contains("Antonio"));
    }
 
    @Test
    public void testReservaTicketContieneDni() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(r.generarLineaTicket().contains("11111111A"));
    }
 
    @Test
    public void testReservaTicketContieneMarca() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(r.generarLineaTicket().contains("Toyota"));
    }
 
    @Test
    public void testReservaTicketContieneModelo() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(r.generarLineaTicket().contains("Corolla"));
    }
 
    @Test
    public void testReservaTicketContieneJavaDrive() {
        Reserva r = new Reserva(c1, cocheDisp, LocalDate.now(), LocalDate.now().plusDays(5));
        assertTrue(r.generarLineaTicket().contains("JAVADRIVE"));
    }
 
    @Test
    public void testReservaConFurgoneta() {
        Reserva r = new Reserva(c2, furgonetaCarga, LocalDate.now(), LocalDate.now().plusDays(1));
        String ticket = r.generarLineaTicket();
        assertTrue(ticket.contains("Maria"));
        assertTrue(ticket.contains("Mercedes"));
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
        assertFalse(new GestorReservas().procesarReserva(null, cocheDisp));
    }
 
    @Test
    public void testProcesarReservaVehiculoNulo() {
        assertFalse(new GestorReservas().procesarReserva(c1, null));
    }
 
    @Test
    public void testProcesarReservaVehiculoNoDisponible() {
        assertFalse(new GestorReservas().procesarReserva(c1, cocheNoDisp));
    }
 
    @Test
    public void testProcesarReservaAmbosNulos() {
        assertFalse(new GestorReservas().procesarReserva(null, null));
    }
 
    // =====================
    // GestorInformes
    // =====================
 
    @Test
    public void testGenerarInformeXMLCompleto() {
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
    public void testGenerarInformeXMLSoloFurgoneta() {
        GestorFlota gfSolo = new GestorFlota();
        gfSolo.addVehiculo(furgonetaCarga);
        assertDoesNotThrow(() ->
            GestorInformes.generarInformeXML(gfSolo.getFlota(), gc.getClientes())
        );
    }
 
    // =====================
    // GestorPersistencia — sin ficheros
    // =====================
 
    @Test
    public void testCargarDatosSinFicheros() {
        assertDoesNotThrow(() ->
            new GestorPersistencia().cargarDatos(new GestorFlota(), new GestorClientes())
        );
    }
 
    // =====================
    // GestorPersistencia — CON ficheros (cubre las ramas del if exists)
    // =====================
 
    @Test
    public void testCargarClientesDesdefichero() throws IOException {
        // Crear clientes.txt temporal con un cliente válido
        try (FileWriter fw = new FileWriter(FICHERO_CLIENTES)) {
            fw.write("Pedro;33333333C;600000003\n");
        }
        GestorClientes gcCargado = new GestorClientes();
        GestorFlota gfCargado = new GestorFlota();
        new GestorPersistencia().cargarDatos(gfCargado, gcCargado);
        assertEquals(1, gcCargado.getClientes().size());
        assertEquals("Pedro", gcCargado.getClientes().get(0).getNombre());
    }
 
    @Test
    public void testCargarClientesFicheroLineaIncompleta() throws IOException {
        // Línea con solo 2 campos — debe ignorarse (datos.length != 3)
        try (FileWriter fw = new FileWriter(FICHERO_CLIENTES)) {
            fw.write("SoloUnCampo\n");
            fw.write("Dos;Campos\n");
        }
        GestorClientes gcCargado = new GestorClientes();
        assertDoesNotThrow(() ->
            new GestorPersistencia().cargarDatos(gfCargado(gcCargado), gcCargado)
        );
        assertEquals(0, gcCargado.getClientes().size());
    }
 
    @Test
    public void testCargarVehiculosCocheDesdefichero() throws IOException {
        // Crear vehiculos.txt con un coche
        try (FileWriter fw = new FileWriter(FICHERO_VEHICULOS)) {
            fw.write("COCHE;AAA111;Seat;Ibiza;true;Turismo;5\n");
        }
        GestorFlota gfCargado = new GestorFlota();
        new GestorPersistencia().cargarDatos(gfCargado, new GestorClientes());
        assertEquals(1, gfCargado.getFlota().size());
        assertEquals("AAA111", gfCargado.getFlota().get(0).getMatricula());
    }
 
    @Test
    public void testCargarVehiculosFurgonetaDesdefichero() throws IOException {
        // Crear vehiculos.txt con una furgoneta
        try (FileWriter fw = new FileWriter(FICHERO_VEHICULOS)) {
            fw.write("FURGONETA;BBB222;Renault;Master;true;true;800\n");
        }
        GestorFlota gfCargado = new GestorFlota();
        new GestorPersistencia().cargarDatos(gfCargado, new GestorClientes());
        assertEquals(1, gfCargado.getFlota().size());
        assertEquals("BBB222", gfCargado.getFlota().get(0).getMatricula());
    }
 
    @Test
    public void testCargarVehiculosFicheroMalformado() throws IOException {
        // Línea malformada — debe capturar la excepción internamente sin lanzar
        try (FileWriter fw = new FileWriter(FICHERO_VEHICULOS)) {
            fw.write("TIPO_INVALIDO;XXX;marcaX;modeloX;true\n");
        }
        assertDoesNotThrow(() ->
            new GestorPersistencia().cargarDatos(new GestorFlota(), new GestorClientes())
        );
    }
 
    // Método auxiliar para el test de línea incompleta
    private GestorFlota gfCargado(GestorClientes gcCargado) {
        return new GestorFlota();
    }
}
