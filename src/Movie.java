
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
    public ArrayList<Agent> actors = new ArrayList<>();
    public ArrayList<Agent> directors = new ArrayList<>();
    public ArrayList<Agent> writers = new ArrayList<>();
    public ArrayList<Agent> studios = new ArrayList<>();
    public ArrayList<String> languages = new ArrayList<>();
    public ArrayList<String> genres = new ArrayList<>();
    public ArrayList<String> countries = new ArrayList<>();
    public boolean add = true;
    
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
