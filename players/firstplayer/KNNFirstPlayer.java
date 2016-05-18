package players.firstplayer;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import players.firstplayer.knn.KNNResolver;
import rules.gamemanager.GameManager;

import java.util.List;

/**
 * Created on 06.04.2016.
 *
 * @author Źmicier Dzikański
 */
public class KNNFirstPlayer extends AbstractFirstPlayer{

    private KNNResolver kNNResolver;

    public KNNResolver getkNNResolver() {
        return kNNResolver;
    }

    public void setkNNResolver(KNNResolver kNNResolver) {
        this.kNNResolver = kNNResolver;
    }

    @Override
    public FirstPlayerMovie movie(Position position, List<FirstPlayerMovie> movies, GameManager manager) {
        return kNNResolver.findNearest(position, movies, 1, 0);
    }
}
