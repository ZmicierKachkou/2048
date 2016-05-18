package players.firstplayer.knn.impl;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import players.firstplayer.knn.KNNResolver;

import java.util.List;

/**
 * Created on 06.04.2016.
 *
 * @author Źmicier Dzikański
 */
public class BasicKNNResolver implements KNNResolver {
    @Override
    public FirstPlayerMovie findNearest(Position position, List<FirstPlayerMovie> movies, int level, float lowerResult) {
        float diff = Float.MIN_VALUE;
        FirstPlayerMovie bestMovie = null;
        for(FirstPlayerMovie movie: movies) {
            float currDiff = calculateLength(position, movie, level, lowerResult);
            if(currDiff > diff) {
                diff = currDiff;
                bestMovie = movie;
            }
        }
        return bestMovie == null ? movies.get(0) : bestMovie;
    }

    private float calculateLength(Position position, FirstPlayerMovie movie, int level, float lowerResult) {
        return 0;
    }
}
