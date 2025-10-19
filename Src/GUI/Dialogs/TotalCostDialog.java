package GUI.Dialogs;

import GUI.Button;
import GUI.GridBagSettings;
import GUI.Label;
import GUI.Window;
import Managers.BookingManager;
import Managers.GuiAppManager;
import Utilities.Visuals;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;

public class TotalCostDialog extends JDialog
{
    public TotalCostDialog(Window window, int[] ticketsPerSection, int[] costData)
    {
        super(window, "Booking Summary");

        AtomicBoolean isConfirmed = new AtomicBoolean(false);

        // Components
        Label mainLabel = new Label("Booking Summary", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        JPanel buttonsPanel = new JPanel();
        JPanel billPanel = new JPanel();
        JPanel R = new JPanel();
        JPanel L = new JPanel();
        JPanel perRowCostPanelL = new JPanel();
        JPanel perRowCostPanelR = new JPanel();
        JPanel taxesAndFeesPanelL = new JPanel();
        JPanel taxesAndFeesPanelR = new JPanel();
        JPanel finalCostPanel = new JPanel();
        Button cancelButton = new Button("Cancel", new Dimension(170, 35));
        Button confirmButton = new Button("Confirm", new Dimension(170, 35));

        addWindowListener(new WindowListener()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if (!isConfirmed.get()) GuiAppManager.StartBooking();
            }
            @Override
            public void windowClosed(WindowEvent e)
            {
                if (!isConfirmed.get()) GuiAppManager.StartBooking();
            }

            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        // Layouts
        GridBagLayout layout = new GridBagLayout();

        // Configurations
        setSize(400, 470);
        setLayout(layout);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(layout);
        getContentPane().setBackground(Visuals.Colors.DIALOG_BACKGROUND);

        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(confirmButton);

        billPanel.setOpaque(false);
        billPanel.setLayout(layout);
        R.setOpaque(false);
        R.setLayout(layout);
        L.setOpaque(false);
        L.setLayout(layout);
        billPanel.add(L, new GridBagSettings(0, 0, 1, 1, GridBagSettings.BOTH, new Insets(0, 0, 0, 2)));
        billPanel.add(R, new GridBagSettings(1, 0, 0, 1, GridBagSettings.BOTH, new Insets(0, 2, 0, 0)));

        perRowCostPanelL.setOpaque(false);
        perRowCostPanelR.setOpaque(false);
        perRowCostPanelL.setLayout(new GridLayout(4, 1));
        perRowCostPanelR.setLayout(new GridLayout(4, 1));
        perRowCostPanelL.setBorder(Visuals.Borders.INTERACTABLE_BORDER);
        perRowCostPanelR.setBorder(Visuals.Borders.INTERACTABLE_BORDER);
        taxesAndFeesPanelL.setOpaque(false);
        taxesAndFeesPanelR.setOpaque(false);
        taxesAndFeesPanelL.setLayout(new GridLayout(3, 1));
        taxesAndFeesPanelR.setLayout(new GridLayout(3, 1));
        taxesAndFeesPanelL.setBorder(Visuals.Borders.INTERACTABLE_BORDER);
        taxesAndFeesPanelR.setBorder(Visuals.Borders.INTERACTABLE_BORDER);
        L.add(perRowCostPanelL, new GridBagSettings(0, 0, 1, 1, GridBagSettings.BOTH, new Insets(0, 0, 2, 0)));
        L.add(taxesAndFeesPanelL, new GridBagSettings(0, 1, 1, 1, GridBagSettings.BOTH, new Insets(2, 0, 0, 0)));

        R.add(perRowCostPanelR, new GridBagSettings(0, 0, 1, 1, GridBagSettings.BOTH, new Insets(0, 0, 2, 0)));
        R.add(taxesAndFeesPanelR, new GridBagSettings(0, 1, 1, 1, GridBagSettings.BOTH, new Insets(2, 0, 0, 0)));

        finalCostPanel.setOpaque(false);
        finalCostPanel.setBorder(Visuals.Borders.INTERACTABLE_BORDER);

        cancelButton.addActionListener(e -> dispose());
        confirmButton.addActionListener(e -> {
            GuiAppManager.EndBooking();
            isConfirmed.set(true);
            dispose();
        });

        perRowCostPanelL.add(new Label( "   Front Row Seats (" + ticketsPerSection[0] + "x)", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        perRowCostPanelL.add(new Label( "   Normal Row Seats (" + ticketsPerSection[1] + "x)", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        perRowCostPanelL.add(new Label( "   Premium Row Seats (" + ticketsPerSection[2] + "x)", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        perRowCostPanelL.add(new Label( "   " + "Base Cost Total", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));

        perRowCostPanelR.add(new Label( "   $" + costData[0] + "   ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        perRowCostPanelR.add(new Label( "   $" + costData[1] + "   ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        perRowCostPanelR.add(new Label( "   $" + costData[2] + "   ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        perRowCostPanelR.add(new Label( "   $" + costData[3] + "   ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));

        taxesAndFeesPanelL.add(new Label( "   Tax (" + BookingManager.TAX + "%)", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        taxesAndFeesPanelL.add(new Label( "   Service (" + BookingManager.SERVICE_TAX + "%)", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        taxesAndFeesPanelL.add(new Label( "   Convenience Fee (" + BookingManager.CONVENIENCE_FEE_PERCENTAGE + "%)", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));

        taxesAndFeesPanelR.add(new Label( "   $" + costData[4] + "   ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        taxesAndFeesPanelR.add(new Label( "   $" + costData[5] + "   ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));
        taxesAndFeesPanelR.add(new Label( "   $" + costData[6] + "   ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));

        finalCostPanel.add(new Label( "Total Cost: $" + costData[7], Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT));

        add(mainLabel, new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(10, 15, 0 ,15)));
        add(billPanel, new GridBagSettings(0, 1, 1, 1, GridBagSettings.BOTH, new Insets(15, 15, 0 ,15)));
        add(finalCostPanel, new GridBagSettings(0, 2, 1, 0, GridBagSettings.HORIZONTAL, new Insets(4, 15, 10 ,15)));
        add(buttonsPanel, new GridBagSettings(0, 3, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 15, 10 ,15)));

        setVisible(true);
        setLocation(getLocation().x, getLocation().y + 20);
    }
}
