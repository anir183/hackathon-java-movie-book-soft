package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.ScrollPane;
import Utilities.Account;
import Utilities.Booking;
import Utilities.Visuals;
import java.awt.*;
import javax.swing.*;

public class PreviousBookingsScreen extends Screen
{
    JPanel bookingsPanel;
    public PreviousBookingsScreen()
    {
        super();

        // Components
        Label headingLabel = new Label( "Previous Bookings!", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.HEADING_FONT);
        bookingsPanel = new JPanel();
        ScrollPane moviesScrollPane = new ScrollPane( bookingsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Button returnButton  = new Button( "Return", new Dimension(350, 50));

        // Layouts
        GridBagLayout layout = new GridBagLayout();

        // Layout config
        GridBagSettings headingLabelConstraints = new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(10, 25, 0, 20));
        GridBagSettings bookingsPanelConstraints = new GridBagSettings(0, 1, 1, 1, GridBagSettings.BOTH, new Insets(10, 20, 10, 20));
        GridBagSettings returnButtonConstraints = new GridBagSettings(0, 2, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 20, 10, 20));

        // Other Configurations
        setLayout(layout);
        bookingsPanel.setLayout(layout);
        bookingsPanel.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        returnButton.addActionListener(e -> ((GUI.Window) SwingUtilities.getWindowAncestor(this)).openScreen("Main"));

        setName("Previous Bookings");
        add(headingLabel, headingLabelConstraints);
        add(moviesScrollPane, bookingsPanelConstraints);
        add(returnButton, returnButtonConstraints);
    }

    public void PopulateBookings(Account account)
    {
        if (account.previousBookings.length <= 0)
        {
            bookingsPanel.add(new Label( "Feels a bit empty around here! Wanna book a movie?", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT));
            return;
        }

        bookingsPanel.removeAll();

        int x = 0, y = 0;
        for (Booking booking : account.previousBookings)
        {
            JPanel holder = new JPanel(new GridBagLayout());
            holder.setBackground(Visuals.Colors.INTERACTABLE_NORMAL);
            holder.setBorder(Visuals.Borders.INTERACTABLE_BORDER);
            Label movieLabel = new Label(booking.movie, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
            movieLabel.setIcon(new ImageIcon(booking.thumbnailPath));
            movieLabel.setVerticalTextPosition(Button.BOTTOM);
            movieLabel.setHorizontalTextPosition(Button.CENTER);
            movieLabel.setHorizontalAlignment(Button.CENTER);
            movieLabel.setVerticalAlignment(Button.CENTER);
            holder.add(movieLabel, new GridBagSettings(0, 0, 1, 1, GridBagSettings.BOTH, new Insets(4, 4, 4, 4)));
            Label dataLabel = new Label( booking.venue + " | " + booking.date + " | " + booking.time, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
            dataLabel.setHorizontalAlignment(Label.CENTER);
            holder.add(dataLabel, new GridBagSettings(0, 1, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 4, 4, 4)));
            Label seatsLabel = new Label( "Seats: " + booking.seatsStr, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALLER_FONT);
            seatsLabel.setHorizontalAlignment(Label.CENTER);
            holder.add(seatsLabel, new GridBagSettings(0, 2, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 4, 6, 4)));
            Button cancelButton = new Button("Cancel", new Dimension(200, 30));
            holder.add(cancelButton, new GridBagSettings(0, 3, 1, 0, GridBagSettings.HORIZONTAL, new Insets(5, 10, 7, 10)));
            bookingsPanel.add(holder, new GridBagSettings(x, y, new Insets(3, 3, 3, 3)));
            x++;
            if (x % 5 == 0 && x != 0)
            { y++; x = 0; }
        }
    }
}
