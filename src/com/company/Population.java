package com.company;

import java.util.ArrayList;

public class Population {
    private int populationSize;
    private ArrayList<ItemChromosome> population;
    private Shop shop;

    public Population(int populationSize, Shop shop) {
        this.populationSize = populationSize;
        this.setShop(shop);
        this.population = new ArrayList<>() ;
        for (int i = 0; i < populationSize ; i++) {
            population.add(new ItemChromosome(shop));
        }
    }

    public Population(int populationSize, ArrayList<ItemChromosome> population, Shop shop) {
        this.populationSize = populationSize;
        this.population = population;
        this.setShop(shop);
    }

    //method to calculate the value carried by a chromosome and its total weight,
    // if it is above the threshold weight 0 is returned for this value
    int[] calculateChromValues(ItemChromosome indiv, int threshold ){
        int sum_weight=0, fitness=0;

        for (int i = 0; i <shop.getNumber_items() ; i++) {
            String val = ""+ indiv.getChromosome().charAt(i);
            int gene = Integer.parseInt(val);
            sum_weight += gene * this.getShop().getWeight()[i];
            fitness += gene * this.getShop().getValue()[i];
        }

        int[] arr = {sum_weight, fitness};
        arr[1] = (arr[0] > threshold)? 0: arr[1];
        return arr;
    }

    //Here Min for bound is our position in the population and Max our sum_weight
    //The tracker is used to keep track of all this information and to sort information based on the roulette sections value
    //The section is the last parameter whose area represents a type of probability
    ArrayList<Track> calculateForAllPopulation(int threshold ){
        ArrayList<Track> tmp = new ArrayList<>();
        for (int i = 0; i <this.getPopulationSize() ; i++) {
            int[] tmp1 = calculateChromValues(this.population.get(i),threshold);
            Track tracker = new Track(new Bound(i,tmp1[0]),tmp1[1]);
            tmp.add(tracker);
        }
        return tmp;
    }

    //sum of all the values in the shop
    int normalizationFactor(){
        int sum=0;
        for (int i = 0; i <this.getShop().getNumber_items() ; i++) {
            sum += this.getShop().getValue()[i];
        }
        return sum;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public ArrayList<ItemChromosome> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<ItemChromosome> population) {
        this.population = population;
    }
}
