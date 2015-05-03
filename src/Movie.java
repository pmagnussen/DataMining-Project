
import java.util.ArrayList;
import java.util.Date;

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
    public String actor1, actor2, actor3, actor4;
    public ArrayList<String> directors;
    public String writer1, writer2;
    public String genre1, genre2;
    public String studio1, studio2;
    public String language1, language2;
    public String country1, country2;
    
    
    public String genre;
    
    public Movie() {
        
    }
    
    @Override
    public String toString() {
        String result = "";
        
        if (this.id != 0) {
            result += "id: " + this.id;
        }
        
        if (this.title != null) {
            result += "\ttitle: " + this.title;
        }
        
        if (this.length != 0) {
            result += "\tlength: " + this.length;
        }
        
        if (this.releaseDate != null) {
            result += "\trelease date: " + this.releaseDate;
        }
        
        if (this.mpaaRating != null) {
            result += "\tMPAA rating: " + this.mpaaRating;
        }
        
        if (this.budget != 0) {
            result += "\tbudget: " + this.budget;
        }
        
        if (this.revenue != 0) {
            result += "\trevenue: " + this.revenue;
        }
        
        if (this.imdbID != 0) {
            result += "\timdb ID: " + this.imdbID;
        }
        
        if (this.awards != null) {
            result += "\tawards: " + this.awards;
        }
        
        if (this.actor1 != null) {
            result += "\tactor 1: " + this.actor1;
        }
        
        if (this.actor2 != null) {
            result += "\tactor 2: " + this.actor2;
        }
        
        if (this.actor3 != null) {
            result += "\tactor 3: " + this.actor3;
        }
        
        if (this.actor4 != null) {
            result += "\tactor 4: " + this.actor4;
        }
        
        if (this.directors != null) {
            result += "\tdirectors: " + this.directors;
        }
        
        if (this.writer1 != null) {
            result += "\twriter 1: " + this.writer1;
        }
        
        if (this.writer2 != null) {
            result += "\twriter 2: " + this.writer2;
        }
        
        if (this.genre1 != null) {
            result += "\tgenre 1: " + this.genre1;
        }
        
        if (this.genre2 != null) {
            result += "\tgenre 2: " + this.genre2;
        }
        
        if (this.studio1 != null) {
            result += "\tstudio 1: " + this.studio1;
        }
        
        if (this.studio2 != null) {
            result += "\tstudio 2: " + this.studio2;
        }
        
        if (this.language1 != null) {
            result += "\tlanguage 1: " + this.language1;
        }
        
        if (this.language2 != null) {
            result += "\tlanguage 2: " + this.language2;
        }
        
        if (this.country1 != null) {
            result += "\tcountry 1: " + this.country1;
        }
        
        if (this.country2 != null) {
            result += "\tcountry 2: " + this.country2;
        }
        
        return result;
    }
}
