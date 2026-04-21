/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Fran
 */
public class GestorClientes {
    private List<Cliente> clientes = new ArrayList<>();

    public void addCliente(Cliente c) {
        clientes.add(c);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente buscarCliente(String dni) {
        for (Cliente c : clientes) {
            if (c.getDni().equalsIgnoreCase(dni)) return c;
        }
        return null;
    }
}
