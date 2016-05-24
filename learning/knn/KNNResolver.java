package learning.knn;

import dto.Position;
import dto.movies.FirstPlayerMovie;

import java.util.List;

/**
 * Created on 06.04.2016.
 *
 * @author Źmicier Dzikański
 */
public interface KNNResolver {
    void load(String fileName);

    FirstPlayerMovie findNearest(Position position, List<FirstPlayerMovie> movies);
}
