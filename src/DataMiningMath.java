/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author pmagnussen
 */
public class DataMiningMath {
    
    public static Double standardDeviation(Set<Integer> values) {
        Double mean = mean(values);
        Set<Integer> newValues = new HashSet<>();
        
        System.out.println("Incoming datapoints");
        for (Integer dp : values) {
            System.out.println(dp);
        }
        
        
        for (Integer dp : values) {
            // (datapoint - mean)~2 add to new values
            System.out.println("Val: " + dp);
            double a = dp.doubleValue();
            Double result = Math.pow(a-mean, 2);
            System.out.println("new res: " + result);
            newValues.add(result.intValue());
        }
        
        System.out.println("mean: " + mean);
        System.out.println("calc datapoints");
        for (Integer dp : newValues) {
            System.out.println(dp);
        }
        
        double meanOfNewValues = DataMiningMath.mean(newValues);
        System.out.println("meanOfNewValues: " + meanOfNewValues);
        double result = Math.sqrt(meanOfNewValues);
        
        return result;
    }

    public static Double mean(Set<Integer> values) {
        Double mean = 0.0;

        for (Integer value : values) {
            mean += value;
        }
        mean /= values.size();

        return mean;
    }

    // the array double[] m MUST BE SORTED
    public static double median(double[] m) {
        int middle = m.length / 2;
        if (m.length % 2 == 1) {
            return m[middle];
        } else {
            return (m[middle - 1] + m[middle]) / 2.0;
        }
    }

    public static int mode(final int[] values) {
        int maxKey = 0;
        int maxCounts = 0;

        int[] counts = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            counts[values[i]]++;
            if (maxCounts < counts[values[i]]) {
                maxCounts = counts[values[i]];
                maxKey = values[i];
            }
        }
        return maxKey;
    }
}
