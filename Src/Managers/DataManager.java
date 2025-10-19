package Managers;

import Utilities.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataManager
{
    // FILE PATHS
    private static final String MOVIES_DATA_PATH = "Data/Movies.txt";
    public static final String SEATS_ARRANGEMENT_PATH = "Data/Seating/";
    public static final String ACCOUNTS_FILE_PATH = "Data/Accounts.txt";
    public static final String ACCOUNTS_DATA_PATH = "Data/AccountsData/";

    public static void InitializeMovieData()
    {
        try
        {
            File seatArrangementsDir = new File(SEATS_ARRANGEMENT_PATH);
            if (!seatArrangementsDir.exists()) seatArrangementsDir.mkdir();

            for (Movie movie : BookingManager.Movies)
            {
                String movieDirPath = SEATS_ARRANGEMENT_PATH + movie.name + "/";
                File movieDir = new File(movieDirPath);
                if (!movieDir.exists()) movieDir.mkdir();

                for (CinemaHall hall : movie.cinemaHalls)
                {
                    String hallDirPath = movieDirPath + hall.name + "/";
                    File hallDir = new File(hallDirPath);
                    if (!hallDir.exists()) hallDir.mkdir();

                    for (Date date : hall.dates)
                    {
                        String dateDirPath = hallDirPath + date.date + "/";
                        File dateDir = new File(dateDirPath);
                        if (!dateDir.exists()) dateDir.mkdir();

                        int i = 0;
                        for (String time : date.times)
                        {
                            File timeFile =
                                    new File(dateDirPath + time.replace(":", "_") + ".txt");
                            if (!timeFile.exists())
                            {
                                timeFile.createNewFile();
                                RandomSeatsFiller.Fill(movie, hall, date, i, timeFile.toPath(), hall.TotalSeats(), false);
                            }
                            else
                                RandomSeatsFiller.Fill(movie, hall, date, i, timeFile.toPath(), hall.TotalSeats(), true);

                            i++;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Initializing Data!");
            System.exit(0);
        }
    }

    public static void InitializeAccountsData()
    {
        try {
            File accountDataPath = new File(ACCOUNTS_DATA_PATH);
            if (!accountDataPath.exists()) accountDataPath.mkdir();

            for (Account account : AccountsManager.Accounts) {
                String accountDirPath = ACCOUNTS_DATA_PATH + account.holderEmail;
                File accountDir = new File(accountDirPath);
                if (!accountDir.exists()) accountDir.mkdir();
            }
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Initializing Accounts Data!");
            System.exit(0);
        }
    }

    public static String[] ProcessMovieData()
    {
        try
        {
            Path file = Path.of(MOVIES_DATA_PATH);
            String data = Files.readString(file);

            return data.trim().split("\\r?\\n\\r?\\n");
        }
        catch (Exception e)
        {
            System.out.println("Error Processing Movie Data!");
            System.exit(0);
            return null;
        }
    }

    public static String[] ProcessAccountsData()
    {
        try
        {
            Path file = Path.of(ACCOUNTS_FILE_PATH);
            String data = Files.readString(file);

            return data.trim().split("\\r?\\n\\r?\\n");
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Processing Accounts Data!");
            System.exit(0);
            return null;
        }
    }
}
