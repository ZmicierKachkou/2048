package players.secondplayer;

import dto.Coords;
import dto.Position;
import dto.movies.SecondPlayerMovie;
import dto.tiles.Tile;

import java.util.List;

/**
 * Created on 28.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class RandomSecondPlayer extends AbstractSecondPlayer {
    @Override
    public SecondPlayerMovie movie(Position position, List<Tile> tiles, List<Coords> coords) {
        return new SecondPlayerMovie(coords.get((int)Math.floor(Math.random() * coords.size())),
                tiles.get((int)Math.floor(Math.random() * tiles.size())));
    }
}
