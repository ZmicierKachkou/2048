package learning.genetic.impl;

import learning.genetic.model.Genome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 20.05.2016.
 *
 * @author Źmicier Dzikański
 */
public class RandomCoeffsGenerator extends BasicCoeffsGenerator {
    @Override
    public List<Genome> getNewGeneration(List<Genome> l) {
        List<Genome> newGeneration = new ArrayList<Genome>(POPULATION_SIZE);
        for(int i = 0; i < 1; i++) newGeneration.add(l.get(i));
        for(int i = 1; i < POPULATION_SIZE; i++) {
            Genome genome = new Genome();
            float average = 0;
            for(int j=0; j < Genome.SIZE; j++) {
                genome.getCoeff()[j] = generateRandomParam();
                average += genome.getCoeff()[j] * genome.getCoeff()[j];
            }
            if(average != 0) {
                average = (float) Math.sqrt(average);
                for(int j=0; j < Genome.SIZE; j++) {
                    genome.getCoeff()[j] /= average;
                }
            }
            newGeneration.add(genome);
        }
        return newGeneration;
    }
}
