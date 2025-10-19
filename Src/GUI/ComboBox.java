package GUI;

import Utilities.Visuals;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class ComboBox<E> extends JComboBox<E>
{
    public ComboBox()
    {
        super();
        setForeground(Visuals.Colors.TEXT_NORMAL);
        setBackground(Visuals.Colors.INTERACTABLE_DARKER);
        setFocusable(false);
        setPreferredSize(new Dimension(300, 35));
        setFont(Visuals.Fonts.SMALL_FONT);
        setUI(new ComboUI());
    }

    private class ComboUI extends BasicComboBoxUI
    {
        @Override
        protected ComboPopup createPopup()
        {
            BasicComboPopup pop = new BasicComboPopup(comboBox);
            pop.setBorder(BorderFactory.createEmptyBorder());
            pop.setFocusable(false);
            return pop;
        }

        @Override
        protected JButton createArrowButton()
        {
            return new ArrowButton();
        }

        @Override
        public void paint(Graphics graphics, JComponent jc)
        {
            super.paint(graphics, jc);
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            int width = getWidth();
            int height = getHeight();
            g2.setColor(Visuals.Colors.TEXT_NORMAL);
            g2.fillRect(2, height - 1, width - 4, 2);
            g2.dispose();
        }


        @Override
        public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus)
        {
            ListCellRenderer<? super Object> renderer = comboBox.getRenderer();
            Component c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
            c.setPreferredSize(new Dimension(200, 27));
            boolean shouldValidate = c instanceof JPanel;
            currentValuePane.paintComponent(g, c, comboBox, bounds.x, bounds.y, bounds.width, bounds.height, shouldValidate);
        }
    }

    private static class ArrowButton extends JButton
    {

        public ArrowButton()
        {
            setContentAreaFilled(false);
            setBorder(new EmptyBorder(0, 5, 10, 5));
        }

        @Override
        public void paint(Graphics graphics)
        {
            super.paint(graphics);
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            int size = 11;
            int x = (width - size) / 2;
            int y = (height - size) / 2 + 5;
            int[] positionsX = {x - 2, x + size, x - 1 + size / 2};
            int[] positionsY = {y - 4, y - 4, y + size - 5};
            g2.setColor(Visuals.Colors.TEXT_NORMAL);
            g2.fillPolygon(positionsX, positionsY, positionsX.length);
            g2.dispose();
        }
    }
}
