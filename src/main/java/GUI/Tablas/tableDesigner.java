/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package GUI.Tablas;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class tableDesigner {
    public static void designTable(JTable table, JScrollPane jScrollPane) {
        //TABLA
        table.setBorder(new EmptyBorder(5,5, 5, 5));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setRowHeight(60);
        table.setGridColor(new Color(204,204,204));
        table.setIntercellSpacing(new Dimension(0, 0));

        //HEADER
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bin, boolean b1n1, int i, int i1) {
                tablaHeader header = new tablaHeader(o + "");
                if (i1 == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean b1n1, int row, int column) {
                Component com = super.getTableCellRendererComponent(jtable, o, isSelected, b1n1, row, column);

                if (isSelected) {
                    com.setBackground(new Color(242,242,242));
                    com.setForeground(table.getForeground());
                    setBorder(null);
                } else {
                    com.setBackground(table.getBackground());
                    com.setForeground(table.getForeground());
                    com.setBackground(new Color(242,242,242));
                }

                ((JLabel) com).setHorizontalAlignment(SwingConstants.CENTER);
                return com;
            }
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       jScrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());
       jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBar());

    }
    
    
}
