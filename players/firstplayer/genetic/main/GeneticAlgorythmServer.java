package players.firstplayer.genetic.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import players.firstplayer.genetic.CoeffsGenerator;
import players.firstplayer.genetic.impl.BasicCoeffsGenerator;
import players.firstplayer.genetic.model.Genome;

import java.util.List;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public class GeneticAlgorythmServer {
    public final static int ITERATIONS = 2500;

    CoeffsGenerator coeffsGenerator;

    public CoeffsGenerator getCoeffsGenerator() {
        return coeffsGenerator;
    }

    public void setCoeffsGenerator(CoeffsGenerator coeffsGenerator) {
        this.coeffsGenerator = coeffsGenerator;
    }

    public static void main(String args[]) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");
        GeneticAlgorythmServer server = context.getBean(GeneticAlgorythmServer.class);
        List<Genome> population = server.coeffsGenerator.initializePopulation();
        server.coeffsGenerator.analyzeGeneration(population);
        for(int i=0; i<ITERATIONS; i++) {
            population = server.getCoeffsGenerator().getNewGeneration(population);
            server.coeffsGenerator.analyzeGeneration(population);
            System.out.print(i + " step: ");
            for(float f: population.get(0).getCoeff()) System.out.print(f + "f, ");
            System.out.println("result: " + population.get(0).getFitness());
        }
    }
}
