
import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    static ArrayList<Movie> movies;

    // define format of CSV file one time and use everywhere
    // human readable configuration 
    private static final CSV csv = CSV
            .separator(',')
            .quote('\"')
            .skipLines(1)
            .charset("UTF-8")
            .create();

    private static ArrayList<Movie> loadCSV(String filename) {
        // CSVReader will be closed after end of processing
        // Less code to process CSV content -> less bugs
        ArrayList<Movie> result = new ArrayList<>();

        csv.read(filename, new CSVReadProc() {
            @Override
            public void procRow(int rowIndex, String... values) {
                boolean add = true;

                Movie movie = new Movie();

                if (values.length == 42) {
                    // Get the movie id
                    // If the values is an empty String, set the values to -1
                    if (values[0].equalsIgnoreCase("")) {
                        add = false;
                        // Otherwise, parse the int.
                    } else {
                        movie.id = Integer.parseInt(values[0]);
                    }
                    
//                    // There's one movie that has to be removed
//                    if (values[0].equalsIgnoreCase("81158")) {
//                        add = false;
//                    }

                    // Get the movie title
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[1].equalsIgnoreCase("") || values[1].equalsIgnoreCase("NA") || values[1].equalsIgnoreCase("N/A")) {
                        movie.title = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.title = values[1];
                    }

                    // Get the movie length
                    // If the values is an empty String, set the values to -1
                    if (values[3].equalsIgnoreCase("")) {
                        add = false;
                    } else {
                        movie.length = Integer.parseInt(values[3]);
                    }

                    // Get the MPAA rating
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[5].equalsIgnoreCase("") || values[5].equalsIgnoreCase("NA") || values[5].equalsIgnoreCase("N/A") || values[5].equalsIgnoreCase("NOT RATED") || values[5].equalsIgnoreCase("APPROVED")) {
                        add = false;
                        // Otherwise, parse the int.
                    } else {
                        movie.mpaaRating = values[5];
                    }

                    // Get the release date
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[6].equalsIgnoreCase("") || values[6].equalsIgnoreCase("NA") || values[6].equalsIgnoreCase("N/A")) {
                        //movie.releaseDate = "NO VALUE";
                        add = false;
                    } else {
                        movie.releaseDate = values[6];
                    }

                    // Get the budget
                    // If the values is an empty String or 0, then we aren't interested in the values
                    if (values[11].equalsIgnoreCase("") || values[11].equalsIgnoreCase("0")) {
                        add = false;
                    } else {
                        movie.budget = Long.parseLong(values[11]);
                    }

                    // Get the revenue
                    // If the values is an empty String or 0, set the values to -1
                    if (values[15].equalsIgnoreCase("") || values[15].equalsIgnoreCase("0")) {
                        add = false;
                    } else {
                        movie.revenue = Long.parseLong(values[15]);
                    }

                    // Get the idmb id
                    // If the values is an empty String or 0, set the values to -1
                    if (values[19].equalsIgnoreCase("") || values[19].equalsIgnoreCase("0")) {
                        movie.imdbID = -1;
                        // Otherwise, parse the int.
                    } else {
                        movie.imdbID = Integer.parseInt(values[19]);
                    }

                    // Get the tmdb id
                    // If the values is an empty String or 0, set the values to -1
                    if (values[20].equalsIgnoreCase("") || values[20].equalsIgnoreCase("0")) {
                        movie.tmdbID = -1;
                        // Otherwise, parse the int.
                    } else {
                        movie.tmdbID = Integer.parseInt(values[20]);
                    }

                    // Get the awards values
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[23].equalsIgnoreCase("") || values[23].equalsIgnoreCase("NA") || values[23].equalsIgnoreCase("N/A")) {
                        movie.awards = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.awards = values[23];
                    }

                    // Get actor 1
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[26].equalsIgnoreCase("") || values[26].equalsIgnoreCase("NA") || values[26].equalsIgnoreCase("N/A")) {
                        movie.actor1 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.actor1 = values[26];
                    }

                    // Get actor 2
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[27].equalsIgnoreCase("") || values[27].equalsIgnoreCase("NA") || values[27].equalsIgnoreCase("N/A")) {
                        movie.actor2 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.actor2 = values[27];
                    }

                    // Get actor 3
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[28].equalsIgnoreCase("") || values[28].equalsIgnoreCase("NA") || values[28].equalsIgnoreCase("N/A")) {
                        movie.actor3 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.actor3 = values[28];
                    }

                    // Get actor 4
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[29].equalsIgnoreCase("") || values[29].equalsIgnoreCase("NA") || values[29].equalsIgnoreCase("N/A")) {
                        movie.actor4 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.actor4 = values[29];
                    }

                    // Get directors
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (!values[30].equalsIgnoreCase("") || !values[30].equalsIgnoreCase("NA") || !values[30].equalsIgnoreCase("N/A")) {

                        String directors = values[30];
                        String[] directorsArray = directors.split(";");
                        ArrayList<String> directorsList = new ArrayList<String>(Arrays.asList(directorsArray));

                        movie.directors = directorsList;

                    } else {
                        add = false;
                    }

                    // Get writer 1
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[31].equalsIgnoreCase("") || values[31].equalsIgnoreCase("NA") || values[31].equalsIgnoreCase("N/A")) {
                        movie.writer1 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.writer1 = values[31];
                    }

                    // Get writer 2
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[32].equalsIgnoreCase("") || values[32].equalsIgnoreCase("NA") || values[32].equalsIgnoreCase("N/A")) {
                        movie.writer2 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.writer2 = values[32];
                    }

                    // Column 33 is the income - skip it
                    // Get genre 1
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[34].equalsIgnoreCase("") || values[34].equalsIgnoreCase("NA") || values[34].equalsIgnoreCase("N/A")) {
                        movie.genre1 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.genre1 = values[34];
                    }

                    // Get genre 2
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[35].equalsIgnoreCase("") || values[35].equalsIgnoreCase("NA") || values[35].equalsIgnoreCase("N/A")) {
                        movie.genre2 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.genre2 = values[35];
                    }

                    // Get studio 1
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[36].equalsIgnoreCase("") || values[36].equalsIgnoreCase("NA") || values[36].equalsIgnoreCase("N/A")) {
                        movie.studio1 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.studio1 = values[36];
                    }

                    // Get studio 2
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[37].equalsIgnoreCase("") || values[37].equalsIgnoreCase("NA") || values[37].equalsIgnoreCase("N/A")) {
                        movie.studio2 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.studio2 = values[37];
                    }

                    //System.out.println(values.length);
                    // Get language 1
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[38].equalsIgnoreCase("") || values[38].equalsIgnoreCase("NA") || values[38].equalsIgnoreCase("N/A")) {
                        movie.language1 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.language1 = values[38];
                    }

                    // Get language 2
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[38].equalsIgnoreCase("") || values[38].equalsIgnoreCase("NA") || values[38].equalsIgnoreCase("N/A")) {
                        movie.language2 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.language2 = values[38];
                    }

                    // Get country 1
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[39].equalsIgnoreCase("") || values[39].equalsIgnoreCase("NA") || values[39].equalsIgnoreCase("N/A")) {
                        movie.country1 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.country1 = values[39];
                    }

                    // Get country 2
                    // If the data is an empty String, set the data to "NO VALUE"
                    if (values[40].equalsIgnoreCase("") || values[40].equalsIgnoreCase("NA") || values[40].equalsIgnoreCase("N/A")) {
                        movie.country2 = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.country2 = values[40];
                    }

                    if (add) {
                        result.add(movie);
                    }

                }
            }
        });

        return result;
    }

    public static void main(String[] args) {
        String filename = "movie_samples.csv";

        // Read CSV into ArrayList
        movies = loadCSV(filename);

        // Remove any movies where the lenghts are "too" short or "too" long
        movies = Cleaner.findLengthOutliers(movies);
        
        // Remove any movies with invalid MPAA ratings
        movies = Cleaner.cleanMPAARating(movies);
        
        // Remove any movies where the budget is too small or too big        
        movies = Cleaner.cleanBudgetOutliers(movies);
        
        BigInteger revenueMarketSum = Cleaner.calculateRevenue(movies);
        
        // Rank actors
        ArrayList<Actor> actors = Cleaner.rankActors(movies);
        
        actors.sort(null);
        for (Actor actor : actors) {            
            System.out.println(actor.name + " Rank: " + actor.rank);
        }

    }
}
