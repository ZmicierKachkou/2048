package players.secondplayer;

import dto.Coords;
import dto.Position;
import dto.tiles.Tile;
import dto.movies.SecondPlayerMovie;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractSecondPlayer {
    public abstract SecondPlayerMovie movie(Position position, List<Tile> tiles, List<Coords> coords);

    public void init() {

    }
}
