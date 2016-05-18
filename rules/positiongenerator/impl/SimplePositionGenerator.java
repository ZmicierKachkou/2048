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
    public Position generatePosition(AbstractSecondPlayer player, MovieMaker movieMaker, int size) {
        Position pos = new Position(size);
        List<Coords> coords = movieMaker.getEmptyCells(pos);
        List<Tile> tiles = movieMaker.getCorrectSecondPlayerMovies(pos);
        SecondPlayerMovie movie = player.movie(pos, tiles, coords);
        movieMaker.movie(pos, movie);
        movie = player.movie(pos, tiles, coords);
        movieMaker.movie(pos, movie);
        return pos;
    }
}
