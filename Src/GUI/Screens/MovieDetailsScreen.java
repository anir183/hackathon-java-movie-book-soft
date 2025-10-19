package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.Window;
import Managers.BookingManager;
import Managers.GuiAppManager;
import Utilities.CinemaHall;
import Utilities.Date;
import Utilities.Movie;
import Utilities.Visuals;
import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class MovieDetailsScreen extends Screen
{
    ComboBox<String> datesComboBox = new ComboBox<>();
    ComboBox<String> timesComboBox = new ComboBox<>();
    ComboBox<String> hallsComboBox = new ComboBox<>();
    Movie movie;
    public MovieDetailsScreen(Movie _movie)
    {
        super();
        movie = _movie;

        // Components
        Label headingLabel = new Label( "Movie Details: " + movie.name, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.HEADING_FONT);
        JPanel movieDetailsPanel = new JPanel();
        JPanel detailsPanel = new JPanel();
        JPanel datePanel = new JPanel();
        JPanel timePanel = new JPanel();
        JPanel hallsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JLabel posterLabel = new JLabel("");
        Button returnButton  = new Button( "Return", new Dimension(350, 50));
        Button bookButton  = new Button( "Book Tickets", new Dimension(350, 50));

        // Detail Labels
        Label nameLabel = new Label(movie.name + movie.tagline, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBTITLE_FONT);
        Label releaseAndDurationLabel = new Label(movie.releaseYear + " • " + movie.duration, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SMALL_FONT);
        Label aboutLabel = new Label("About the movie:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        Label descriptionLabel = new Label("Description: " + movie.description, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label directorsLabel = new Label("Directors: " + movie.directors, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label writersLabel = new Label("Writers: " + movie.writers, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label starsLabel = new Label("Stars: " + movie.stars, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label genreLabel = new Label("Genre: " + movie.genre, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label ratingLabel = new Label("Rating: " + movie.rating, Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label bookDetailsLabel = new Label("Booking details:", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.SUBHEADING_FONT);
        Label dateLabel = new Label("Available Dates:               ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label timeLabel = new Label("Available Times:               ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        Label availableHallsLabel = new Label("Available Cinema Halls:  ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.NORMAL_FONT);
        JPanel emptySpace = new JPanel();

        // Layouts
        GridBagLayout layout = new GridBagLayout();

        // Layout Config
        GridBagSettings headingLabelConstraints = new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(10, 25, 0, 20));
        GridBagSettings movieDetailsPanelConstraints = new GridBagSettings(0, 1, 1, 1, GridBagSettings.BOTH, new Insets(10, 20, 10, 20));
        GridBagSettings buttonsPanelConstraints = new GridBagSettings(0, 2, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 20, 10, 20));
        GridBagSettings detailsPanelConstraints = new GridBagSettings(1, 0, 1, 1, GridBagSettings.BOTH, new Insets(0,10, 10, 5));
        GridBagSettings posterLabelConstraints = new GridBagSettings(0, 0, 0, 1, GridBagSettings.VERTICAL, new Insets(0,10, 10, 5));
        GridBagSettings returnButtonConstraints = new GridBagSettings(0, 0, 1, 1, GridBagSettings.BOTH, new Insets(0,10, 10, 5));
        GridBagSettings bookButtonConstraints = new GridBagSettings(1, 0, 1, 1, GridBagSettings.BOTH, new Insets(0,5, 10, 10));

        // Labels Constraints
        GridBagSettings nameLabelConstraints = new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 2, 0));
        GridBagSettings releaseYearAndDurationLabelConstraints = new GridBagSettings( 0, 1, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 30, 0));
        GridBagSettings aboutLabelConstraints = new GridBagSettings(0, 2, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings ratingLabelConstraints = new GridBagSettings(0, 3, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings descriptionLabelConstraints = new GridBagSettings(0, 4, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings directorsLabelConstraints = new GridBagSettings(0, 5, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings writersLabelConstraints = new GridBagSettings(0, 6, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings starsLabelConstraints = new GridBagSettings(0, 7, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 4, 0));
        GridBagSettings genreLabelConstraints = new GridBagSettings(0, 8, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 30, 0));
        GridBagSettings bookingLabelConstraints = new GridBagSettings(0, 9, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 2, 0));
        GridBagSettings hallsPanelConstraints = new GridBagSettings(0, 10, 0, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 0, 0));
        GridBagSettings datesPanelConstraints = new GridBagSettings(0, 11, 0, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 0, 0));
        GridBagSettings timesPanelConstraints = new GridBagSettings(0, 12, 0, 0, GridBagSettings.HORIZONTAL, new Insets(0,0, 0, 0));
        GridBagSettings emptySpaceConstraints = new GridBagSettings(0, 13, 1, 1, GridBagSettings.BOTH, new Insets(0,0, 0, 0));

        // Other Configurations
        setLayout(layout);
        movieDetailsPanel.setLayout(layout);
        buttonsPanel.setLayout(layout);
        detailsPanel.setLayout(layout);
        datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        hallsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        hallsPanel.setOpaque(false);
        datePanel.setOpaque(false);
        timePanel.setOpaque(false);
        buttonsPanel.setOpaque(false);
        movieDetailsPanel.setOpaque(false);
        detailsPanel.setOpaque(false);
        emptySpace.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        posterLabel.setIcon(new ImageIcon(movie.posterPath));

        // Populating Combo Boxes
        PopulateHallsComboBox();
        PopulateDatesComboBox();
        PopulateTimesComboBox();

        // Action Listeners
        hallsComboBox.addActionListener(e -> {
            if (hallsComboBox.getSelectedItem() != null)
                PopulateDatesComboBox();
        });
        datesComboBox.addActionListener(e -> {
            if (datesComboBox.getSelectedItem() != null)
                PopulateTimesComboBox();
        });
        returnButton.addActionListener(e -> {
            ((Window) SwingUtilities.getWindowAncestor(this)).openScreen("Movie Select");
            GuiAppManager.movieSelectScreen.PopulateMovies();
        });
        bookButton.addActionListener(e -> {
            if (hallsComboBox.getItemCount() <= 0) return;
            GuiAppManager.SetData( movie, ((String) Objects.requireNonNull(hallsComboBox.getSelectedItem())).trim(), ((String) Objects.requireNonNull(datesComboBox.getSelectedItem())).trim(), ((String) Objects.requireNonNull(timesComboBox.getSelectedItem())).trim());
            GuiAppManager.StartAuthentication();
        });

        movieDetailsPanel.add(posterLabel, posterLabelConstraints);
        movieDetailsPanel.add(detailsPanel, detailsPanelConstraints);

        hallsPanel.add(availableHallsLabel);
        hallsPanel.add(hallsComboBox);

        datePanel.add(dateLabel);
        datePanel.add(datesComboBox);

        timePanel.add(timeLabel);
        timePanel.add(timesComboBox);

        detailsPanel.add(nameLabel, nameLabelConstraints);
        detailsPanel.add(releaseAndDurationLabel, releaseYearAndDurationLabelConstraints);
        detailsPanel.add(aboutLabel, aboutLabelConstraints);
        detailsPanel.add(descriptionLabel, descriptionLabelConstraints);
        detailsPanel.add(directorsLabel, directorsLabelConstraints);
        detailsPanel.add(writersLabel, writersLabelConstraints);
        detailsPanel.add(starsLabel, starsLabelConstraints);
        detailsPanel.add(genreLabel, genreLabelConstraints);
        detailsPanel.add(ratingLabel, ratingLabelConstraints);
        detailsPanel.add(releaseAndDurationLabel, releaseYearAndDurationLabelConstraints);
        detailsPanel.add(bookDetailsLabel, bookingLabelConstraints);
        detailsPanel.add(hallsPanel, hallsPanelConstraints);
        detailsPanel.add(datePanel, datesPanelConstraints);
        detailsPanel.add(timePanel, timesPanelConstraints);
        detailsPanel.add(emptySpace, emptySpaceConstraints);

        buttonsPanel.add(returnButton, returnButtonConstraints);
        buttonsPanel.add(bookButton, bookButtonConstraints);

        setName(movie.name);
        add(headingLabel, headingLabelConstraints);
        add(movieDetailsPanel, movieDetailsPanelConstraints);
        add(buttonsPanel, buttonsPanelConstraints);
    }

    public void Refresh()
    {
        PopulateHallsComboBox();
        PopulateDatesComboBox();
        PopulateTimesComboBox();
    }

    public void PopulateHallsComboBox()
    {
        hallsComboBox.removeAllItems();
        for (CinemaHall hall : movie.cinemaHalls)
            if (BookingManager.AreSeatsAvailableIn(movie, hall))
                hallsComboBox.addItem("   " + hall.name);
        if (hallsComboBox.getItemCount() > 0) hallsComboBox.setSelectedIndex(0);
    }

    public void PopulateDatesComboBox()
    {
        if (hallsComboBox.getItemCount() <= 0)
        {
            datesComboBox.removeAllItems();
            return;
        }
        CinemaHall cinemaHall = null;
        for (CinemaHall _hall : movie.cinemaHalls)
            if (((String) hallsComboBox.getSelectedItem()).trim().equals(_hall.name))
            {
                cinemaHall = _hall;
                break;
            }

        datesComboBox.removeAllItems();
        for (Date date : cinemaHall.dates)
            if (BookingManager.AreSeatsAvailableOn(movie, cinemaHall, date))
                datesComboBox.addItem("   " + date.date);
        if (datesComboBox.getItemCount() > 0) datesComboBox.setSelectedIndex(0);
    }

    public void PopulateTimesComboBox()
    {
        if (hallsComboBox.getItemCount() <= 0)
        {
            timesComboBox.removeAllItems();
            return;
        }
        CinemaHall cinemaHall = null;
        for (CinemaHall _hall : movie.cinemaHalls)
            if (((String) hallsComboBox.getSelectedItem()).trim().equals(_hall.name))
            {
                cinemaHall = _hall;
                break;
            }

        Date date = null;
        for (Date _date : cinemaHall.dates)
            if (((String) datesComboBox.getSelectedItem()).trim().equals(_date.date))
            {
                date = _date;
                break;
            }

        timesComboBox.removeAllItems();
        int i = 0;
        for (String time : date.times)
            if (BookingManager.AreSeatsAvailableAt(movie, cinemaHall, date, i++))
                timesComboBox.addItem("   " + time);
        if (timesComboBox.getItemCount() > 0) timesComboBox.setSelectedIndex(0);
    }
}
