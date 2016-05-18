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
public class RandomPositionGenerator implements PositionGenerator {

    @Override
    public Position generatePosition(AbstractSecondPlayer player, MovieMaker movieMaker, int size) {
        Position pos = new Position(size);
        List<Coords> coords = movieMaker.getEmptyCells(pos);
        List<Tile> tiles = movieMaker.getCorrectSecondPlayerMovies(pos);
        int n = (int)Math.floor(Math.random() * coords.size());
        SecondPlayerMovie movie = new SecondPlayerMovie(coords.get(n), tiles.get((int)Math.floor(Math.random() * tiles.size())));
        movieMaker.movie(pos, movie);
        if(coords.size() > 1) {
            coords.remove(n);
            movie = new SecondPlayerMovie(coords.get((int)Math.floor(Math.random() * (coords.size()))),
                    tiles.get((int)Math.floor(Math.random() * tiles.size())));
            movieMaker.movie(pos, movie);
        }
        return pos;
    }

}
