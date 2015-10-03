package players.firstplayer;

import dto.Position;
import dto.movies.FirstPlayerMovie;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractFirstPlayer {
    public abstract FirstPlayerMovie movie(Position position, FirstPlayerMovie[] movies);
}
