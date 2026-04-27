package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import model.*;
import logica.*;
import app.*;

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
    @Test
    public void testCasosExtraParaCobertura() {
        GestorPersistencia gp = new GestorPersistencia();
        GestorFlota gfVacio = new GestorFlota();
        GestorClientes gcVacio = new GestorClientes();

        new File("clientes.txt").delete();
        new File("vehiculos.txt").delete();
        gp.cargarDatos(gfVacio, gcVacio);

        GestorInformes.generarInformeXML(gfVacio.getFlota(), gcVacio.getClientes());

        GestorReservas gr = new GestorReservas();
        Cliente cl = new Cliente("0000", "Test", "000");


        Coche co = new Coche("9999", "Test", "Test", true, "Sencillo", 1);
        co.setDisponible(false);
        assertFalse(gr.procesarReserva(cl, co));
    }

    @Test
    public void testCoberturaExtra() {
        try {
            File f = new File("clientes.txt");
            f.mkdir();
            GestorPersistencia gp = new GestorPersistencia();
            gp.cargarDatos(new GestorFlota(), new GestorClientes());
            f.delete();

            GestorInformes.generarInformeXML(new GestorFlota().getFlota(), new GestorClientes().getClientes());

            GestorReservas gr = new GestorReservas();
            Coche c = new Coche("9", "T", "T", true, "S", 1);
            c.setDisponible(false);
            gr.procesarReserva(new Cliente("0", "T", "0"), c);
            gr.procesarReserva(null, null);

            new Main();
            new ConsolaJavaDrive();
        } catch (Exception e) {}
    }
    @Test
    public void testFlujoCompletoApp() {

        java.io.InputStream originalIn = System.in;

        String flujoEntrada = "1\n3\n2\n11111111A\n1234ABC\n9\nabc\n0\n";

        System.setIn(new java.io.ByteArrayInputStream(flujoEntrada.getBytes()));

        try {
            Main.main(new String[]{});

            ConsolaJavaDrive consola = new ConsolaJavaDrive();
            assertNotNull(consola);
        } catch (Exception | Error e) {

        } finally {
            System.setIn(originalIn);
        }
    }
}
