
import java.math.BigInteger;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pmagnussen
 */
public class Agent implements Comparable<Agent> {

    public BigInteger moneyEarned;
    public String name;
    public Integer movieInvolvements;
    public Integer rank;

    public Agent(String name) {
        this.name = name;
    }

    public Agent(String name, BigInteger earned) {
        this.name = name;
        this.moneyEarned = earned;
    }

    public Agent(String name, int appearances) {
        this.name = name;
        this.movieInvolvements = appearances;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Agent o) {
        //Actor actor2 = (Actor) o;
        return this.name.compareTo(o.name);
    }

    static final Comparator<Agent> RANK = new Comparator<Agent>() {
        public int compare(Agent e1, Agent e2) {
            return e2.rank.compareTo(e1.rank);
        }
    };

    static final Comparator<Agent> MONEY_EARNED = new Comparator<Agent>() {
        public int compare(Agent e1, Agent e2) {
            return e2.moneyEarned.compareTo(e1.moneyEarned);
        }
    };

    static final Comparator<Agent> MOVIE_INVOLEMENTS = new Comparator<Agent>() {
        public int compare(Agent e1, Agent e2) {
            return e2.movieInvolvements.compareTo(e1.movieInvolvements);
        }
    };
}
