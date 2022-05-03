package com.company;

public class ItemChromosome {
    private String chromosome;
    Shop shop;
    public ItemChromosome() {
    }

    public ItemChromosome(Shop shop) {
        this.chromosome = generateSequence(shop.getNumber_items());
        setShop(shop);
    }

    public ItemChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    /*For passing the string containing the representation of a chromosome
        and not the chromosome itself
       */
    public ItemChromosome passByvalue(String string){
       ItemChromosome chrom = new ItemChromosome();
       chrom.setChromosome(string);
       return chrom;
    }

    //Creating the string of chromosomes of zero's and 1's this is done randomly
    String generateSequence(int number_of_char){
        StringBuilder genStr = new StringBuilder(number_of_char);
        for(int i=0; i<number_of_char; i++){
            int[] prob = new Bound(1,10).generateRandomGroup(1);
            char c;
            if(prob[0]>5 ){
                c='1';
            }else{
                c='0';
            }

            genStr.append(c);
        }
        return genStr.toString();
    }


    //Mutation of a chromosome, the range is used to specify the position of the genes to flip
    public void mutation(Bound range,double mutationRate){

        if(Double.compare(Math.random(),mutationRate)>0)
            return;

        StringBuilder genStr = new StringBuilder(this.getChromosome().length());

        for(int i=0; i<this.getChromosome().length(); i++){
            if(i>= (range.getMin()-1) && i<range.getMax() ){
                genStr.append(flip(this.getChromosome().charAt(i)));
            }else{
                genStr.append(this.getChromosome().charAt(i));
            }

        }
        this.setChromosome(genStr.toString());
    }

    public void itemsCollected(int generation){
        System.out.println("THE BEST SOLUTION AFTER "+generation+" IS GENERATED FROM: ");
        for(int i=0; i<this.getChromosome().length(); i++){
            char c = this.getChromosome().charAt(i);
            if(c=='1' ){
                int x =this.getShop().getValue()[i];
                int y = this.getShop().getWeight()[i];
                System.out.println("Item "+i+" with Value "+x+" & Weight "+ y);
            }
        }
    }

    char flip(char c){
        return (c == '0')?'1':'0';
    }

    public String getChromosome() {
        return chromosome;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
}
