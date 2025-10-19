package GUI;

import Utilities.Visuals;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ScrollPane extends JScrollPane
{
    public ScrollPane(Component component, int verticalScrollbarState, int horizontalScrollbarState)
    {
        super(component, verticalScrollbarState, horizontalScrollbarState);

        setOpaque(false);
        setBorder(null);
        getViewport().setOpaque(false);

        getVerticalScrollBar().setBackground(Visuals.Colors.SCROLLBAR_BACKGROUND);
        getVerticalScrollBar().setUI(new WithoutArrowButtonScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Visuals.Colors.SCROLLBAR_THUMB;
                this.thumbHighlightColor = Visuals.Colors.SCROLLBAR_BORDER;
                this.scrollBarWidth = 10;
            }
        });
    }

}

class WithoutArrowButtonScrollBarUI extends BasicScrollBarUI {
    @Override
    protected JButton createDecreaseButton(int orientation)
    {
        return new ZeroSizeButton();
    }

    @Override protected JButton createIncreaseButton(int orientation)
    {
        return new ZeroSizeButton();
    }
}

class ZeroSizeButton extends JButton
{
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension();
    }
}