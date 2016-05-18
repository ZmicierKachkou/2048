package players.secondplayer;

import dto.Coords;
import dto.Position;
import dto.movies.SecondPlayerMovie;
import dto.tiles.Tile;

import java.util.List;

/**
 * Created on 15.11.2015.
 *
 * @author Źmicier Dzikański
 */
public class QuasiRandomSecondPlayer extends AbstractSecondPlayer{
    private int movie = 0;

    @Override
    public void init() {
        movie = 0;
    }

    @Override
    public SecondPlayerMovie movie(Position position, List<Tile> tiles, List<Coords> coords) {
        movie++;
        return new SecondPlayerMovie(coords.get(Math.abs(31*movie + position.hashCode()) % coords.size()),
                tiles.get(Math.abs(movie + 31 * position.hashCode()) % tiles.size()));
    }
}
