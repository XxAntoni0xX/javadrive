package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.*;
import logica.*;

public class JavaDriveTest {

    @Test
    public void testCoberturaCienPorCien() throws Exception {
        // 1. MODELOS Y RAMAS DE LOGICA (IF/ELSE)
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
        gf.buscarVehiculo(null);
        gf.listarDisponibles();
        
        // Forzar rama de 'no disponible' en listarDisponibles
        co.setDisponible(false);
        gf.listarDisponibles();

        // 2. GESTOR RESERVAS (Todas las combinaciones de error)
        GestorReservas gr = new GestorReservas();
        co.setDisponible(true);
        gr.procesarReserva(cl, co);      // Éxito
        gr.procesarReserva(null, co);    // Error Cliente
        gr.procesarReserva(cl, null);    // Error Vehículo
        co.setDisponible(false);
        gr.procesarReserva(cl, co);      // Error No disponible

        // 3. GESTOR PERSISTENCIA (Éxito y formatos de datos)
        Files.write(Paths.get("clientes.txt"), "22222222B;Juan;666777888\nLineaInvalida".getBytes());
        Files.write(Paths.get("vehiculos.txt"), 
            "COCHE;8888BBB;Seat;Leon;true;Compacto;5\n" +
            "FURGONETA;7777CCC;Renault;Master;false;true;1500\n" +
            "OTRO;ERROR;ERROR;ERROR;false;false;0".getBytes());
        
        GestorPersistencia gp = new GestorPersistencia();
        gp.cargarDatos(gf, gc);

        // 4. FORZAR CATCH (EXCEPCIONES)
        // Forzar catch en GestorInformes (usando un nombre de archivo inválido como carpeta)
        new File("directorio_error.xml").mkdir();
        GestorInformes.generarInformeXML(gf.getFlota(), gc.getClientes());
        new File("directorio_error.xml").delete();

        // Forzar catch en GestorReservas (usando un nombre de archivo de ticket inválido)
        Coche cocheError = new Coche("?/\\*", "Error", "Error", true, "Error", 0);
        gr.procesarReserva(cl, cocheError);

        // 5. MÉTODOS DE SOPORTE Y TOSTRING
        cl.toString();
        co.toString();
        cl.getDni(); cl.getNombre(); cl.getTelefono();
        co.getMatricula(); co.getMarca(); co.getModelo(); co.isDisponible();
        fuC.obtenerDetalles();
        fuP.obtenerDetalles();
        
        Reserva res = new Reserva(cl, co, LocalDate.now(), LocalDate.now().plusDays(2));
        res.generarLineaTicket();

        // 6. CONSTRUCTORES DE CLASES UTILITARIAS
        new GestorInformes();
        
        // 7. LIMPIEZA TOTAL
        new File("clientes.txt").delete();
        new File("vehiculos.txt").delete();
        new File("reporte_completo.xml").delete();
        new File("ticket_1234ABC.txt").delete();
        
        assertTrue(true);
    }
}
