package com.company;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GeneticAlgorithm {
    public static  int number_of_generation = 20;
    private double crossover_rate;
    private double mutation_rate;

    public GeneticAlgorithm(double crossover_rate, double mutation_rate) {
        this.crossover_rate = crossover_rate;
        this.mutation_rate = mutation_rate;
    }

    /*Selection
    *need of a decay factor for num_parents as the number_of_generation increase*/
     /*ArrayList<ItemChromosome> rouletteSelection(int num_parents, Population population, int threshold) {
        ArrayList<Track> list_fitness_weight = new ArrayList<>();
        int j = 0;
        while ( j < population.getPopulationSize() && list_fitness_weight.size()<=num_parents) {
            //if((Double.compare(Math.random(),population.calculateForAllPopulation(threshold).get(j).getPair().getMin())>0 ) && (Double.compare(0,population.calculateForAllPopulation(threshold).get(j).getPair().getMin())==0 )  )
            if(Double.compare(Math.random(),(((double)population.calculateForAllPopulation(threshold).get(j).getSection())/((double)population.normalizationFactor())))<=0 ){
                list_fitness_weight.add(population.calculateForAllPopulation(threshold).get(j));
            }
            j++;
        }

        int i=0;
        for (Track el: list_fitness_weight) {
            System.out.println(i+" prioritry: "+ el.getPair().getMin());
            i++;
        }

        return reconstitute(population,list_fitness_weight);
    }*/

    /*Collects the Selected Chromosomes using the Track list which contains the position
     *of the selected elements in the population*/
    /*private ArrayList<ItemChromosome> reconstitute(Population population, ArrayList<Track> tracker){
        ArrayList<ItemChromosome> solution = new ArrayList<>();

        for (Track track: tracker) {
            solution.add(population.getPopulation().get(track.getPair().getMin()));
        }
        return solution;
    }*/

    //Selection done by ranking and choosing the best
    ArrayList<ItemChromosome> rankSelection(int num_parents, Population population, int threshold) {
        ArrayList<Track> list_track = population.calculateForAllPopulation(threshold);
        ArrayList<ItemChromosome> chromosomesRetained = new ArrayList<>();
        Comparator<Track> bySection = Comparator.comparing(Track::getSection);
        list_track.sort(Collections.reverseOrder(bySection));

        System.out.println("************** RANK SELECTION ****************");

        int j = 0;
        for (Track el: list_track) {
            population.getPopulation().get(el.getPair().getMin()).setShop(population.getShop());
            chromosomesRetained.add(population.getPopulation().get(el.getPair().getMin()));
            System.out.println("Chromosome selected number "+(j+1)+" "
                    +population.calculateChromValues(population.getPopulation().get(el.getPair().getMin()),threshold)[0]
                    +"\t"+population.calculateChromValues(population.getPopulation().get(el.getPair().getMin()),threshold)[1]);
            j++;
            if(j>=num_parents)
                break;
        }

        return chromosomesRetained;
    }



    //Cross over of chromosomes
    private ArrayList<ItemChromosome> crossover(ArrayList<ItemChromosome> parents, int number_of_offsprings) {
        ArrayList<ItemChromosome> offsprings = new ArrayList<>();
        int[] crossoverRange;
        ItemChromosome[] generatedChild;
        int i = 0;
        while ( offsprings.size() < number_of_offsprings) {
            crossoverRange = new Bound(0,29).generateRandomGroup(2);
            if(crossoverRange[0]>crossoverRange[1]){
                int tmp = crossoverRange[0];
                crossoverRange[0] = crossoverRange[1];
                crossoverRange[1] = tmp;
            }
            if (Double.compare(Math.random(),this.crossover_rate) <= 0) {
                generatedChild = generate(parents.get(i%parents.size()),parents.get((i+1)%parents.size()),crossoverRange);
                offsprings.add(generatedChild[0]);
                offsprings.add(generatedChild[1]);
            }
            i++;
        }
        return offsprings;
    }

    //Generating Crossover off-springs from parents given as argument
    private ItemChromosome[] generate(ItemChromosome chromosome1, ItemChromosome chromosome2, int[] crossoverRange) {
        StringBuilder tmpChrom1 = new StringBuilder();
        StringBuilder tmpChrom2 = new StringBuilder();
        String tmpChrom1_sub1 = chromosome1.getChromosome().substring(0,(crossoverRange[0]==0)? 1:crossoverRange[0]);
        String tmpChrom1_sub2 = chromosome1.getChromosome().substring((crossoverRange[0]==0)? 1:crossoverRange[0],
                (crossoverRange[1]==crossoverRange[0])?crossoverRange[1] + 1:crossoverRange[1]);
        String tmpChrom1_sub3 = ((crossoverRange[1]+1)==chromosome1.getChromosome().length())?"":
                chromosome1.getChromosome().substring(((crossoverRange[1]==crossoverRange[0]))?crossoverRange[1] + 1:crossoverRange[1]);

        String tmpChrom2_sub1 = chromosome2.getChromosome().substring(0,(crossoverRange[0]==0)? 1:crossoverRange[0]);
        String tmpChrom2_sub2 = chromosome2.getChromosome().substring((crossoverRange[0]==0)? 1:crossoverRange[0],
                (crossoverRange[1]==crossoverRange[0])?crossoverRange[1] + 1:crossoverRange[1]);
        String tmpChrom2_sub3 = ((crossoverRange[1]+1)==chromosome2.getChromosome().length())?"":
                chromosome2.getChromosome().substring(((crossoverRange[1]==crossoverRange[0]))?crossoverRange[1] + 1:crossoverRange[1]);

        tmpChrom1.append(tmpChrom1_sub1);
        tmpChrom1.append(tmpChrom2_sub2);
        tmpChrom1.append(tmpChrom1_sub3);

        tmpChrom2.append(tmpChrom2_sub1);
        tmpChrom2.append(tmpChrom1_sub2);
        tmpChrom2.append(tmpChrom2_sub3);

        return new ItemChromosome[]{chromosome1.passByvalue(tmpChrom1.toString()), chromosome2.passByvalue(tmpChrom2.toString())};
    }

    public void optimize(Population population, int threshold){
        int count_generation = GeneticAlgorithm.number_of_generation;
        Population tmpPop = population;
        while (count_generation > 0){
            ArrayList<ItemChromosome> privileged = rankSelection(tmpPop.getPopulationSize()/2,tmpPop,threshold);
            ArrayList<ItemChromosome> newPopulation = crossover(privileged,tmpPop.getPopulationSize());
            for (ItemChromosome indiv: newPopulation) {
                indiv.mutation(new Bound(8,17), this.mutation_rate);
            }
            display(count_generation,tmpPop,threshold);
            tmpPop = new Population (tmpPop.getPopulationSize(),newPopulation,population.getShop());
            count_generation--;
        }
    }

    public void display(int generation, Population population, int threshold){
        System.out.println("Chromosomes generated from generation: "+ (number_of_generation-generation+1));
        int count = 1;
        for (ItemChromosome chromosome: population.getPopulation()) {
            System.out.println("chromosome: "+count+" :"+
                    chromosome.getChromosome()+"\t Weight :"+population.calculateChromValues(chromosome,threshold)[0] +
                    "\t Value :"+population.calculateChromValues(chromosome,threshold)[1] );
            count++;
        }
        if(generation == 1){
            this.rankSelection(1,population,threshold).get(0).itemsCollected(number_of_generation);
        }
    }

}