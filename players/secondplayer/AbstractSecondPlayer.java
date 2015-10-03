package players.secondplayer;

import dto.Position;
import dto.tiles.Tile;
import dto.movies.SecondPlayerMovie;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractSecondPlayer {
    public abstract SecondPlayerMovie movie(Position position, Tile[] tiles);
}
