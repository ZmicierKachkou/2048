package rules.positiongenerator;

import dto.Coords;
import dto.Position;
import dto.tiles.Tile;
import players.secondplayer.AbstractSecondPlayer;
import rules.moviemaker.MovieMaker;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public interface PositionGenerator {
    Position generatePosition(AbstractSecondPlayer player, List<Tile> tiles, List<Coords> coords, MovieMaker movieMaker);
}
