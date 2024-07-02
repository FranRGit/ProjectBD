package GUI.Tablas;

import GUI.Clientes.ClientesMenu;
import GUI.Clientes.EliminarCliente;
import GUI.Clientes.ModificarCliente;
import GUI.Principal;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class opcEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JPanel pnlModificar;
    private JPanel pnlEliminar;
    private JTable table;
    private Principal parent; 
    private String context; 

    public opcEditor(JTable table, Principal parent, String context) {
        this.table = table;
        this.parent = parent;
        this.context = context; 
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); 

        // Panel Modificar (amarillo)
        pnlModificar = new JPanel();
        pnlModificar.setBackground(new Color(242, 196, 109));
        pnlModificar.setPreferredSize(new Dimension(40, 40)); 
        pnlModificar.setToolTipText("Modificar");       
        JLabel lblModificar = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/edicion.png")));
        pnlModificar.add(lblModificar);
        pnlModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openModal("modificar");
                stopCellEditing();
            }
        });
        
        // Panel Eliminar (rojo)
        pnlEliminar = new JPanel();
        pnlEliminar.setBackground(new Color(242, 169, 153));
        pnlEliminar.setPreferredSize(new Dimension(40, 40));
        pnlEliminar.setToolTipText("Eliminar");
        JLabel lblEliminar = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/eliminar.png")));
        pnlEliminar.add(lblEliminar);
        pnlEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openModal("eliminar");
                stopCellEditing();
            }
        });    
        panel.add(pnlModificar);
        panel.add(pnlEliminar);
    }

    private void openModal(String action) {
        switch (context) {
            case "menuCliente":
                if (action.equals("modificar")) {
                    ModificarCliente modificarCliente = new ModificarCliente(parent, true);
                    modificarCliente.setLocation(360, 167);
                    modificarCliente.setVisible(true);
                } else if (action.equals("eliminar")) {
                    EliminarCliente eliminarCliente = new EliminarCliente(parent,true);
                    eliminarCliente.setLocation(360,167);
                    eliminarCliente.setVisible(true);
                }
                break;
            case "menuOtro":
                if (action.equals("modificar")) {
                    // Abrir modal de modificación para otro menú
                    System.out.println("Modificar en menuOtro");
                } else if (action.equals("eliminar")) {
                    // Abrir modal de eliminación para otro menú
                    System.out.println("Eliminar en menuOtro");
                }
                break;
            // Añadir más casos según sea necesario
            default:
                System.out.println("Acción no definida para el contexto: " + context);
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            panel.setBackground(new Color(242, 242, 242));
        } else {
            panel.setBackground(new Color(242, 242, 242));
        }
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
