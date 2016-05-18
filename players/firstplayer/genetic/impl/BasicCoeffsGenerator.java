package players.firstplayer.genetic.impl;

import dto.Result;
import players.firstplayer.GeneticFirstPlayer;
import players.firstplayer.genetic.CoeffsGenerator;
import players.firstplayer.genetic.model.Genome;
import server.Server2048;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public class BasicCoeffsGenerator implements CoeffsGenerator {
    public static final int POPULATION_SIZE = 100;
    public static final double ELITE_RATE = 0.05;
    public static final double SURVIVE_RATE = 0.1;
    public static final double MUTATION_RATE = 0.05;

    private Server2048 server;

    public Server2048 getServer() {
        return server;
    }

    public void setServer(Server2048 server) {
        this.server = server;
    }

    private float generateRandomParam() {
        return (float)(Math.random());
    }

    @Override
    public List<Genome> initializePopulation() {
        List<Genome> list = new ArrayList<Genome>(POPULATION_SIZE);
        for(int i=0; i < POPULATION_SIZE; i++) {
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
            list.add(genome);
        }
        return list;
    }

    @Override
    public float countFitness(Genome g) {
        if(g.getFitness() > -0.5) return g.getFitness();
        float fitness = 0;
        if(!(server.getFirst() instanceof GeneticFirstPlayer)) return 0;
        else {
            GeneticFirstPlayer pl = (GeneticFirstPlayer) server.getFirst();
            pl.getPositionEstimator().setCoeffs(g.getCoeff());
            server.getSecond().init();
            for(int i = 0; i < 5; i++) {
                Result r = server.playGame();
                fitness += r.getPoints();
            }
        }
        return (float) (fitness/5.);
    }


    @Override
    public void mutate(Genome g, int i) {
         g.getCoeff()[i] = (float)(Math.random());
     }

    @Override
    public List<Genome> getNewGeneration(List<Genome> l) {
        int esize = (int) (POPULATION_SIZE * ELITE_RATE);
        List<Genome> newGeneration = new ArrayList<Genome>(POPULATION_SIZE);
        for(int i = 0; i < esize; i++) newGeneration.add(l.get(i));
        for(int i = esize; i < POPULATION_SIZE; i++) {
            Genome newCitizen = new Genome();
            int i1 = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            int i2 = (int) (Math.random() * POPULATION_SIZE * SURVIVE_RATE);
            float average = 0;
            for(int j = 0; j < Genome.SIZE; j++) {
                double rand = Math.random();
                newCitizen.getCoeff()[j] = (float) (l.get(i1).getCoeff()[j] * rand + l.get(i2).getCoeff()[j] * rand * (1 - rand));
                if(Math.random() < MUTATION_RATE) mutate(newCitizen, j);
                average += newCitizen.getCoeff()[j] * newCitizen.getCoeff()[j];
            }
            if(average != 0) {
                average = (float) Math.sqrt(average);
                for(int j=0; j < Genome.SIZE; j++) {
                    newCitizen.getCoeff()[j] /= average;
                }
            }
            newGeneration.add(newCitizen);
        }
        return newGeneration;
    }

    @Override
    public void analyzeGeneration(List<Genome> parents) {
        for(Genome g: parents) {
            g.setFitness(countFitness(g));
        }
        Collections.sort(parents);
    }
}
