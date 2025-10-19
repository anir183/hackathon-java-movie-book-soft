package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.Window;
import Managers.AccountsManager;
import Managers.GuiAppManager;
import Utilities.Visuals;
import java.awt.*;
import javax.swing.*;

public class MainScreen extends Screen
{
    Button auth_prevBook_button;
    public MainScreen()
    {
        super();

        // Components
        Label titleLabel = new Label( "Showtime Hub ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.TITLE_FONT);
        Label subTitleLabel = new Label( "Incredible cinematic experiences since 2006!", Visuals.Colors.TEXT_NORMAL_DARKER, Visuals.Fonts.SUBTITLE_FONT);
        Button promptButton = new Button( "Start Booking Now!", new Dimension(350, 50));
        auth_prevBook_button = new Button( "Authenticate", new Dimension(350, 50));
        ImageIcon logo = new ImageIcon("Assets/Branding/Logo.png");

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();

        // Layout config
        GridBagSettings titleLabelConstraints = new GridBagSettings(0, 0);
        GridBagSettings subTitleLabelConstraints = new GridBagSettings(0, 1);
        GridBagSettings promptButtonConstraints = new GridBagSettings(0, 2, new Insets(150, 0, 0, 0));
        GridBagSettings authButtonConstraints = new GridBagSettings(0, 3, new Insets(7, 0, 0, 0));

        // Other Configurations
        setLayout(mainLayout);
        titleLabel.setVerticalAlignment(JLabel.BOTTOM);
        titleLabel.setIcon(logo);
        titleLabel.setHorizontalTextPosition(Label.LEFT);
        subTitleLabel.setVerticalAlignment(JLabel.TOP);
        promptButton.addActionListener(e -> {
            ((Window) SwingUtilities.getWindowAncestor(this)).openScreen("Movie Select");
            GuiAppManager.movieSelectScreen.PopulateMovies();
        });
        auth_prevBook_button.addActionListener(e -> {
            if (AccountsManager.currentAccount == null)
            {
                GuiAppManager.isMainPanelAuth = true;
                GuiAppManager.StartAuthentication();
            }
            else
            {
                ((Window) SwingUtilities.getWindowAncestor(this)).openScreen("Previous Bookings");
                GuiAppManager.previousBookingsScreen.PopulateBookings(AccountsManager.currentAccount);
            }

        });

        setName("Main");
        add(titleLabel, titleLabelConstraints);
        add(subTitleLabel, subTitleLabelConstraints);
        add(promptButton, promptButtonConstraints);
        add(auth_prevBook_button, authButtonConstraints);
    }

    public void SwitchButtons()
    {
        auth_prevBook_button.setText("Previous Bookings");
    }
}
