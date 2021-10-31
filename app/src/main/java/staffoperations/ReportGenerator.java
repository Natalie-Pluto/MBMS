package staffoperations;

import MTBMS.Database;
import databaseutility.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportGenerator {
    private Database d;
    private Scanner s;

    public ReportGenerator(Scanner scanner, Database db){
        d = db;
        s = scanner;
    }

    public List<String> getUpcomingMovieNames(){
        String query = "select name_ from moviebooking_db.upcomingmovie";
        List<String> names = d.sql_getStrings(query,"name_");
        return names;
    }
    public void generateUpcomingMovieReport(){
        List<String> movieNames = getUpcomingMovieNames();
        boolean success = true;
        try{
            FileWriter myWriter = new FileWriter("UpcomingMoviesReport.txt");
            for(int i = 0; i < movieNames.size(); i++) {
                String movieName = movieNames.get(i);
                String classification = GetMovieClassification.getMovieClassification(d, movieName);
                String releaseDate = GetMovieReleaseDate.getMovieReleasedate(d, movieName);
                String synopsis = GetMovieSynopsis.getMovieSynopsis(d, movieName);
                String director = GetMovieDirectors.getDirectors(d, movieName);
                String genre = GetMovieGenre.getMovieGenre(d, movieName);
                String casts = GetMovieCast.getMovieCast(d, movieName);
                String showingDate = GetMovieShowDate.getMovieShowDate(d, movieName);
                myWriter.write("Movie Name: " + movieName + System.lineSeparator());
                myWriter.write("Classification: " + classification + System.lineSeparator());
                myWriter.write("Release Date: " + releaseDate + System.lineSeparator());
                myWriter.write("Synopsis: " + synopsis + System.lineSeparator());
                myWriter.write("Directors: " + director + System.lineSeparator());
                myWriter.write("Genre: " + genre + System.lineSeparator());
                myWriter.write("Casts: " + casts + System.lineSeparator());
                myWriter.write("Showing Date: " + showingDate + System.lineSeparator());
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
        }catch(IOException e) {
                System.out.println("Error: An error occurred.");
                success = false;
                e.printStackTrace();
            }
        if(success){
            System.out.println("Successfully generated the Upcoming Movie Report.");
        }
        }



    public void generateMovieSessionReport(){
        boolean success = true;
        ArrayList<String[]> cinemaSessions = ListAllCinemaSessions.listAllCinemaSessions(d);
        try {
            FileWriter myWriter = new FileWriter("CinemaSessionsInfo.txt");
            for (int i = 0; i < cinemaSessions.size(); i++) {
                String[] cinemaSession = cinemaSessions.get(i);
                String cinema = cinemaSession[0];
                String movieName = cinemaSession[1];
                String screenType = cinemaSession[2];
                String startTime = cinemaSession[3];
                int frontSeatBooking = Integer.parseInt(cinemaSession[4]);
                int midSeatBooking = Integer.parseInt(cinemaSession[5]);
                int backSeatBooking = Integer.parseInt(cinemaSession[6]);
                int frontSeatCapacity = Integer.parseInt(cinemaSession[7]);
                int midSeatCapacity = Integer.parseInt(cinemaSession[8]);
                int backSeatCapacity = Integer.parseInt(cinemaSession[9]);

                int frontSeatAvailable = frontSeatCapacity - frontSeatBooking;
                if (frontSeatAvailable < 0) {
                    frontSeatAvailable = 0;
                }
                int midSeatAvailable = midSeatCapacity - midSeatBooking;
                if (midSeatAvailable < 0) {
                    midSeatAvailable = 0;
                }
                int backSeatAvailable = backSeatCapacity - backSeatBooking;
                if (backSeatAvailable < 0) {
                    backSeatAvailable = 0;
                }
                myWriter.write("Cinema: " + cinema + System.lineSeparator());
                myWriter.write("Movie: " + movieName + System.lineSeparator());
                myWriter.write("Screen Type: " + screenType + System.lineSeparator());
                myWriter.write("Start Time: " + startTime + System.lineSeparator());
                myWriter.write("Front Seat Booking: " + String.valueOf(frontSeatBooking) + System.lineSeparator());
                myWriter.write("Front Seat Available: " + String.valueOf(frontSeatAvailable) + System.lineSeparator());
                myWriter.write("Middle Seat Booking: " + String.valueOf(midSeatBooking) + System.lineSeparator());
                myWriter.write("Middle Seat Available: " + String.valueOf(midSeatAvailable) + System.lineSeparator());
                myWriter.write("Back Seat Booking: " + String.valueOf(backSeatBooking) + System.lineSeparator());
                myWriter.write("Back Seat Available: " + String.valueOf(backSeatAvailable) + System.lineSeparator());
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
        }catch(IOException e){
            System.out.println("Error: An error occurred.");
            success = false;
            e.printStackTrace();
        }
        if(success){
            System.out.println("Successfully generated the Movie Session Report.");
        }
    }
}


