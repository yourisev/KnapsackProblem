package com.company;

import java.util.Comparator;
import java.util.Random;

public class Bound {
    private int min;
    private int max;

    public Bound(int min, int max) {
        this.min = min;
        this.max = max;
    }
    //creates an array of size "size" of random integers with values in [min,max]
    int [] generateRandomGroup(int size){
        int [] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=random.nextInt(this.getMax() - this.getMin()) + this.getMin();
        }
        return arr;
    }



    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
