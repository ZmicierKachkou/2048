package players.firstplayer;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import exceptions.IncorrectMovieException;
import players.firstplayer.genetic.PositionEstimator;
import rules.gamemanager.GameManager;

import java.util.List;

/**
 * Created on 14.11.2015.
 *
 * @author Źmicier Dzikański
 */
public class GeneticFirstPlayer extends AbstractFirstPlayer{

    private PositionEstimator positionEstimator;

    public GeneticFirstPlayer() {
    }

    public GeneticFirstPlayer(PositionEstimator positionEstimator) {
        this.positionEstimator = positionEstimator;
    }

    public PositionEstimator getPositionEstimator() {
        return positionEstimator;
    }

    public void setPositionEstimator(PositionEstimator positionEstimator) {
        this.positionEstimator = positionEstimator;
    }

    @Override
    public FirstPlayerMovie movie(Position position, List<FirstPlayerMovie> movies, final GameManager manager) {
        FirstPlayerMovie bestMovie = null;
        float bestEstimate = Float.MIN_VALUE;
        for(FirstPlayerMovie movie: movies) {
            Position potentialPosition = position.clone();
            try{
                manager.getMovieMaker().movie(potentialPosition, movie);
            }
            catch(IncorrectMovieException e) { continue; }
            float currEstimate = positionEstimator.estimatePosition(potentialPosition);
            System.out.println("Movie: " + movie + ", estimation: " + currEstimate);
            if(currEstimate > bestEstimate) {
                bestEstimate = currEstimate;
                bestMovie = movie;
            }
        }
        return bestMovie != null ? bestMovie : movies.get(0);
    }
}
