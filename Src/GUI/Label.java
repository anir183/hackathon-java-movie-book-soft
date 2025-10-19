package GUI;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel
{
    public Label(String text, Color foreGround, Font font)
    {
        super(text);
        setForeground(foreGround);
        setFont(font);
    }
}
