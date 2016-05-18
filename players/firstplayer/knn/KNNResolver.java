package players.firstplayer.knn;

import dto.Position;
import dto.movies.FirstPlayerMovie;

import java.util.List;

/**
 * Created on 06.04.2016.
 *
 * @author Źmicier Dzikański
 */
public interface KNNResolver {
    FirstPlayerMovie findNearest(Position position, List<FirstPlayerMovie> movies, int level, float lowerResult);
}
