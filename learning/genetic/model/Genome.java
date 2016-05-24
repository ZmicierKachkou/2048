package learning.genetic.model;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public class Genome implements Comparable<Genome> {
    private float[] coeff;
    private float fitness = -1f;
    public static final int SIZE = 20;

    public Genome() {
        coeff = new float[SIZE];
    }

    public Genome(float[] coeff) {
        this.coeff = coeff;
    }

    public float[] getCoeff() {
        return coeff;
    }

    public void setCoeff(float[] coeff) {
        this.coeff = coeff;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    @Override
    public int compareTo(Genome o) {
        return (int)(o.fitness - fitness);
    }
}
