package backpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Backpack {

    public static void main(String[] args) {
        Random random = new Random();
        int M = 13;
        int weith[] = new int[M];
        int ci[] = new int[M];
        int L = 150;
        for (int i = 0; i < M; i++) {
            weith[i] = 1 + random.nextInt(L);
            ci[i] = 1 + random.nextInt(L);
        }

        System.out.println(Arrays.toString(weith));
        System.out.println(Arrays.toString(ci));
        System.out.println("L = " + L);
        Data.input(weith, ci, L);
        int best = 0;

        Population population = new Population();
        population.createPopulation();
        int number = 0;
        do {
            population.successAllChromosome();
            population.findBestChromosome();
            population.selestion();
            number++;
        } while (number < M);
        System.out.println("Best chromosome: " + population.findBestChromosome());

        System.out.println("Greedy");
        find();
        System.out.println("price = " + best);
    }
    static int find() {
        ArrayList<Thing> lst=Data.getLst();
        Collections.sort(lst);
        System.out.println("");
        int sumPrice1 = 0;
        int sumW1 = 0;
        int currentL = Data.getL();
        ArrayList result = new ArrayList();
        int wi;
        for (int i=0; i<lst.size(); i++) {
            wi = lst.get(i).getWeight();
            if (currentL < wi) {
                continue;
            }
            result.add(wi);
            sumW1 += wi;
            currentL -= wi;
            sumPrice1 += lst.get(i).getPrice();
        }
        System.out.println(" res = " + result + " = "+ sumW1);
        return sumPrice1;
    }
}
