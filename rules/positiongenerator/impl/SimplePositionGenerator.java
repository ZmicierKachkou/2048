package rules.positiongenerator.impl;

import dto.Coords;
import dto.Position;
import dto.movies.SecondPlayerMovie;
import dto.tiles.Tile;
import players.secondplayer.AbstractSecondPlayer;
import rules.moviemaker.MovieMaker;
import rules.positiongenerator.PositionGenerator;

import java.util.List;

/**
 * Created on 19.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class SimplePositionGenerator implements PositionGenerator {

    @Override
    public Position generatePosition(AbstractSecondPlayer player, List<Tile> tiles, List<Coords> coords, MovieMaker movieMaker) {
        Position pos = new Position();
        SecondPlayerMovie movie = player.movie(pos, tiles, coords);
        movieMaker.movie(pos, movie);
        movie = player.movie(pos, tiles, coords);
        movieMaker.movie(pos, movie);
        return pos;
    }
}
