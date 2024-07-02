/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Tablas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author USUARIO
 */
public class tablaHeader extends JLabel{

    public tablaHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(new Color(54,54,54));
        setFont(new Font("sansserif",Font.BOLD ,14 ));
        setHorizontalAlignment(CENTER);
        setForeground(new Color(255,255,255));
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Ajusta el tamaño del borde según tus necesidades

    }
    
}
