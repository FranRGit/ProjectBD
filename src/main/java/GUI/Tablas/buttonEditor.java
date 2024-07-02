/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Tablas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;

public class buttonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JTable table;

    public buttonEditor(JTable table) {
        this.table = table;
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Usamos FlowLayout para mejor control

        // Botón Modificar (amarillo con icono)
        btnModificar = new JButton("M");
        btnModificar.setBackground(new Color(242, 196, 109));
        btnModificar.setPreferredSize(new Dimension(40, 40)); // Tamaño ajustable
        btnModificar.setBorderPainted(false);
        btnModificar.setFocusPainted(false);
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Botón Eliminar (rojo con icono)
        btnEliminar = new JButton("E");
        btnEliminar.setBackground(new Color(242, 169, 153));
        btnEliminar.setPreferredSize(new Dimension(40, 40)); // Tamaño ajustable
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        panel.add(btnModificar);
        panel.add(btnEliminar);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            panel.setBackground(new Color(242,242,242));
            
        } else {
            panel.setBackground(new Color(242,242,242));
        }
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

}
