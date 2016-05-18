package players.firstplayer.genetic;

import players.firstplayer.genetic.model.Genome;
import server.Server2048;

import java.util.List;

/**
 * Created on 28.02.2016.
 *
 * @author Źmicier Dzikański
 */
public interface CoeffsGenerator {
    List<Genome> initializePopulation();

    float countFitness(Genome g);

    List<Genome> getNewGeneration(List<Genome> l);
    void mutate(Genome g, int i);

    void analyzeGeneration(List<Genome> parents);

    Server2048 getServer();
    void setServer(Server2048 server);
}
