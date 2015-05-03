
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pmagnussen
 */
public class Movie {
    public int id;
    public String title;
    public int length;
    public String mpaaRating;
    public String releaseDate;
    public long budget;
    public long revenue;
    public int imdbID;
    public int tmdbID;
    public String awards;
    public HashSet<Agent> actors = new HashSet<>();
    public HashSet<Agent> directors = new HashSet<>();
    public HashSet<Agent> writers = new HashSet<>();
    public HashSet<Agent> studios = new HashSet<>();
    public HashSet<String> languages = new HashSet<>();
    public HashSet<String> genres = new HashSet<>();
    public HashSet<String> countries = new HashSet<>();
    
    public Movie() {
        
    }
    
    @Override
    public String toString() {
        String result = "";

        if (this.title != null) {
            result += "\ttitle: " + this.title;
        }
        
        if (this.length != 0) {
            result += "\tlength: " + this.length;
        }
        
        if (this.revenue != 0) {
            result += "\trevenue: " + this.revenue;
        }

        if (this.actors != null) {
            result += "\tactors: " + this.actors;
        }
        
        return result;
    }
}
