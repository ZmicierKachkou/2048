package players.secondplayer;

import dto.Coords;
import dto.Position;
import dto.movies.SecondPlayerMovie;
import dto.tiles.Tile;
import exceptions.IncorrectMovieException;
import learning.genetic.PositionEstimator;
import rules.gamemanager.GameManager;

import java.util.List;

/**
 * Created on 20.05.2016.
 *
 * @author Źmicier Dzikański
 */
public class AngryGeneticSecondPlayer extends AbstractSecondPlayer{

    private PositionEstimator positionEstimator;

    public AngryGeneticSecondPlayer() {
    }

    public AngryGeneticSecondPlayer(PositionEstimator positionEstimator) {
        this.positionEstimator = positionEstimator;
    }

    public PositionEstimator getPositionEstimator() {
        return positionEstimator;
    }

    public void setPositionEstimator(PositionEstimator positionEstimator) {
        this.positionEstimator = positionEstimator;
    }

    @Override
    public SecondPlayerMovie movie(Position position, List<Tile> tiles, List<Coords> coords, GameManager manager) {
        SecondPlayerMovie bestMovie = null;
        float bestEstimate = Float.MAX_VALUE;
        for(Coords coord: coords) {
            for (Tile tile : tiles) {
                Position potentialPosition = position.clone();
                SecondPlayerMovie movie = new SecondPlayerMovie(coord.getX(), coord.getY(), tile);
                try {
                    manager.getMovieMaker().movie(potentialPosition, movie);
                } catch (IncorrectMovieException e) {
                    continue;
                }
                float currEstimate = positionEstimator.estimatePosition(potentialPosition);
                if (currEstimate < bestEstimate || bestMovie == null) {
                    bestEstimate = currEstimate;
                    bestMovie = movie;
                }
            }
        }
        return bestMovie;
    }
}
