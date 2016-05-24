package players.firstplayer;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import exceptions.IncorrectMovieException;
import learning.genetic.PositionEstimator;
import rules.gamemanager.GameManager;

import java.util.List;

/**
 * Created on 21.05.2016.
 *
 * @author Źmicier Dzikański
 */
public class RandomEstimatorFirstPlayer extends AbstractFirstPlayer {
    private PositionEstimator positionEstimator;

    public RandomEstimatorFirstPlayer() {
    }

    public RandomEstimatorFirstPlayer(PositionEstimator positionEstimator) {
        this.positionEstimator = positionEstimator;
    }

    public PositionEstimator getPositionEstimator() {
        return positionEstimator;
    }

    public void setPositionEstimator(PositionEstimator positionEstimator) {
        this.positionEstimator = positionEstimator;
    }

    @Override
    public void init() {
        float[] coeffs = new float[20];
        for(int i=0; i<20; i++) {
            coeffs[i] = (float)Math.random();
        }
        positionEstimator.setCoeffs(coeffs);
    }

    @Override
    public FirstPlayerMovie movie(Position position, List<FirstPlayerMovie> movies, final GameManager manager) {
        FirstPlayerMovie bestMovie = null;
        float bestEstimate = Float.MIN_VALUE;
        for(FirstPlayerMovie movie: movies) {
            Position potentialPosition = position.clone();
            try{
                manager.getMovieMaker().movie(potentialPosition, movie, false);
            }
            catch(IncorrectMovieException e) { continue; }
            float currEstimate = positionEstimator.estimatePosition(potentialPosition);
            if(currEstimate > bestEstimate) {
                bestEstimate = currEstimate;
                bestMovie = movie;
            }
        }
        return bestMovie != null ? bestMovie : movies.get(0);
    }
}
