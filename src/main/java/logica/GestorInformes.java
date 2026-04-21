/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import model.Cliente;
import model.Coche;
import model.Vehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 *
 * @author Fran
 */
public class GestorInformes {

    public static void generarInformeXML(List<Vehiculo> flota, List<Cliente> clientes) {
        try (PrintWriter pw = new PrintWriter("reporte_completo.xml")) {
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<?xml-stylesheet type=\"text/xsl\" href=\"estilo_javadrive.xsl\"?>");
            pw.println("<javadrive>");
            
            pw.println("  <empresa>");
            pw.println("    <nombre>JavaDrive SL</nombre>");
            pw.println("    <ubicacion>Sede Central Madrid</ubicacion>");
            pw.println("  </empresa>");

            pw.println("  <flota>");
            for (Vehiculo v : flota) {
                String tipoObj = (v instanceof Coche) ? "Coche" : "Furgoneta";
                pw.println("    <vehiculo tipo=\"" + tipoObj + "\">");
                pw.println("      <matricula>" + v.getMatricula() + "</matricula>");
                pw.println("      <marca_modelo>" + v.getMarca() + " " + v.getModelo() + "</marca_modelo>");
                pw.println("      <especificaciones>" + v.obtenerDetalles() + "</especificaciones>");
                pw.println("    </vehiculo>");
            }
            pw.println("  </flota>");

            pw.println("  <clientes>");
            for (Cliente cl : clientes) {
                pw.println("    <cliente>");
                pw.println("      <dni>" + cl.getDni() + "</dni>");
                pw.println("      <nombre>" + cl.getNombre() + "</nombre>");
                pw.println("      <telefono>" + cl.getTelefono() + "</telefono>");
                pw.println("    </cliente>");
            }
            pw.println("  </clientes>");
            
            pw.println("</javadrive>");
            System.out.println("Informe XML generado con éxito: reporte_completo.xml");
        } catch (IOException e) {
            System.out.println("Error al crear el XML.");
        }
    }
}
