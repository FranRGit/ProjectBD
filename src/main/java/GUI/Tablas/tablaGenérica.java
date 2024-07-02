/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Tablas;

import Clases.Cliente;
import GUI.Principal;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;


/**
 *
 * @author USUARIO
 */

//CLASE GENÉRICA
public class tablaGenérica<T> {
    
    public void actualizarTabla(ArrayList<T> lista, String[] columnas, JTable tabla, String contexto) {
        DefaultTableModel modelo = new DefaultTableModel();
        Principal main = Principal.getInstance();
        for (String columna : columnas) {
            modelo.addColumn(columna);
        }
        modelo.addColumn("Acciones");

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (T elemento : lista) {
            Object datos[] = obtenerDatos(elemento);
            Object[] filaConBotones = new Object[datos.length + 1];
            System.arraycopy(datos, 0, filaConBotones, 0, datos.length);
            filaConBotones[datos.length] = "Acciones"; // Placeholder for buttons
            modelo.addRow(filaConBotones);
        }

        tabla.setModel(modelo);
        tabla.getColumn("Acciones").setCellRenderer(new opcRenderer());
        tabla.getColumn("Acciones").setCellEditor(new opcEditor(tabla,main,contexto));
    }

    private Object[] obtenerDatos(T elemento) {
        if (elemento instanceof Cliente) {
            Cliente cliente = (Cliente) elemento;
            return new Object[]{cliente.getClienteId(), cliente.getNombre(), cliente.getDireccion(), cliente.getEmail(), cliente.getTelefono(), cliente.getLineaCredito()};
        }
        return new Object[0];
    }

}
