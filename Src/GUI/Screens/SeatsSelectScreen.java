package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.Window;
import Managers.AccountsManager;
import Managers.GuiAppManager;
import Utilities.CinemaHall;
import Utilities.Date;
import Utilities.Movie;
import Utilities.Visuals;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class SeatsSelectScreen extends Screen
{
    private final Label headingLabel;
    private final JPanel seatButtonPanel;
    private final Button returnButton;
    private final Button confirmButton;
    private final Window window;

    public SeatsSelectScreen(Window _window)
    {
        super();
        window = _window;

        // Components
        headingLabel = new Label( "Book your seats: ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.HEADING_FONT);
        Label screenLabel = new Label( "[------------------------------------ All eyes here ------------------------------------]", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        seatButtonPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        returnButton  = new Button( "Return", new Dimension(350, 50));
        confirmButton  = new Button( "Minimum 1 Seat Should Be Selected", new Dimension(350, 50));

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();
        GridBagLayout seatButtonsPanelLayout = new GridBagLayout();
        GridBagLayout buttonPanelLayout = new GridBagLayout();

        // Layout Config
        GridBagSettings headingLabelConstraints = new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(10, 25, 0, 20));
        GridBagSettings screenLabelConstraints = new GridBagSettings(0, 1, 1, 0, GridBagSettings.HORIZONTAL, new Insets(10, 25, 0, 20));
        GridBagSettings seatButtonsPanelConstraints = new GridBagSettings(0, 2, 1, 1, GridBagSettings.BOTH, new Insets(10, 20, 10, 20));
        GridBagSettings buttonsPanelConstraints = new GridBagSettings(0, 3, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 20, 10, 20));
        GridBagSettings returnButtonConstraints = new GridBagSettings(0, 0, 1, 1, GridBagSettings.BOTH, new Insets(0,10, 10, 5));
        GridBagSettings bookButtonConstraints = new GridBagSettings(1, 0, 1, 1, GridBagSettings.BOTH, new Insets(0,5, 10, 10));

        // Other Configurations
        setLayout(mainLayout);
        screenLabel.setHorizontalAlignment(Label.CENTER);
        seatButtonPanel.setLayout(seatButtonsPanelLayout);
        buttonsPanel.setLayout(buttonPanelLayout);
        buttonsPanel.setOpaque(false);
        seatButtonPanel.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        confirmButton.setDisabled();

        buttonsPanel.add(returnButton, returnButtonConstraints);
        buttonsPanel.add(confirmButton, bookButtonConstraints);

        setName("Seat Select");
        add(headingLabel, headingLabelConstraints);
        add(screenLabel, screenLabelConstraints);
        add(seatButtonPanel, seatButtonsPanelConstraints);
        add(buttonsPanel, buttonsPanelConstraints);
    }

    public void PopulateSeats(Movie movie, CinemaHall hall, Date date, int timeIndex)
    {
        headingLabel.setText("Choose your seats, " + AccountsManager.currentAccount.holderName.trim().split(" ")[0] + ":");

        ArrayList<Integer> seatsToBook = new ArrayList<>();
        AtomicInteger frontSeats = new AtomicInteger();
        AtomicInteger normalSeats = new AtomicInteger();
        AtomicInteger premiumSeats = new AtomicInteger();
        AtomicBoolean isConfirmListenerAdded = new AtomicBoolean(false);
        ActionListener confirmButtonListener = e -> {
            if (seatsToBook.size() > 0 && seatsToBook.size() <= 8)
            {
                GuiAppManager.BookTickets(seatsToBook, new int[] {frontSeats.get(), normalSeats.get(), premiumSeats.get()});
                seatsToBook.clear();
                confirmButton.setDisabled();
                confirmButton.setText("Minimum 1 Seat Should Be Selected");
            }
        };
        returnButton.addActionListener(e -> window.openScreen(movie.name));
        seatButtonPanel.removeAll();

        int[][] frontRow = hall.GetFrontRowArrangement(movie, date, timeIndex);
        int[][] normalRow = hall.GetNormalRowArrangement(movie, date, timeIndex);
        int[][] premiumRow = hall.GetPremiumRowArrangement(movie, date, timeIndex);

        JPanel frontRowPanel = new JPanel();
        JPanel normalRowPanel = new JPanel();
        JPanel premiumRowPanel = new JPanel();

        frontRowPanel.setLayout(new GridLayout(hall.frontRows, hall.columns));
        frontRowPanel.setOpaque(false);
        normalRowPanel.setLayout(new GridLayout(hall.normalRows, hall.columns));
        normalRowPanel.setOpaque(false);
        premiumRowPanel.setLayout(new GridLayout(hall.premiumRows, hall.columns));
        premiumRowPanel.setOpaque(false);

        Label frontRowLabel = new Label("Front Rows ($" + hall.frontRowCost + ")", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label normalRowLabel = new Label("Normal Rows ($" + hall.normalRowCost+ ")", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label premiumRowLabel = new Label("Premium Rows ($" + hall.premiumRowCost + ")", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);

        seatButtonPanel.add(frontRowLabel, new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 5, 0)));
        seatButtonPanel.add(frontRowPanel, new GridBagSettings(0, 1, 1, 1, GridBagSettings.BOTH, new Insets(0, 0, 6, 0)));
        seatButtonPanel.add(normalRowLabel, new GridBagSettings(0, 2, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 5, 0)));
        seatButtonPanel.add(normalRowPanel, new GridBagSettings(0, 3, 1, 2, GridBagSettings.BOTH, new Insets(0, 0, 7, 0)));
        seatButtonPanel.add(premiumRowLabel, new GridBagSettings(0, 4, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 0, 5, 0)));
        seatButtonPanel.add(premiumRowPanel, new GridBagSettings(0, 5, 1, 1, GridBagSettings.BOTH, new Insets(0, 0, 7, 0)));

        for (int i = 0; i < hall.frontRows; i++)
            for (int j = 0; j < hall.columns; j++)
            {
                JPanel holderPanel = new JPanel();
                holderPanel.setOpaque(false);
                Button button = new Button(String.valueOf(frontRow[i][j]), new Dimension(34, 34));
                button.setFont(Visuals.Fonts.SMALLER_FONT);
                if (frontRow[i][j] == -1)
                {
                    button.setText("x");
                    button.setFont(Visuals.Fonts.NORMAL_FONT);
                    button.setDisabled();
                }
                int finalI = i, finalJ = j;
                button.addActionListener(e -> {
                    if (button.isSelected)
                    {
                        seatsToBook.remove((Integer) frontRow[finalI][finalJ]);
                        button.setNormal();
                        frontSeats.getAndDecrement();
                    }
                    else
                    {
                        seatsToBook.add(frontRow[finalI][finalJ]);
                        button.setSelected();
                        frontSeats.getAndIncrement();
                    }
                    if (seatsToBook.size() == 0)
                    {
                        confirmButton.setDisabled();
                        if (isConfirmListenerAdded.get()) confirmButton.removeActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(false);
                        confirmButton.setText("Minimum 1 Seat Should Be Selected");
                    }
                    else if (seatsToBook.size() > 8)
                    {
                        confirmButton.setDisabled();
                        if (isConfirmListenerAdded.get()) confirmButton.removeActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(false);
                        confirmButton.setText("Maximum 8 Seats Can Be Selected");
                    }
                    else
                    {
                        confirmButton.setNormal();
                        if (!isConfirmListenerAdded.get()) confirmButton.addActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(true);
                        confirmButton.setText("Confirm Seats");
                    }
                });
                holderPanel.add(button);
                frontRowPanel.add(holderPanel);
            }

        for (int i = 0; i < hall.normalRows; i++)
            for (int j = 0; j < hall.columns; j++)
            {
                JPanel holderPanel = new JPanel();
                holderPanel.setOpaque(false);
                Button button = new Button(String.valueOf(normalRow[i][j]), new Dimension(34, 34));
                button.setFont(Visuals.Fonts.SMALLER_FONT);
                if (normalRow[i][j] == -1)
                {
                    button.setText("x");
                    button.setFont(Visuals.Fonts.NORMAL_FONT);
                    button.setDisabled();
                }
                int finalI = i, finalJ = j;
                button.addActionListener(e -> {
                    if (button.isSelected)
                    {
                        seatsToBook.remove((Integer) normalRow[finalI][finalJ]);
                        button.setNormal();
                        normalSeats.getAndDecrement();
                    }
                    else
                    {
                        seatsToBook.add(normalRow[finalI][finalJ]);
                        button.setSelected();
                        normalSeats.getAndIncrement();
                    }
                    if (seatsToBook.size() == 0)
                    {
                        confirmButton.setDisabled();
                        if (isConfirmListenerAdded.get()) confirmButton.removeActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(false);
                        confirmButton.setText("Minimum 1 Seat Should Be Selected");
                    }
                    else if (seatsToBook.size() > 8)
                    {
                        confirmButton.setDisabled();
                        if (isConfirmListenerAdded.get()) confirmButton.removeActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(false);
                        confirmButton.setText("Maximum 8 Seats Can Be Selected");
                    }
                    else
                    {
                        confirmButton.setNormal();
                        if (!isConfirmListenerAdded.get()) confirmButton.addActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(true);
                        confirmButton.setText("Confirm Seats");
                    }
                });
                holderPanel.add(button);
                normalRowPanel.add(holderPanel);
            }

        for (int i = 0; i < hall.premiumRows; i++)
            for (int j = 0; j < hall.columns; j++)
            {
                JPanel holderPanel = new JPanel();
                holderPanel.setOpaque(false);
                Button button = new Button(String.valueOf(premiumRow[i][j]), new Dimension(34, 34));
                button.setFont(Visuals.Fonts.SMALLER_FONT);
                if (premiumRow[i][j] == -1)
                {
                    button.setText("x");
                    button.setFont(Visuals.Fonts.NORMAL_FONT);
                    button.setDisabled();
                }
                int finalI = i, finalJ = j;
                button.addActionListener(e -> {
                    if (button.isSelected)
                    {
                        seatsToBook.remove((Integer) premiumRow[finalI][finalJ]);
                        button.setNormal();
                        premiumSeats.getAndDecrement();
                    }
                    else
                    {
                        seatsToBook.add(premiumRow[finalI][finalJ]);
                        button.setSelected();
                        premiumSeats.getAndIncrement();
                    }
                    if (seatsToBook.size() == 0)
                    {
                        confirmButton.setDisabled();
                        if (isConfirmListenerAdded.get()) confirmButton.removeActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(false);
                        confirmButton.setText("Minimum 1 Seat Should Be Selected");
                    }
                    else if (seatsToBook.size() > 8)
                    {
                        confirmButton.setDisabled();
                        if (isConfirmListenerAdded.get()) confirmButton.removeActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(false);
                        confirmButton.setText("Maximum 8 Seats Can Be Selected");
                    }
                    else
                    {
                        confirmButton.setNormal();
                        if (!isConfirmListenerAdded.get()) confirmButton.addActionListener(confirmButtonListener);
                        isConfirmListenerAdded.set(true);
                        confirmButton.setText("Confirm Seats");
                    }
                });
                holderPanel.add(button);
                premiumRowPanel.add(holderPanel);
            }
    }
}
