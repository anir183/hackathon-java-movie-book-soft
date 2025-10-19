package Utilities;

import Managers.BookingManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class RandomSeatsFiller
{
    private static final int MIN_SEATS_TO_BE_FILLED = 40;
    private static final int MAX_SEATS_THAT_CAN_BE_FILLED = 70;
    private static final int MAX_SEATS_THAT_CAN_BE_FILLED_APPEND = 5;
    private static final float MAX_FILLED_PERCENTAGE = 50;
    private static final int MAX_CONSECUTIVE_SEATS = 8;

    public static void Fill(Movie movie, CinemaHall hall, Date date, int time, Path path, int totalSeats, boolean isAppend)
    {
        Random randomGenerator = new Random();
        int seatsToBeFilled = isAppend ?  randomGenerator.nextInt(0, MAX_SEATS_THAT_CAN_BE_FILLED_APPEND + 1) : randomGenerator.nextInt(MIN_SEATS_TO_BE_FILLED, MAX_SEATS_THAT_CAN_BE_FILLED + 1);
        int seatsFilled = 0;

        try
        {
            while (seatsFilled <= seatsToBeFilled)
            {
                if (BookingManager.GetBookedSeats(movie, hall, date, time).size() >= (totalSeats * MAX_FILLED_PERCENTAGE / 100f)) return;

                int randSeat = randomGenerator.nextInt(1, totalSeats + 1);
                int consecutiveSeats = randomGenerator.nextInt(0, MAX_CONSECUTIVE_SEATS);

                for (int i = 0; i < consecutiveSeats; i++, seatsFilled++)
                {
                    if (!BookingManager.GetBookedSeats(movie, hall, date, time).contains(randSeat + i))
                        Files.writeString(path, randSeat + i + "\n", StandardOpenOption.APPEND);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError While Filling Random Seats");
            System.exit(0);
        }
    }
}
