package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.Window;
import Managers.AccountsManager;
import Utilities.Visuals;
import java.awt.*;
import javax.swing.*;

public class BookingDetailsScreen extends Screen
{
    JLabel posterLabel;
    Label headingLabel;
    Label nameLabel;
    Label hallAndRoomLabel;
    Label dateLabel;
    Label timeLabel;
    Label seatsLabel;

    public BookingDetailsScreen()
    {
        super();

        // Components
        headingLabel = new Label( "Pssht... Your Booking Has Been Confirmed!", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.HEADING_FONT_SMALLER);
        JPanel movieDetailsPanel = new JPanel();
        JPanel detailsPanel = new JPanel();
        posterLabel = new JLabel("");
        Button okButton  = new Button( "Woohoo", new Dimension(350, 50));
        okButton.setFont(Visuals.Fonts.NORMAL_FONT);

        // Detail Labels
        Label bookDetailsLabel = new Label("Booking Confirmed! Don't forget your popcorn. Details below:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBTITLE_FONT);
        nameLabel = new Label("", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        hallAndRoomLabel = new Label("", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        dateLabel = new Label("", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        timeLabel = new Label("", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        seatsLabel = new Label("", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);

        // Layouts
        GridBagLayout layout = new GridBagLayout();

        // Other Configurations
        setLayout(layout);
        movieDetailsPanel.setLayout(layout);
        detailsPanel.setLayout(layout);
        movieDetailsPanel.setOpaque(false);
        detailsPanel.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        okButton.addActionListener(e -> { ((Window) SwingUtilities.getWindowAncestor(this)).openScreen("Movie Select"); });

        movieDetailsPanel.add(posterLabel, new GridBagSettings(0, 0, 0, 1, GridBagSettings.VERTICAL, new Insets(0, 0, 0, 5)));
        movieDetailsPanel.add(detailsPanel, new GridBagSettings(1, 0, 1, 1, GridBagSettings.BOTH, new Insets(0, 30, 0, 0)));

        detailsPanel.add(bookDetailsLabel, new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 20, 0)));
        detailsPanel.add(nameLabel, new GridBagSettings(0, 1, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 4, 0)));
        detailsPanel.add(hallAndRoomLabel, new GridBagSettings(0, 2, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 4, 0)));
        detailsPanel.add(dateLabel, new GridBagSettings(0, 3, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 4, 0)));
        detailsPanel.add(timeLabel, new GridBagSettings(0, 4, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 4, 0)));
        detailsPanel.add(seatsLabel, new GridBagSettings(0, 5, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 4, 0)));

        add(headingLabel, new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(25, 20, 10, 20)));
        add(movieDetailsPanel, new GridBagSettings(0, 1, 1, 1, GridBagSettings.BOTH, new Insets(0, 20, 0, 20)));
        add(okButton, new GridBagSettings(0, 2, 1, 1, GridBagSettings.BOTH, new Insets(10, 20, 10 ,20)));

        setName("Booking Details");
    }

    public void Setup(String movie, String venue, String date, String time, String seats, String poster)
    {
        headingLabel.setText("Pssht, " + AccountsManager.currentAccount.holderName.trim().split(" ")[0] + "... Your Ticket(s) Have Been Confirmed!");
        nameLabel.setText("Movie Name: " + movie);
        hallAndRoomLabel.setText("Venue: " + venue);
        dateLabel.setText("Show Date: " + date);
        timeLabel.setText("Show Time: " + time);
        seatsLabel.setText("Seat Number(s): " + seats);
        posterLabel.setIcon(new ImageIcon(poster));
    }
}
