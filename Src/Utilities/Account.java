package Utilities;

import Managers.DataManager;

import java.io.File;
import java.nio.file.Files;

public class Account
{
    public String holderName;
    public String holderEmail;
    public String holderPassword;
    public Booking[] previousBookings;

    public Account(String[] data)
    {
        holderName = data[0];
        holderEmail = data[1];
        holderPassword = Security.decrypt(data[2]);
        GetPreviousBookings();
    }

    public void GetPreviousBookings()
    {
        try
        {
            File folder = new File(DataManager.ACCOUNTS_DATA_PATH + holderEmail + "/");
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles == null || listOfFiles.length <= 0)
            {
                previousBookings = new Booking[0];
                return;
            }

            previousBookings = new Booking[listOfFiles.length];
            for (int i = 0; i < listOfFiles.length; i++)
            {
                String[] seats = Files.readString(listOfFiles[i].toPath()).trim().split("\\r?\\n");
                previousBookings[i] = new Booking(listOfFiles[i].getName(), seats);
            }
        }
        catch(Exception e)
        {
            System.out.println(e + "\nError Reading Previous Bookings");
            System.exit(0);
        }
    }
}
