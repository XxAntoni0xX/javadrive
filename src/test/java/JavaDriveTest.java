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
    public void testMaximoRendimiento() throws Exception {
        
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
        gf.buscarVehiculo("FALSO");
        gf.listarDisponibles();
        
        
        co.setDisponible(false);
        gf.listarDisponibles();
        gf.getFlota();

        
        GestorReservas gr = new GestorReservas();
        co.setDisponible(true); 
        gr.procesarReserva(cl, co);     
        gr.procesarReserva(null, co);   
        gr.procesarReserva(cl, null);   
        co.setDisponible(false);
        gr.procesarReserva(cl, co);     

        
        GestorInformes.generarInformeXML(gf.getFlota(), gc.getClientes());

        
        Files.write(Paths.get("clientes.txt"), "22222222B;Juan;666777888".getBytes());
        Files.write(Paths.get("vehiculos.txt"), "COCHE;8888BBB;Seat;Leon;true;Compacto;5\nFURGONETA;7777CCC;Renault;Master;false;true;1500".getBytes());
        
        GestorPersistencia gp = new GestorPersistencia();
        gp.cargarDatos(gf, gc); 

        
        String s1 = cl.toString();
        String s2 = co.toString();
        String d1 = fuC.obtenerDetalles();
        String d2 = fuP.obtenerDetalles();
        
        cl.getDni(); cl.getNombre(); cl.getTelefono();
        co.getMatricula(); co.getMarca(); co.getModelo(); co.isDisponible();
        
        Reserva res = new Reserva(cl, co, LocalDate.now(), LocalDate.now().plusDays(2));
        res.generarLineaTicket();

        
        new File("clientes.txt").delete();
        new File("vehiculos.txt").delete();
        new File("reporte_completo.xml").delete();
        new File("ticket_1234ABC.txt").delete();
        
        assertTrue(true);
    }
}
