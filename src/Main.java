
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
import java.util.HashSet;
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

                // If the row is a lenght of 42, then it is a valid row
                if (values.length == 42) {
                    // There's one movie that has to be removed
                    if (values[0].equalsIgnoreCase("81158") || values[0].equalsIgnoreCase("5077") || values[0].equalsIgnoreCase("105355")) {
                        movie.add = false;
                    }

                    // Get the movie id
                    // If the values is an empty String, set the values to -1
                    if (values[0].equalsIgnoreCase("") || values[0].equalsIgnoreCase("NA") || values[0].equalsIgnoreCase("N/A")) {
                        movie.id = -1;
                    } else {
                        movie.id = Integer.parseInt(values[0]);
                    }

                    // Get the movie title
                    // If the values is an empty String, set the values to "NO VALUE"
                    if (values[1].equalsIgnoreCase("") || values[1].equalsIgnoreCase("NA") || values[1].equalsIgnoreCase("N/A")) {
                        movie.title = "NO VALUE";
                    } else {
                        movie.title = values[1].trim();
                    }

                    // Get the movie length
                    // If the values is an empty String or invalid, don't add the movie
                    if (values[3].equalsIgnoreCase("") || values[3].equalsIgnoreCase("NA") || values[3].equalsIgnoreCase("N/A")) {
                        movie.add = false;
                    } else {
                        movie.length = Integer.parseInt(values[3]);
                    }

                    // Get the MPAA rating
                    // If the values is an empty String or invalid, don't add the movie
                    if (values[5].equalsIgnoreCase("") || values[5].equalsIgnoreCase("NA") || values[5].equalsIgnoreCase("N/A") || values[5].equalsIgnoreCase("NOT RATED") && values[5].equalsIgnoreCase("APPROVED")) {
                        movie.add = false;
                    } else {
                        movie.mpaaRating = values[5].trim();
                    }

                    // Get the release date
                    // If the values is an empty String or invalid, don't add the movie
                    if (values[6].equalsIgnoreCase("") || values[6].equalsIgnoreCase("NA") || values[6].equalsIgnoreCase("N/A")) {
                        movie.add = false;
                    } else {
                        movie.releaseDate = values[6].trim();
                    }

                    // Get the budget
                    // If the values is an empty String or invalid, don't add the movie
                    if (values[11].equalsIgnoreCase("") || values[11].equalsIgnoreCase("0") || values[11].equalsIgnoreCase("NA") || values[11].equalsIgnoreCase("N/A")) {
                        movie.add = false;
                    } else {
                        movie.budget = Long.parseLong(values[11]);
                    }

                    // Get the revenue
                    // If the values is an empty String or invalid, don't add the movie
                    if (values[15].equalsIgnoreCase("") || values[15].equalsIgnoreCase("0") || values[15].equalsIgnoreCase("NA") || values[15].equalsIgnoreCase("N/A")) {
                        movie.add = false;
                    } else {
                        movie.revenue = Long.parseLong(values[15]);
                    }

                    // Get the idmb id
                    // If the values is an empty String or invalid, set the values to -1
                    if (values[19].equalsIgnoreCase("") || values[19].equalsIgnoreCase("0") || values[19].equalsIgnoreCase("NA") || values[19].equalsIgnoreCase("N/A")) {
                        movie.imdbID = -1;
                        // Otherwise, parse the int.
                    } else {
                        movie.imdbID = Integer.parseInt(values[19]);
                    }

                    // Get the tmdb id
                    // If the values is an empty String or invalid, set the values to -1
                    if (values[20].equalsIgnoreCase("") || values[20].equalsIgnoreCase("0") || values[20].equalsIgnoreCase("NA") || values[20].equalsIgnoreCase("N/A")) {
                        movie.tmdbID = -1;
                    } else {
                        // Otherwise, parse the int.
                        movie.tmdbID = Integer.parseInt(values[20]);
                    }

                    // Get the awards values
                    // If the values is an empty String or invalid, set the values to "NO VALUE"
                    if (values[23].equalsIgnoreCase("") || values[23].equalsIgnoreCase("NA") || values[23].equalsIgnoreCase("N/A")) {
                        movie.awards = "NO VALUE";
                        // Otherwise, parse the int.
                    } else {
                        movie.awards = values[23].trim();
                    }

                    // Get actor 1
                    // If the value is not an empty String or invalid
                    if (!values[26].equalsIgnoreCase("") && !values[26].equalsIgnoreCase("NA") && !values[26].equalsIgnoreCase("N/A")) {
                        movie.actors.add(new Agent(values[26].trim()));
                    }

                    // Get actor 2
                    // If the value is not an empty String or invalid
                    if (!values[27].equalsIgnoreCase("") && !values[27].equalsIgnoreCase("NA") && !values[27].equalsIgnoreCase("N/A")) {
                        movie.actors.add(new Agent(values[27].trim()));
                    }

                    // Get actor 3
                    // If the value is not an empty String or invalid
                    if (!values[28].equalsIgnoreCase("") && !values[28].equalsIgnoreCase("NA") && !values[28].equalsIgnoreCase("N/A")) {
                        movie.actors.add(new Agent(values[28].trim()));
                    }

                    // Get actor 4
                    // If the value is not an empty String or invalid
                    if (!values[29].equalsIgnoreCase("") && !values[29].equalsIgnoreCase("NA") && !values[29].equalsIgnoreCase("N/A")) {
                        movie.actors.add(new Agent(values[29].trim()));
                    }

                    // If no actors were added, don't add the movie
                    if (movie.actors.size() < 4) {
                        movie.add = false;
                    }

                    // Get directors
                    // If the data is not an empty String or invalid data
                    if (!values[30].equalsIgnoreCase("") && !values[30].equalsIgnoreCase("NA") && !values[30].equalsIgnoreCase("N/A")) {

                        String directors = values[30];
                        String[] directorsArray = directors.split(",");
                        ArrayList<String> directorsList = new ArrayList<String>(Arrays.asList(directorsArray));

                        // Remove any whitespaces
                        ArrayList<Agent> directorsListNoWhiteSpaces = new ArrayList<>();
                        for (String director : directorsList) {
                            directorsListNoWhiteSpaces.add(new Agent(director.trim()));
                        }

                        if (directorsListNoWhiteSpaces.size() == 0) {
                            movie.add = false;
                        } else {
                            movie.directors = directorsListNoWhiteSpaces;
                        }

                    } else {
                        movie.add = false;
                    }

                    // If no actors were added, don't add the movie
                    if (movie.directors.size() < 1) {
                        movie.add = false;
                    }

                    // Get writer 1
                    // If the data is not an empty String or invalid data
                    if (!(values[31].equalsIgnoreCase("")) && !(values[31].equalsIgnoreCase("NA")) && !(values[31].equalsIgnoreCase("N/A"))) {
                        movie.writers.add(new Agent(values[31].trim()));
                    }

                    // Get writer 2
                    // If the data is not an empty String or invalid data
                    if (!(values[32].equalsIgnoreCase("")) && !(values[32].equalsIgnoreCase("NA")) && !(values[32].equalsIgnoreCase("N/A"))) {
                        movie.writers.add(new Agent(values[32].trim()));
                    }

                    // If no writers were added, don't add the movie
                    if (movie.writers.size() < 2) {
                        movie.add = false;
                    }

                    // Column 33 is the income - skip it
                    // Get genre 1
                    // If the data is not an empty String or invalid data
                    if (!values[34].equalsIgnoreCase("") && !values[34].equalsIgnoreCase("NA") && !values[34].equalsIgnoreCase("N/A")) {
                        movie.genres.add(values[34].trim());
                    }

                    // Get genre 2
                    // If the data is not an empty String or invalid data
                    if (!values[35].equalsIgnoreCase("") && !values[35].equalsIgnoreCase("NA") && !values[35].equalsIgnoreCase("N/A")) {
                        movie.genres.add(values[35].trim());
                    }

                    // If no genres were added, don't add the movie
                    if (movie.genres.size() == 0) {
                        movie.add = false;
                    }

                    // Get studio 1
                    // If the data is not an empty String or invalid data
                    if (!values[36].equalsIgnoreCase("") && !values[36].equalsIgnoreCase("NA") && !values[36].equalsIgnoreCase("N/A")) {
                        movie.studios.add(new Agent(values[36].trim()));
                    }

                    // Get studio 2
                    // If the data is not an empty String or invalid data
                    if (!values[37].equalsIgnoreCase("") && !values[37].equalsIgnoreCase("NA") && !values[37].equalsIgnoreCase("N/A")) {
                        movie.studios.add(new Agent(values[37].trim()));
                    }

                    // If no studios were added, don't add the movie
                    if (movie.studios.size() < 1) {
                        movie.add = false;
                    }

                    // Get language 1
                    // If the data is not an empty String or invalid data
                    if (!values[38].equalsIgnoreCase("") && !values[38].equalsIgnoreCase("NA") && !values[38].equalsIgnoreCase("N/A")) {
                        movie.languages.add(values[38].trim());
                    }

                    // Get language 2
                    // If the data is not an empty String or invalid data
                    if (!values[39].equalsIgnoreCase("") && !values[39].equalsIgnoreCase("NA") && !values[39].equalsIgnoreCase("N/A")) {
                        movie.languages.add(values[39].trim());
                    }

                    // If no languages were added, don't add the movie
                    if (movie.languages.size() == 0) {
                        movie.add = false;
                    }

                    // Get country 1
                    // If the data is not an empty String or invalid data
                    if (!values[40].equalsIgnoreCase("") && !values[40].equalsIgnoreCase("NA") && !values[40].equalsIgnoreCase("N/A")) {
                        movie.countries.add(values[40].trim());
                    }

                    // Get country 2
                    // If the data is not an empty String or invalid data
                    if (!values[41].equalsIgnoreCase("") && !values[41].equalsIgnoreCase("NA") && !values[41].equalsIgnoreCase("N/A")) {
                        movie.countries.add(values[41].trim());
                    }

                    if (movie.countries.size() == 0) {
                        movie.add = false;
                    }

                    //if (add) {
                    result.add(movie);
                    //}
                    
                }
            }
        });
        System.out.println("Result size: " + result.size());
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
        ArrayList<Agent> actors = Cleaner.rankActors(movies);
        movies = Cleaner.rankActors2(movies);
        //actors.sort(Agent.RANK);

        // Rank directors
        ArrayList<Agent> directors = Cleaner.rankDirectors(movies);
        movies = Cleaner.rankDirectors2(movies);
        //directors.sort(Agent.MOVIE_INVOLEMENTS);

        // Rank writers
        ArrayList<Agent> writers = Cleaner.rankWriters(movies);
        movies = Cleaner.rankWriters2(movies);
        //writers.sort(Agent.MOVIE_INVOLEMENTS);

        // Rank studios
        ArrayList<Agent> studios = Cleaner.rankStudios(movies);
        //movies = Cleaner.rankStudios2(movies);
        movies = Cleaner.rankStudios3(movies);
        //studios.sort(Agent.MOVIE_INVOLEMENTS);

        System.out.println("Movies count: " + movies.size());
        

        csv.write("movie_samples_cleaned.csv", new CSVWriteProc() {
            int size = 0;
            public void process(CSVWriter out) {
                //out.writeNext("id", "title", "lenght", "MPAA rating", "Release date", "Budget", "Revenue", "IMDB ID", "TMDB ID", "Awards", "Actors", "Directors", "Writers", "Studios", "Languages", "Genres", "Countries");
                out.writeNext("id", "title", "lenght", "MPAA rating", "Release date",
                        "Budget", "Revenue", "IMDB ID", "TMDB ID", "Actor1", "Actor2",
                        "Actor3", "Actor4", "Actor1rank", "Actor2rank", "Actor3rank",
                        "Actor4rank", "Director", "DirectorRank",
                        "Writer1", "Writer1rank", "Writer2", "Writer2Rank", "Studio", "StudioRank", "Language", "Genre", "Country");

                for (Movie mov : movies) {
                    if (mov.add) {
                        size++;
                        out.writeNext(
                            String.valueOf(mov.id),
                            mov.title,
                            String.valueOf(mov.length),
                            mov.mpaaRating,
                            mov.releaseDate,
                            String.valueOf(mov.budget),
                            String.valueOf(mov.revenue),
                            String.valueOf(mov.imdbID),
                            String.valueOf(mov.tmdbID),
                            // Remove the brackets that surround Collections
                            //                            removeBrackets(mov.actors.get(0)),
                            //                            removeBrackets(mov.directors.toString()),
                            //                            removeBrackets(mov.writers.toString()),
                            //                            removeBrackets(mov.studios.toString()),
                            //                            removeBrackets(mov.languages.toString()),
                            //                            removeBrackets(mov.genres.toString()),
                            //                            removeBrackets(mov.countries.toString()));

                            mov.actors.get(0).toString(),
                            mov.actors.get(1).toString(),
                            mov.actors.get(2).toString(),
                            mov.actors.get(3).toString(),
                            mov.actors.get(0).movieInvolvements.toString(),
                            mov.actors.get(1).movieInvolvements.toString(),
                            mov.actors.get(2).movieInvolvements.toString(),
                            mov.actors.get(3).movieInvolvements.toString(),
                            mov.directors.get(0).toString(),
                            mov.directors.get(0).movieInvolvements.toString(),
                            mov.writers.get(0).toString(),
                            mov.writers.get(0).movieInvolvements.toString(),
                            mov.writers.get(1).toString(),
                            mov.writers.get(1).movieInvolvements.toString(),
                            mov.studios.get(0).toString(),
                            mov.studios.get(0).movieInvolvements.toString(),
                            mov.languages.get(0),
                            mov.genres.get(0),
                            mov.countries.get(0));
                    }

//                    String studioRank;
//                    if (mov.studios.get(0).movieInvolvements == null) {
//                        System.out.println("Nullpointer: " + mov.title);
//                        studioRank = "";
//                    }  else {
//                        studioRank = mov.studios.get(0).movieInvolvements.toString();
//                    }
                    
                }
                System.out.println("Size: " + size);
            }
        });
    }

    private static String removeBrackets(String input) {
        return input.substring(1, input.length() - 1);
    }
}
