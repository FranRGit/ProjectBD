/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Tablas;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
/**
 *
 * @author FrancisR
 */
public class CustomScrollBar extends BasicScrollBarUI {
    private static final int SCROLL_BAR_WIDTH = 8;

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(155, 155, 155); // Color del thumb
        this.trackColor = Color.WHITE; // Color del track
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(SCROLL_BAR_WIDTH, SCROLL_BAR_WIDTH);
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        return new Dimension(SCROLL_BAR_WIDTH, Integer.MAX_VALUE);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // No pintamos el track para que sea transparente
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }
        int w = thumbBounds.width;
        int h = thumbBounds.height;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(thumbColor);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, w, h, SCROLL_BAR_WIDTH, SCROLL_BAR_WIDTH);
        g2.dispose();
    }

}
