package Utilities;

public class Movie
{
    private static final String THUMBNAIL_PATH = "Assets/Thumbnail/";
    private static final String POSTER_PATH = "Assets/Poster/";
    private static final String TICKET_PATH = "Assets/Ticket/";

    public String name;
    public String tagline;
    public String description;
    public String directors;
    public String writers;
    public String stars;
    public String genre;
    public String rating;
    public String releaseYear;
    public String duration;
    public CinemaHall[] cinemaHalls;
    public String thumbnailPath;
    public String posterPath;
    public String ticketPath;

    public Movie(String[] data)
    {
        String[] fullName = data[0].trim().split(": ");
        name = fullName[0];
        tagline = fullName.length > 1 ? ": " + fullName[1] : "";

        description = data[1].trim();
        directors = data[2].trim();
        writers = data[3].trim();
        stars = data[4].trim();
        genre = data[5].trim();
        rating = data[6].trim();
        releaseYear = data[7].trim();
        duration = data[8].trim();

        String[] halls = data[9].trim().split(", ");
        String[] hallPrefix = data[10].trim().split(", ");
        String[] datesPerHall = data[11].trim().split(" # ");
        String[] timesPerHall = data[12].trim().split(" ## ");
        String[] costsPerHall = data[13].trim().split(" # ");
        cinemaHalls = new CinemaHall[halls.length];
        for (int i = 0; i < halls.length; i++)
        {
            String[] hallData = halls[i].trim().split(" # ");
            cinemaHalls[i] = new CinemaHall(
                    hallData[0].trim(),
                    hallPrefix[i],
                    datesPerHall[i],
                    timesPerHall[i],
                    hallData[1].trim().split("\\."),
                    Integer.parseInt(hallData[2].trim()),
                    costsPerHall[i].trim().split(", ")
            );
        }

        thumbnailPath = THUMBNAIL_PATH + name + ".jpg";
        posterPath = POSTER_PATH + name + ".jpg";
        ticketPath = TICKET_PATH + name + ".png";
    }

    public void DisplayDetails()
    {
        System.out.println("🎥 " + name + tagline + " 🎥");
        System.out.println("📆 Release: " + releaseYear);
        System.out.println("📃 Description: " + description);
        System.out.println("🎬 Director(s): " + directors);
        System.out.println("✒️ Writer(s): " + writers);
        System.out.println("🎭 Stars: " + stars);
        System.out.println("📚 Genre: " + genre);
        System.out.println("⭐ Rating: " + rating);
        System.out.println("⏱️ Duration: " + duration);
    }
}
