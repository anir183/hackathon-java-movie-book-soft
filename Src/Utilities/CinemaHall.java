package Utilities;

import Managers.BookingManager;

import java.util.ArrayList;

public class CinemaHall
{
    public String name;
    public String roomPrefix;
    public Date[] dates;
    public int frontRowCost;
    public int normalRowCost;
    public int premiumRowCost;
    public int frontRows;
    public int normalRows;
    public int premiumRows;
    public int columns;

    public int TotalSeats() { return (frontRows + normalRows + premiumRows) * columns; }

    public CinemaHall(String _name, String _prefix, String _dates, String _times, String[] _rows, int _cols, String[] costs)
    {
        name = _name;
        roomPrefix = _prefix;
        String[] dts = _dates.trim().split(", ");
        String[] timesPerDate = _times.split(" # ");
        dates = new Date[dts.length];
        for (int i = 0; i < dates.length; i++)
            dates[i] = new Date(dts[i], timesPerDate[i]);
        frontRows = Integer.parseInt(_rows[0].trim());
        normalRows = Integer.parseInt(_rows[1].trim());
        premiumRows = Integer.parseInt(_rows[2].trim());
        columns = _cols;
        frontRowCost = Integer.parseInt(costs[0].trim());
        normalRowCost = Integer.parseInt(costs[1].trim());
        premiumRowCost = Integer.parseInt(costs[2].trim());
    }

    public int[][] GetFrontRowArrangement(Movie movie, Date date, int timeIndex)
    {
        ArrayList<Integer> bookedSeats = BookingManager.GetBookedSeats(movie, this, date, timeIndex);
        int[][] seats = new int[frontRows][columns];

        int k = 1;
        for (int i = 0; i < frontRows; i++)
        {
            for (int j = 0; j < columns; j++, k++)
            {
                seats[i][j] = bookedSeats.contains(k) ? -1 : k;
            }
        }
        return seats;
    }
    public int[][] GetNormalRowArrangement(Movie movie, Date date, int timeIndex)
    {
        ArrayList<Integer> bookedSeats = BookingManager.GetBookedSeats(movie, this, date, timeIndex);
        int[][] seats = new int[normalRows][columns];

        int k = frontRows * columns + 1;
        for (int i = 0; i < normalRows; i++)
        {
            for (int j = 0; j < columns; j++, k++)
            {
                seats[i][j] = bookedSeats.contains(k) ? -1 : k;
            }
        }
        return seats;
    }
    public int[][] GetPremiumRowArrangement(Movie movie, Date date, int timeIndex)
    {
        ArrayList<Integer> bookedSeats = BookingManager.GetBookedSeats(movie, this, date, timeIndex);
        int[][] seats = new int[premiumRows][columns];

        int k = (frontRows + normalRows) * columns + 1;
        for (int i = 0; i < premiumRows; i++)
        {
            for (int j = 0; j < columns; j++, k++)
            {
                seats[i][j] = bookedSeats.contains(k) ? -1 : k;
            }
        }
        return seats;
    }
}
