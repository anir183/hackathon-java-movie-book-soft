package GUI;

import Utilities.Visuals;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TextField extends JTextField
{
    public TextField()
    {
        super();
        setBackground(Visuals.Colors.INTERACTABLE_DARKER);
        setFont(Visuals.Fonts.NORMAL_FONT);
        setForeground(Visuals.Colors.TEXT_NORMAL);
        setBorder(BorderFactory.createCompoundBorder( Visuals.Borders.INTERACTABLE_BORDER, new EmptyBorder(new Insets(0, 10, 0, 10))));
        setPreferredSize(new Dimension(300, 35));
    }
}
