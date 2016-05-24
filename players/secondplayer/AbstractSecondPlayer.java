package players.secondplayer;

import dto.Coords;
import dto.Position;
import dto.tiles.Tile;
import dto.movies.SecondPlayerMovie;
import rules.gamemanager.GameManager;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public abstract class AbstractSecondPlayer {
    public abstract SecondPlayerMovie movie(Position position, List<Tile> tiles, List<Coords> coords, GameManager manager);

    public void init() {

    }
}
