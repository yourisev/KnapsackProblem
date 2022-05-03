package com.company;

import java.util.Arrays;
import java.util.Random;

public class Shop {
    int number_items;
    int[] weight;
    int[] value;

    public Shop(int number_items, int weight_min, int weight_max, int value_min, int value_max) {
        this.number_items = number_items;
        this.weight = new Bound(weight_min,weight_max).generateRandomGroup(this.number_items);
        this.value = new Bound(value_min,value_max).generateRandomGroup(this.number_items);
    }

    public int getNumber_items() {
        return number_items;
    }

    public void setNumber_items(int number_items) {
        this.number_items = number_items;
    }

    public int[] getWeight() {
        return weight;
    }

    public void setWeight(int[] weight) {
        this.weight = weight;
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    //design the toString and may be a method to display
    @Override
    public String toString() {
        return "Shop{\n" +
                "Number of items : " + number_items +
                ", \nWeights ->" + Arrays.toString(weight) +
                ", \nValue ->" + Arrays.toString(value) +
                "\n}";
    }
}
