
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Cleaner {

    public static ArrayList<Movie> cleanMPAARating(ArrayList<Movie> dirtyList) {
        ArrayList<Movie> result = new ArrayList<>();

        // For every movie
        for (Movie m : dirtyList) {
            if (m.mpaaRating.equalsIgnoreCase("X")) {
                m.mpaaRating = "NC-17";
            }

            if (isValidMPAARating(m.mpaaRating)) {
                result.add(m);
            }
        }

        return result;
    }

    private static boolean isValidMPAARating(String input) {
        ArrayList<String> validMPAARatings = new ArrayList<>();
        validMPAARatings.add("G");
        validMPAARatings.add("PG");
        validMPAARatings.add("PG-13");
        validMPAARatings.add("R");
        validMPAARatings.add("NC-17");

        if (validMPAARatings.contains(input)) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Movie> findLengthOutliers(ArrayList<Movie> dirtyList) {
        DescriptiveStatistics statsList = new DescriptiveStatistics();
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : dirtyList) {
            statsList.addValue(movie.length);
        }

        Double stddev = statsList.getStandardDeviation();
        Double mean = statsList.getMean();

        for (Movie element : dirtyList) {
            double value = (double) element.length;

            if (!(value < (mean - (stddev * 7))) || (value > (mean + (stddev * 7)))) {
                result.add(element);
            }
        }
        return result;
    }

    public static ArrayList<Movie> cleanBudgetOutliers(ArrayList<Movie> dirtyList) {
        DescriptiveStatistics statsList = new DescriptiveStatistics();
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : dirtyList) {
            statsList.addValue(movie.length);
        }

        Double stddev = statsList.getStandardDeviation();
        Double mean = statsList.getMean();

        for (Movie element : dirtyList) {
            double value = (double) element.length;

            if (!(value < (mean - (stddev * 7))) || !(value > (mean + (stddev * 7)))) {
                result.add(element);
            }
        }
        return result;
    }

    public static ArrayList<Movie> findBudgetOutliers(ArrayList<Movie> dirtyList) {
        DescriptiveStatistics statsList = new DescriptiveStatistics();
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : dirtyList) {
            statsList.addValue(movie.length);
        }

        Double stddev = statsList.getStandardDeviation();
        Double mean = statsList.getMean();

        for (Movie element : dirtyList) {
            long value = (long) element.length;

            if ((value < (mean - (stddev * 5))) || (value > (mean + (stddev * 5)))) {
                result.add(element);
            }
        }
        return result;
    }

    public static ArrayList<Movie> findRevenueOutliers(ArrayList<Movie> dirtyList) {
        DescriptiveStatistics statsList = new DescriptiveStatistics();
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : dirtyList) {
            statsList.addValue(movie.revenue);
        }

        Double stddev = statsList.getStandardDeviation();
        Double mean = statsList.getMean();

        for (Movie element : dirtyList) {
            long value = (long) element.revenue;

            if ((value < (mean - (stddev * 6))) || (value > (mean + (stddev * 6)))) {
                result.add(element);
            }
        }
        return result;
    }

    public static BigInteger calculateRevenue(ArrayList<Movie> movies) {
        BigInteger sum = BigInteger.valueOf(0);

        for (Movie movie : movies) {
            sum = sum.add(BigInteger.valueOf(movie.revenue));
        }

        return sum;
    }

    public static BigInteger calculateBudget(ArrayList<Movie> movies) {
        BigInteger sum = BigInteger.valueOf(0);

        for (Movie movie : movies) {
            sum = sum.add(BigInteger.valueOf(movie.budget));
        }

        return sum;
    }

    public static ArrayList<Agent> rankActors(ArrayList<Movie> movies) {
        ArrayList<Agent> foundActors = new ArrayList<>();

        for (Movie movie : movies) {
            for (Agent actor : movie.actors) {
                boolean foundInList = false;
                for (Agent foundActor : foundActors) {
                    if (foundActor.name.equalsIgnoreCase(actor.name)) {
                        foundActor.movieInvolvements++;
                        foundInList = true;
                        break;
                    }
                }
                if (!foundInList) {
                    foundActors.add(new Agent(actor.name, 1));
                }
            }
        }

        for (Agent actor : foundActors) {
            if (actor.movieInvolvements >= 10) {
                actor.rank = 1;
            } else if (actor.movieInvolvements < 10 && actor.movieInvolvements >= 5) {
                actor.rank = 2;
            } else if (actor.movieInvolvements < 5 && actor.movieInvolvements >= 2) {
                actor.rank = 3;
            } else if (actor.movieInvolvements == 1) {
                actor.rank = 4;
            }
        }

        return foundActors;
    }

    public static ArrayList<Agent> rankDirectors(ArrayList<Movie> movies) {
        ArrayList<Agent> directors = new ArrayList<>();

        for (Movie movie : movies) {
            HashSet<Agent> directorsInMovie = movie.directors;

            for (Agent director : directorsInMovie) {

                boolean foundInList1 = false;
                for (Agent actor : directors) {
                    if (actor.name.equalsIgnoreCase(director.name)) {
                        actor.movieInvolvements++;
                        foundInList1 = true;
                        break;
                    }
                }
                if (!foundInList1) {
                    directors.add(new Agent(director.name, 1));
                }
            }

            for (Agent director : directors) {
                if (director.movieInvolvements >= 5) {
                    director.rank = 1;
                } else if (director.movieInvolvements < 5 && director.movieInvolvements >= 3) {
                    director.rank = 2;
                } else if (director.movieInvolvements < 3 && director.movieInvolvements >= 2) {
                    director.rank = 3;
                } else if (director.movieInvolvements == 1) {
                    director.rank = 4;
                }
            }

        }
        return directors;
    }
    
    public static ArrayList<Agent> rankWriters(ArrayList<Movie> movies) {
        ArrayList<Agent> foundWriters = new ArrayList<>();

        for (Movie movie : movies) {
            HashSet<Agent> writersInMovie = movie.writers;
            for (Agent writer : writersInMovie) {
                boolean foundInList = false;
                for (Agent foundWriter : foundWriters) {
                    if (foundWriter.name.equalsIgnoreCase(writer.name)) {
                        foundWriter.movieInvolvements++;
                        foundInList = true;
                        break;
                    }
                }
                if (!foundInList) {
                    foundWriters.add(new Agent(writer.name, 1));
                }
            }

            for (Agent foundWriter : foundWriters) {
                if (foundWriter.movieInvolvements >= 5) {
                    foundWriter.rank = 1;
                } else if (foundWriter.movieInvolvements < 5 && foundWriter.movieInvolvements >= 3) {
                    foundWriter.rank = 2;
                } else if (foundWriter.movieInvolvements < 3 && foundWriter.movieInvolvements >= 2) {
                    foundWriter.rank = 3;
                } else if (foundWriter.movieInvolvements == 1) {
                    foundWriter.rank = 4;
                }
            }

        }
        return foundWriters;
    }

    public static ArrayList<Agent> rankStudios(ArrayList<Movie> movies) {
        ArrayList<Agent> foundStudios = new ArrayList<>();

        for (Movie movie : movies) {
            HashSet<Agent> studiosWithMovies = movie.studios;
            for (Agent studio : studiosWithMovies) {
                boolean foundInList = false;
                for (Agent foundStudio : foundStudios) {
                    if (foundStudio.name.equalsIgnoreCase(studio.name)) {
                        foundStudio.movieInvolvements++;
                        foundInList = true;
                        break;
                    }
                }
                if (!foundInList) {
                    foundStudios.add(new Agent(studio.name, 1));
                }
            }

            for (Agent foundStudio : foundStudios) {
                if (foundStudio.movieInvolvements >= 20) {
                    foundStudio.rank = 1;
                } else if (foundStudio.movieInvolvements < 20 && foundStudio.movieInvolvements >= 15) {
                    foundStudio.rank = 2;
                } else if (foundStudio.movieInvolvements < 15 && foundStudio.movieInvolvements >= 10) {
                    foundStudio.rank = 3;
                } else {
                    foundStudio.rank = 4;
                }
            }

        }
        return foundStudios;
    }
    
    public static void cleanChars(String text) {
        Set<String> validCharacters = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            validCharacters.add(String.valueOf(i));
        }
    }
}
