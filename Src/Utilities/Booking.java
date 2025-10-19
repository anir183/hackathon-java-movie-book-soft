package Utilities;

import java.util.ArrayList;

public class Booking
{
    private static final String THUMBNAIL_PATH = "Assets/Thumbnail/";

    public String movie;
    public String date;
    public String time;
    public String venue;
    public String seatsStr = "";
    public String thumbnailPath;
    public ArrayList<Integer> seats = new ArrayList<>();

    public Booking(String fileName, String[] seatData)
    {
        fileName = fileName.replace(".txt", "");
        String[] data = fileName.trim().split("#");
        movie = data[0].trim();
        venue = data[1].trim();
        date = data[2].trim();
        time = data[3].trim().replace("_", ":");
        for (int i = 0; i < seatData.length; i++)
        {
            if (i == 0) seatsStr += seatData[i];
            else seatsStr += ", " + seatData[i];

            int seat;
            try
            {
                seat = Integer.parseInt(seatData[i].trim());
            }
            catch(Exception e) { continue; }

            seats.add(seat);
        }
        thumbnailPath = THUMBNAIL_PATH + movie + ".jpg";
    }
}
