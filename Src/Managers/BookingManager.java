package Managers;

import Utilities.CinemaHall;
import Utilities.Date;
import Utilities.Movie;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class BookingManager
{
    public static Movie[] Movies;
    public static final float CONVENIENCE_FEE_PERCENTAGE = 2f;
    public static final float TAX = 2.5f;
    public static final float SERVICE_TAX = 2.5f;

    public static void Initialize()
    {
        String[] movieData = DataManager.ProcessMovieData();
        Movies = new Movie[movieData.length];

        int i = 0;
        for (String data : movieData)
            Movies[i++] = new Movie(data.trim().split("\\r?\\n"));
    }

    public static int[] CalculateCosts(Movie movie, CinemaHall hall, Date date, int timeIndex, ArrayList<Integer> seats, int[] ticketsPerSection)
    {
        int frontRowCost = hall.frontRowCost * ticketsPerSection[0];
        int normalRowCost = hall.normalRowCost * ticketsPerSection[1];
        int premiumRowCost = hall.premiumRowCost * ticketsPerSection[2];
        int baseCost = frontRowCost + normalRowCost + premiumRowCost;
        int tax = (int) (baseCost * TAX / 100f);
        int stax = (int) (baseCost * SERVICE_TAX / 100f);
        int convenienceFee = (int) (baseCost * CONVENIENCE_FEE_PERCENTAGE / 100f);
        int totalFee = baseCost + tax + stax + convenienceFee;

        return new int[]{ frontRowCost, normalRowCost, premiumRowCost, baseCost, tax, stax, convenienceFee, totalFee };
    }

    public static void RegisterSeats(Movie movie, CinemaHall hall, Date date, int timeIndex, ArrayList<Integer> seats)
    {
        Path path = Path.of(DataManager.SEATS_ARRANGEMENT_PATH + movie.name + "/" + hall.name + "/" + date.date + "/" + date.times[timeIndex].replace(":", "_") + ".txt");
        for (Integer seat : seats)
        {
            try
            {
                Files.writeString(path, seat.toString() + "\n", StandardOpenOption.APPEND);
            }
            catch (Exception e)
            {
                System.out.println(e + "\nError Writing Booked Seats In " + path);
                System.exit(0);
            }
        }
    }

    public static boolean AreSeatsAvailableFor(Movie movie)
    {
        int k = 0;
        for (int i = 0; i < movie.cinemaHalls.length; i++)
            if (!AreSeatsAvailableIn(movie, movie.cinemaHalls[i])) k++;

        return k < movie.cinemaHalls.length;
    }

    public static boolean AreSeatsAvailableIn(Movie movie, CinemaHall hall)
    {
        int k = 0;
        for (int i = 0; i < hall.dates.length; i++)
            if (!AreSeatsAvailableOn(movie, hall, hall.dates[i])) k++;

        return k < hall.dates.length;
    }

    public static boolean AreSeatsAvailableOn(Movie movie, CinemaHall hall, Date date)
    {
        int k = 0;
        for (int i = 0; i < date.times.length; i++)
            if (!AreSeatsAvailableAt(movie, hall, date, i)) k++;

        return k < date.times.length;
    }

    public static boolean AreSeatsAvailableAt(Movie movie, CinemaHall hall, Date date, int timeIndex)
    {
        return BookingManager.GetBookedSeats(movie, hall, date, timeIndex).size() < hall.TotalSeats();
    }

    public static ArrayList<Integer> GetBookedSeats(Movie movie, CinemaHall hall, Date date, int timeIndex)
    {
        ArrayList<Integer> bookedSeats = new ArrayList<>();
        try
        {
            Path file = Path.of(DataManager.SEATS_ARRANGEMENT_PATH + movie.name + "/" + hall.name + "/" + date.date + "/" + date.times[timeIndex].replace(":", "_") + ".txt");
            String[] bookedSeatsRead = Files.readString(file).trim().split("\\r?\\n");

            if (bookedSeatsRead.length == 0) return bookedSeats;

            for (String bookedSeat : bookedSeatsRead)
                if (!bookedSeat.isEmpty() && !bookedSeat.isBlank()) bookedSeats.add(Integer.parseInt(bookedSeat));
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Getting Booked Seats");
            System.exit(0);
        }

        return bookedSeats;
    }
}
