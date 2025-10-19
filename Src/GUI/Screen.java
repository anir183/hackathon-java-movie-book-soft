package GUI;

import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel
{
    public Screen()
    {
        super();
        setBorder(Visuals.Borders.SCREEN_BORDER);
    }

	@Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        GradientPaint gp =
                new GradientPaint(0, h, Visuals.Colors.GRADIENT_1, w - 200, 0, Visuals.Colors.GRADIENT_2);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
