package Managers;

import GUI.Dialogs.AuthenticateDialog;
import GUI.Dialogs.TotalCostDialog;
import GUI.Screens.*;
import GUI.Window;
import Utilities.CinemaHall;
import Utilities.Date;
import Utilities.Movie;

import java.util.ArrayList;
import java.util.Random;

public class GuiAppManager
{
    private static Window window;

    public static Movie movie;
    public static CinemaHall cinemaHall;
    public static Date date;
    public static int timeIndex;
    public static ArrayList<Integer> seatsToBook = new ArrayList<>();
    public static ArrayList<MovieDetailsScreen> movieDetailsScreens = new ArrayList<>();

    public static MainScreen mainScreen;
    public static PreviousBookingsScreen previousBookingsScreen;
    public static MovieSelectScreen movieSelectScreen;
    private static SeatsSelectScreen seatSelectScreen;
    private static BookingDetailsScreen bookingDetailsScreen;
    public static boolean isMainPanelAuth = false;

    public static void Initialize()
    {
        window = new Window();
        window.addScreen(previousBookingsScreen = new PreviousBookingsScreen());
        window.addScreen(mainScreen = new MainScreen());
        window.addScreen(movieSelectScreen = new MovieSelectScreen());
        for (Movie movie : BookingManager.Movies)
        {
            MovieDetailsScreen movieDetailsScreen = new MovieDetailsScreen(movie);
            movieDetailsScreens.add(movieDetailsScreen);
            window.addScreen(movieDetailsScreen);
        }
        window.addScreen(seatSelectScreen = new SeatsSelectScreen(window));
        window.addScreen(bookingDetailsScreen = new BookingDetailsScreen());
        window.addScreen(new EmptyScreen());
    }

    public static void SetData(Movie _movie, String _hall, String dt, String tm)
    {
        movie = _movie;
        for (CinemaHall hall : movie.cinemaHalls)
        {
            if (_hall.trim().equals(hall.name))
            {
                cinemaHall = hall;

                for (Date _date : hall.dates)
                    if (_date.date.equals(dt)) date = _date;

                int i = 0;
                for (String _time : date.times)
                {
                    if (tm.equals(_time)) break;
                    i++;
                }

                timeIndex = i;
            }
        }
        seatsToBook.clear();
    }

    public static void StartGUI()
    {
        window.setVisible(true);
        window.openScreen("Main");
    }

    public static void StartAuthentication()
    {
        if (AccountsManager.currentAccount != null)
        {
            StartBooking();
            return;
        }
        window.openScreen("Empty Screen");
        new AuthenticateDialog(window, movie);
    }
    public static void StartBooking()
    {
        GuiAppManager.mainScreen.SwitchButtons();
        if (isMainPanelAuth)
        {
            window.openScreen("Main");
            isMainPanelAuth = false;
            return;
        }
        window.openScreen("Seat Select");
        seatSelectScreen.PopulateSeats(movie, cinemaHall, date, timeIndex);
    }

    public static void BookTickets(ArrayList<Integer> seats, int[] ticketsPerSection)
    {
        seatsToBook.clear();
        seatsToBook.addAll(seats);
        int[] costData = BookingManager.CalculateCosts(movie, cinemaHall, date, timeIndex, seats, ticketsPerSection);
        window.openScreen("Empty Screen");
        new TotalCostDialog(window, ticketsPerSection, costData);
    }

    public static void EndBooking()
    {
        StringBuilder seats = new StringBuilder();
        for (int i = 0; i < seatsToBook.size(); i++)
        {
            String seat = String.valueOf(seatsToBook.get(i));
            if (seatsToBook.get(i) <= (cinemaHall.frontRows * cinemaHall.columns))
                seat = "F" + seat;
            else if (seatsToBook.get(i) <= ((cinemaHall.frontRowCost + cinemaHall.normalRows) * cinemaHall.columns))
                seat = "N" + seat;
            else seat = "P" + seat;
            if (i == 0) seats.append(seat);
            else seats.append(", ").append(seat);
        }

        BookingManager.RegisterSeats(movie, cinemaHall, date, timeIndex, seatsToBook);
        for (MovieDetailsScreen detailsScreen : movieDetailsScreens) detailsScreen.Refresh();
        movieSelectScreen.PopulateMovies();
        AccountsManager.AddBooking(movie.name, date.date, date.times[timeIndex], cinemaHall.name, seatsToBook);
        bookingDetailsScreen.Setup(
                movie.name + (movie.tagline.equals("") ? "" : ": " + movie.tagline),
                "At " + cinemaHall.name + " in " + cinemaHall.roomPrefix + " " + new Random().nextInt(1, 6),
                date.date,
                date.times[timeIndex],
                String.valueOf(seats),
                movie.ticketPath
        );
        window.openScreen("Booking Details");
    }
}
