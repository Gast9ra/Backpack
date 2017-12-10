package backpack;

import java.util.Random;
import java.util.ArrayList;


public class Chromosome {
    private static ArrayList<Thing> lst=Data.getLst();
    private final int P = 20;
    Random random = new Random();

    private String genome = "";
    
    private String getGenome() {
        return genome;
    }

    private void setGenome(String genome) {
        this.genome = genome;
    }
        

    private int best;
    
    int getBest() {
        return best;
    }
    
    private void setBest(int best) {
        this.best = best;
    }
    
    @Override
    protected Object clone() {
        Chromosome result = new Chromosome();
        result.setBest(best);
        result.setGenome(genome);
        return result;
    }

    @Override
    public String toString() {
        return "genome: " + genome + "   best:" + best + "\n";
    }


    void fillingChromosome() {
        StringBuilder gen = new StringBuilder();
        int random01;
        for (int i=0; i<lst.size(); i++) {
            random01 = random.nextInt(2);
            if (random01 == 0)
                gen.append('0');
            else
                gen.append('1');            
        }
        genome = gen.toString();
    }

    void calculateBest() {
        Thing fillPack = getFillPack();
        if (fillPack.getWeight() <= Data.getL())
            best = fillPack.getPrice();
    }


    private Thing getFillPack() {
        int weightSum = 0; 
        int priceSum = 0; 
        for(int i=0; i<lst.size(); i++) {
            if(genome.charAt(i) == '1') {
                weightSum += lst.get(i).getWeight();
                priceSum += lst.get(i).getPrice();
            }
        }
        return new Thing(weightSum, priceSum);
    }

    Chromosome Crossing(Chromosome parent2) {
        Chromosome[] resChild =  new Chromosome[2];
        resChild[0] = new Chromosome();
        resChild[1] = new Chromosome();
        int cross = random.nextInt(lst.size()-1);
        StringBuilder resGenome0 = new StringBuilder();
        StringBuilder resGenome1 = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            if (i <= cross) {
                resGenome0.append(this.getGenome().charAt(i));
                resGenome1.append(parent2.getGenome().charAt(i));
            } else {
                resGenome0.append(parent2.getGenome().charAt(i));
                resGenome1.append(this.getGenome().charAt(i));
            }
        }
        resChild[0].setGenome(resGenome0.toString());
        resChild[1].setGenome(resGenome1.toString());        
        int childNumber = random.nextInt(2);
        return resChild[childNumber];
    }
    

    Chromosome mutation() {
        Chromosome result = (Chromosome) this.clone();
        StringBuilder resGenome = new StringBuilder(genome);
        char oldValue;
        char newValue;
        int randomPercent;
        for (int i=0; i<lst.size(); i++) {
            randomPercent = random.nextInt(100);
            if (randomPercent < P) {
                oldValue = genome.charAt(i);
                if (oldValue == 0)
                    newValue = 1;
                else
                    newValue = 0;       
                resGenome.setCharAt(i, newValue);
            }
        }
        result.setGenome(resGenome.toString());        
        return result;
    }

}
