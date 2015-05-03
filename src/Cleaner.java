import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Cleaner {
    
    public enum Solution {AVERAGE, REMOVED, INFER, NONE}
    
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
            double value = (double)element.length;

            if ( !(value < (mean - (stddev * 7))) ||  (value > (mean + (stddev * 7)))) {
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
            double value = (double)element.length;

            if ( !(value < (mean - (stddev * 7))) ||  !(value > (mean + (stddev * 7)))) {
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
            long value = (long)element.length;

            if ((value < (mean - (stddev * 5))) ||  (value > (mean + (stddev * 5)))) {
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
            long value = (long)element.revenue;

            if ((value < (mean - (stddev * 6))) ||  (value > (mean + (stddev * 6)))) {
                result.add(element);
            }
        }
        return result;
    }
    
    public static ArrayList<Movie> findNameOutliers(ArrayList<Movie> dirtyList) {
        DescriptiveStatistics statsList = new DescriptiveStatistics();
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : dirtyList) {
            statsList.addValue(movie.actor1.length());
        }

        Double stddev = statsList.getStandardDeviation();
        Double mean = statsList.getMean();
        
        for (Movie element : dirtyList) {
            long value = (long)element.actor1.length();

            if ((value < (mean - (stddev * 5))) ||  (value > (mean + (stddev * 5)))) {
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
    
    public static ArrayList<Actor> rankActors(ArrayList<Movie> movies) {        
        ArrayList<Actor> actors = new ArrayList<>();
        
        for (Movie movie : movies) {
            Actor actor1 = new Actor(movie.actor1);
            Actor actor2 = new Actor(movie.actor2);
            Actor actor3 = new Actor(movie.actor3);
            Actor actor4 = new Actor(movie.actor4);
            
            boolean foundInList1 = false;
            for (Actor actor : actors) {
                if (actor.name.equalsIgnoreCase(actor1.name)) {
                    actor.appearances++;
                    foundInList1 = true;
                    break;
                }
            }
            
            if (!foundInList1) {
                actors.add(new Actor(actor1.name, 1));
            }
            
            boolean foundInList2 = false;
            for (Actor actor : actors) {
                if (actor.name.equalsIgnoreCase(actor2.name)) {
                    actor.appearances++;
                    foundInList2 = true;
                    break;
                }
            }
            if (!foundInList2) {
                actors.add(new Actor(actor2.name, 1));
            }
            
            boolean foundInList3 = false;
            for (Actor actor : actors) {
                if (actor.name.equalsIgnoreCase(actor3.name)) {
                    actor.appearances++;
                    foundInList3 = true;
                    break;
                }
            }
            if (!foundInList3) {
                actors.add(new Actor(actor3.name, 1));
            }
            
            boolean foundInList4 = false;
            for (Actor actor : actors) {
                if (actor.name.equalsIgnoreCase(actor4.name)) {
                    actor.appearances++;
                    foundInList4 = true;
                    break;
                }
            }
            if (!foundInList4) {
                actors.add(new Actor(actor4.name, 1));
            }
        }
        
        for (Actor actor : actors) {
            if (actor.appearances >= 10) {
                actor.rank = 1;
            } else if (actor.appearances < 10 && actor.appearances >= 5) {
                actor.rank = 2;
            } else if (actor.appearances < 5 && actor.appearances >= 2) {
                actor.rank = 3;
            } else if (actor.appearances == 1) {
                actor.rank = 4;
            }
        }
        
        return actors;
    }
    
    public static void cleanChars(String text) {        
        Set<String> validCharacters = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            validCharacters.add(String.valueOf(i));
        }
    }
}
