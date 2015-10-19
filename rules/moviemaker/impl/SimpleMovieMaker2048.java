package rules.moviemaker.impl;

import dto.Coords;
import dto.Position;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import dto.tiles.IntegerTile;
import dto.tiles.Tile;
import rules.moviemaker.MovieMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 19.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class SimpleMovieMaker2048 implements MovieMaker {
    @Override
    public boolean isCorrectMovie(Position position, FirstPlayerMovie movie) {
        Position newPosition = position.clone();
        newPosition = movie(newPosition, movie);
        return !newPosition.equals(position);
    }

    @Override
    public boolean isCorrectMovie(Position position, SecondPlayerMovie movie) {
        return position.getTile(movie.getX(), movie.getY()) == null;
    }

    @Override
    public Position movie(Position position, FirstPlayerMovie movie) {
        // TODO
        return null;
    }

    @Override
    public Position movie(Position pos, SecondPlayerMovie movie) {
        pos.setTile(movie.getX(), movie.getY(), movie.getTile());
        return pos;
    }

    @Override
    public List<FirstPlayerMovie> getCorrectFirstPlayerMovies(Position pos) {
        List<FirstPlayerMovie> list = new ArrayList<FirstPlayerMovie>();
        for(FirstPlayerMovie movie: FirstPlayerMovie.values()) if(isCorrectMovie(pos, movie)) list.add(movie);
        return list;
    }

    @Override
    public List<Tile> getCorrectSecondPlayerMovies(Position pos) {
        List<Tile> list = new ArrayList<Tile>();
        for(int i=0; i<9; i++) list.add(new IntegerTile(2));
        list.add(new IntegerTile(4));
        return list;
    }

    @Override
    public List<Coords> getEmptyCells(Position pos) {
        List<Coords> list = new ArrayList<Coords>();
        for(int x=0; x<pos.getSize(); x++) {
            for(int y=0; y<pos.getSize(); y++) {
                if(pos.getTile(x, y) == null) list.add(new Coords(x, y));
            }
        }
        return list;
    }
}
