
import java.math.BigInteger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pmagnussen
 */
public class Actor implements Comparable<Actor> {
    public BigInteger earned;
    public String name;
    public Float percentage;
    public Integer appearances;
    public Integer rank;
    
    public Actor(String name) {
        this.name = name;
    }
    
    public Actor(String name, BigInteger earned) {
        this.name = name;
        this.earned = earned;
    }
    
    public Actor(String name, int appearances) {
        this.name = name;
        this.appearances = appearances;
    }
    
    @Override
    public String toString() {
        return this.name + " earned " + this.earned;
    }

    @Override
    public int compareTo(Actor o) {
        //Actor actor2 = (Actor) o;
        return this.rank.compareTo(o.rank);
    }
}
