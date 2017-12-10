package backpack;

import java.util.Random;

public class Population {

    private final int populationSize = 100; 

    public int getPopulationSize() {
        return populationSize;
    }


    private Chromosome population[];

    Population() {
        population = new Chromosome[populationSize];
    }


    void createPopulation(){
        for (int i=0; i<populationSize; i++) {
            population[i] = new Chromosome();
            population[i].fillingChromosome();
        }			
    }	
        

    void successAllChromosome() {
	for (int i=0; i<populationSize; i++){
            population[i].calculateBest();
	}
    }
    

    Chromosome findBestChromosome(){
        int maxBest = 0;
        Chromosome result = population[0];
        for (int i=0; i<populationSize; i++) {
            if (population[i].getBest() > maxBest) {
                result = population[i];
                maxBest = population[i].getBest();
            }
        }
        return result;
    }
    
    void selestion() {
        int[][] pairs = getPairsForCrossing();
        Chromosome nextPopulation[] = new Chromosome[populationSize];
        nextPopulation = getNextPopulation(pairs);
        this.population = nextPopulation;
    } 

    private int[][] getPairsForCrossing() {
        int[][] pairs = new int[populationSize][2];
        int hromosome1;
        int hromosome2;
        for (int i=0; i<populationSize; i++) {
            hromosome1 = findBestTournament(-1);
            hromosome2 = findBestTournament(hromosome1);
            pairs[i][0] = hromosome1;
            pairs[i][1] = hromosome2;
        }
        return pairs;
    }
        
    private int findBestTournament(int index) {
        int bestChromosomeIndex = 0;
        int maxBest = 0;
        int currentIndex;
        Random random = new Random();
        int tournamentNumber = populationSize / 2;
        for (int i = 0; i< tournamentNumber; i++) {
            currentIndex = random.nextInt(populationSize);
            if (currentIndex == index) {
                i--;
                continue;
            }
            if (population[currentIndex].getBest() > maxBest) {
                maxBest = population[currentIndex].getBest();
                bestChromosomeIndex = currentIndex;
            }
        }
        return bestChromosomeIndex;
    }
        

    private Chromosome[] getNextPopulation(int[][] pairs) {
        Chromosome nextPopulation[] = new Chromosome[populationSize];
        nextPopulation[0] = findBestChromosome();
        Chromosome parent1;
        Chromosome parent2;
        Chromosome resultChild;
        for (int i = 1; i < populationSize; ++i) {
            parent1 = population[pairs[i][0]];
            parent2 = population[pairs[i][1]];
            resultChild = parent1.Crossing(parent2);
            nextPopulation[i] = resultChild;
            nextPopulation[i] = nextPopulation[i].mutation();
        }
        return nextPopulation;
    }


}
