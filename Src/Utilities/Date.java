package Utilities;

public class Date
{
    public String date;
    String timesStr;
    public String[] times;
    public Date(String _date, String _times)
    {
        date = _date.trim();
        timesStr = _times.trim();
        times = timesStr.split(", ");
    }
}
