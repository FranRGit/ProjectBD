/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Tablas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

public class opcRenderer extends JPanel implements TableCellRenderer {
    private JPanel pnlModificar;
    private JPanel pnlEliminar;

    public opcRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); 
        // Panel Modificar (amarillo)
        pnlModificar = new JPanel();
        pnlModificar.setBackground(new Color(242, 196, 109));
        pnlModificar.setPreferredSize(new Dimension(40, 40)); 
        pnlModificar.setToolTipText("Modificar");
        JLabel lblModificar = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/edicion.png")));
        pnlModificar.add(lblModificar);
        
        // Panel Eliminar (rojo)
        pnlEliminar = new JPanel();
        pnlEliminar.setBackground(new Color(242, 169, 153));
        pnlEliminar.setPreferredSize(new Dimension(40, 40)); 
        pnlEliminar.setToolTipText("Eliminar");
        JLabel lblEliminar = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/eliminar.png")));
        pnlEliminar.add(lblEliminar);
        
        add(pnlModificar);
        add(pnlEliminar);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
