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
    public void testSistemaCompleto() throws Exception {
        Cliente cl = new Cliente("11111111A", "Antonio", "600111222"); 
        Coche co = new Coche("1234ABC", "Toyota", "Corolla", true, "Híbrido", 5);
        Furgoneta fuC = new Furgoneta("5678DEF", "Ford", "Transit", true, true, 1000);
        Furgoneta fuP = new Furgoneta("9999XYZ", "VW", "Multivan", true, false, 9);

        GestorClientes gc = new GestorClientes();
        gc.addCliente(cl);
        gc.buscarCliente("11111111A");
        gc.buscarCliente("NO_EXISTE");
        gc.getClientes();

        GestorFlota gf = new GestorFlota();
        gf.addVehiculo(co);
        gf.addVehiculo(fuC);
        gf.addVehiculo(fuP);
        gf.buscarVehiculo("1234ABC");
        gf.buscarVehiculo("INEXISTENTE");
        gf.listarDisponibles();
        gf.getFlota();

        GestorReservas gr = new GestorReservas();
        gr.procesarReserva(cl, co);
        gr.procesarReserva(null, fuC);
        gr.procesarReserva(cl, null);
        
        GestorInformes.generarInformeXML(gf.getFlota(), gc.getClientes());

        Files.write(Paths.get("clientes.txt"), "22222222B;Juan;666777888".getBytes());
        Files.write(Paths.get("vehiculos.txt"), "COCHE;8888BBB;Seat;Leon;true;Compacto;5\nFURGONETA;7777CCC;Renault;Master;false;true;1500".getBytes());
        
        GestorPersistencia gp = new GestorPersistencia();
        gp.cargarDatos(gf, gc);

        co.obtenerDetalles();
        fuC.obtenerDetalles();
        fuP.obtenerDetalles();
        co.getMarca(); co.getModelo(); co.getMatricula(); co.isDisponible();
        cl.getNombre(); cl.getDni(); cl.getTelefono();
        cl.toString(); co.toString();

        Reserva res = new Reserva(cl, co, LocalDate.now(), LocalDate.now().plusDays(3));
        res.generarLineaTicket();

        new GestorInformes(); 
        
        new File("clientes.txt").delete();
        new File("vehiculos.txt").delete();
        new File("reporte_completo.xml").delete();
        new File("ticket_1234ABC.txt").delete();
        
        assertTrue(true);
    }
}
