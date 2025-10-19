package GUI;

import java.awt.*;

public class GridBagSettings extends GridBagConstraints
{
    public GridBagSettings(int _gridX, int _gridY)
    {
        super();
        gridx = _gridX;
        gridy = _gridY;
    }

    public GridBagSettings(int _gridX, int _gridY, Insets _insets)
    {
        super();
        gridx = _gridX;
        gridy = _gridY;
        insets = _insets;
    }

    public GridBagSettings(int _gridX, int _gridY, int _weightX, int _weightY, int _fill, Insets _insets )
    {
        super();
        gridx = _gridX;
        gridy = _gridY;
        weightx = _weightX;
        weighty = _weightY;
        fill = _fill;
        insets = _insets;
    }

}
