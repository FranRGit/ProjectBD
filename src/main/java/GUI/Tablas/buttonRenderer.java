/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Tablas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class buttonRenderer extends JPanel implements TableCellRenderer {
    private JButton btnModificar;
    private JButton btnEliminar;

    public buttonRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Usamos FlowLayout para mejor control

        // Botón Modificar (amarillo con icono)
        btnModificar = new JButton("M");
        btnModificar.setBackground(new Color(242, 196, 109));
        btnModificar.setPreferredSize(new Dimension(40, 40)); // Tamaño ajustable
        btnModificar.setBorderPainted(false);
        btnModificar.setFocusPainted(false);

        // Botón Eliminar (rojo con icono)
        btnEliminar = new JButton("E");
        btnEliminar.setBackground(new Color(242, 169, 153));
        btnEliminar.setPreferredSize(new Dimension(40, 40)); // Tamaño ajustable
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);

        add(btnModificar);
        add(btnEliminar);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
