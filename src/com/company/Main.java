package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    //write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("***** Welcome to this our Knapsack Carry away *****\n");
        System.out.println("Enter the cross over rate: ");
        double crossOver = scanner.nextDouble();
        System.out.println("Enter the mutation over rate: ");
        double mutation = scanner.nextDouble();
        System.out.println("Enter the threshold of your plane: ");
        int threshold = scanner.nextInt(); //[260,450]
        GeneticAlgorithm algo = new GeneticAlgorithm(crossOver,mutation);
        Shop shopCountry = new Shop(30,1,70,200,1000);
        System.out.println(shopCountry.toString());
        Population population = new Population(10,shopCountry);
        algo.optimize(population,threshold);


    }
}
