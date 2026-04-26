package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import model.*;
import logica.*;

public class JavaDriveTest {

    @Test
    public void testCoberturaTotal() throws Exception {
       
        
        Cliente cl = new Cliente("11111111A", "Antonio", "600111222"); 
        
        Coche co = new Coche("1234ABC", "Toyota", "Corolla", true, "Híbrido", 5);
       
        Furgoneta fu = new Furgoneta("5678DEF", "Ford", "Transit", true, true, 1000);

        
        GestorClientes gc = new GestorClientes();
        gc.addCliente(cl);
        assertNotNull(gc.buscarCliente("11111111A")); 
        assertNull(gc.buscarCliente("99999999Z"));    
        assertEquals(1, gc.getClientes().size());

       
        GestorFlota gf = new GestorFlota();
        gf.addVehiculo(co);
        gf.addVehiculo(fu);
        assertNotNull(gf.buscarVehiculo("1234ABC"));
        gf.listarDisponibles(); 

        
        GestorReservas gr = new GestorReservas();
        assertTrue(gr.procesarReserva(cl, co)); 
        assertFalse(gr.procesarReserva(null, fu)); 
        
        
        GestorInformes.generarInformeXML(gf.getFlota(), gc.getClientes());

        
        Files.write(Paths.get("clientes.txt"), "22222222B;Juan;666777888".getBytes());
        Files.write(Paths.get("vehiculos.txt"), "COCHE;9999XYZ;Seat;Leon;true;Compacto;5".getBytes());
        
        GestorPersistencia gp = new GestorPersistencia();
        gp.cargarDatos(gf, gc);

        
        assertNotNull(co.obtenerDetalles());
        assertNotNull(fu.obtenerDetalles());
        Reserva res = new Reserva(cl, co, LocalDate.now(), LocalDate.now().plusDays(3));
        assertNotNull(res.generarLineaTicket());
        
        
        new File("clientes.txt").delete();
        new File("vehiculos.txt").delete();
        new File("reporte_completo.xml").delete();
        new File("ticket_1234ABC.txt").delete();
    }
}
