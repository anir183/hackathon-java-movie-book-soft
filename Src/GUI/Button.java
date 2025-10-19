package GUI;

import Utilities.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton
{
    public boolean isSelected;

    private Color pressedBackgroundColor;
    private boolean isDisabled;

    public Button(String text, Dimension dimensions)
    {
        super(text);
		setFocusPainted(false);
	    setFocusable(false);
	    setRolloverEnabled(false);
        setContentAreaFilled(false);
        setPreferredSize(dimensions);
        setMaximumSize(dimensions);
        setMinimumSize(dimensions);
        setSize(dimensions);
        setForeground(Visuals.Colors.TEXT_NORMAL);
        setBackground(Visuals.Colors.INTERACTABLE_NORMAL);
        setPressedBackgroundColor(Visuals.Colors.INTERACTABLE_INTERACTED);
        setBorder(Visuals.Borders.INTERACTABLE_BORDER);
        setFont(Visuals.Fonts.NORMAL_FONT);
    }

    public void setDisabled()
    {
        isSelected = false;
        isDisabled = true;
        setForeground(Visuals.Colors.TEXT_DARK);
        setBackground(Visuals.Colors.INTERACTABLE_DISABLED);
        setPressedBackgroundColor(Visuals.Colors.INTERACTABLE_DISABLED);
        setBorder(Visuals.Borders.INTERACTABLE_DIABLED_BORDER);
    }

    public void setSelected()
    {
        if (isDisabled) return;
        isSelected = true;
        setForeground(Visuals.Colors.TEXT_DARK);
        setBackground(Visuals.Colors.INTERACTABLE_SELECTED);
        setPressedBackgroundColor(Visuals.Colors.INTERACTABLE_SELECTED);
        setBorder(Visuals.Borders.INTERACTABLE_SELECTED_BORDER);
    }

    public void setNormal()
    {
        isSelected = false;
        isDisabled = false;
        setForeground(Visuals.Colors.TEXT_NORMAL);
        setBackground(Visuals.Colors.INTERACTABLE_NORMAL);
        setPressedBackgroundColor(Visuals.Colors.INTERACTABLE_INTERACTED);
        setBorder(Visuals.Borders.INTERACTABLE_BORDER);
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor)
    { this.pressedBackgroundColor = pressedBackgroundColor; }

    @Override
    public void addActionListener(ActionListener _listener)
    {
        if(isDisabled) return;
        super.addActionListener(_listener);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (getModel().isPressed()) g.setColor(pressedBackgroundColor);
        else g.setColor(getBackground());

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
