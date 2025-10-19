package GUI.Screens;

import GUI.*;
import GUI.Button;
import GUI.Label;
import GUI.ScrollPane;
import GUI.Window;
import Managers.BookingManager;
import Managers.GuiAppManager;
import Utilities.Movie;
import Utilities.Visuals;
import java.awt.*;
import javax.swing.*;

public class MovieSelectScreen extends Screen
{
    JPanel moviesPanel;
    public MovieSelectScreen()
    {
        super();

        // Components
        Label headingLabel = new Label( "Select movie: ", Visuals.Colors.TEXT_NORMAL, Visuals.Fonts.HEADING_FONT);
        moviesPanel = new JPanel();
        ScrollPane moviesScrollPane = new ScrollPane( moviesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Button returnButton  = new Button( "Return", new Dimension(350, 50));

        // Layouts
        GridBagLayout mainLayout = new GridBagLayout();
        GridBagLayout moviesPanelLayout = new GridBagLayout();

        // Layout config
        GridBagSettings headingLabelConstraints = new GridBagSettings(0, 0, 1, 0, GridBagSettings.HORIZONTAL, new Insets(10, 25, 0, 20));
        GridBagSettings moviesPanelConstraints = new GridBagSettings(0, 1, 1, 1, GridBagSettings.BOTH, new Insets(10, 20, 10, 20));
        GridBagSettings returnButtonConstraints = new GridBagSettings(0, 2, 1, 0, GridBagSettings.HORIZONTAL, new Insets(0, 20, 10, 20));

        // Other Configurations
        setLayout(mainLayout);
        moviesPanel.setLayout(moviesPanelLayout);
        moviesPanel.setOpaque(false);
        headingLabel.setHorizontalAlignment(JLabel.LEFT);
        returnButton.addActionListener(e -> ((Window) SwingUtilities.getWindowAncestor(this)).openScreen("Main"));

        PopulateMovies();

        setName("Movie Select");
        add(headingLabel, headingLabelConstraints);
        add(moviesScrollPane, moviesPanelConstraints);
        add(returnButton, returnButtonConstraints);
    }

    public void PopulateMovies()
    {
        moviesPanel.removeAll();

        int x = 0, y = 0;
        for (Movie movie : BookingManager.Movies)
        {
            if (!BookingManager.AreSeatsAvailableFor(movie)) continue;
            Button movieButton = new Button(movie.name, new Dimension(255, 330));
            movieButton.setFont(Visuals.Fonts.NORMAL_FONT);
            movieButton.setIcon(new ImageIcon(movie.thumbnailPath));
            movieButton.setVerticalTextPosition(Button.BOTTOM);
            movieButton.setHorizontalTextPosition(Button.CENTER);
            movieButton.addActionListener(e -> {
                ((Window) SwingUtilities.getWindowAncestor(this)).openScreen(movie.name);
                for (MovieDetailsScreen detailsScreen : GuiAppManager.movieDetailsScreens) detailsScreen.Refresh();
            });
            moviesPanel.add(movieButton, new GridBagSettings(x, y, new Insets(2, 2, 2, 2)));
            x++;
            if (x % 5 == 0 && x != 0)
            { y++; x = 0; }
        }
    }
}
