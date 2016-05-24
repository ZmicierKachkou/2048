package rules.moviemaker.impl;

import dto.Coords;
import dto.Position;
import dto.Result;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import dto.tiles.IntegerTile;
import dto.tiles.Tile;
import exceptions.IncorrectMovieException;
import exceptions.MergeNotPossibleException;
import rules.moviemaker.MovieMaker;
import rules.moviemaker.impl.dto.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 19.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class SimpleMovieMaker2048 extends MovieMaker {
    Map<FirstPlayerMovie, Result> cachedMovies = new HashMap<FirstPlayerMovie, Result>();

    private boolean isCorrectMovie(Position position, FirstPlayerMovie movie) {

        if(movie.getValue() < 0 || movie.getValue() > 3) {
            return false;
        }

        Position newPosition = position.clone();
        int points = 0;
        try {
            points = movie(newPosition, movie);
        }
        catch(IncorrectMovieException e) {
            return false;
        }

        if(!newPosition.equals(position)) {
            cachedMovies.put(movie, new Result(newPosition, 0, points));
            return true;
        }
        return false;
    }

    private boolean isCorrectMovie(Position position, SecondPlayerMovie movie) {
        return position.getTile(movie.getX(), movie.getY()) == null;
    }

    private int movieCore(Position position, FirstPlayerMovie movie) throws IncorrectMovieException {

        int points = 0;
        Direction d = new Direction(position, movie);
        for(int x = 0; x < position.getSize(); x++) {
            int y = 1;
            int curr = 0;
            while(y < position.getSize()) {
                if(d.getTile(x, curr) == null) {
                    if(d.getTile(x, y) != null) {
                        d.setTile(d.getTile(x, y), x, curr);
                        d.setTile(null, x, y);
                    }
                    y++;
                }
                else {
                    if(d.getTile(x, y) != null) {
                        if(getTilesMerger().isMerged(d.getTile(x, curr), d.getTile(x, y))) {
                            try {
                                points+=getTilesMerger().pointsForMerge(d.getTile(x, curr), d.getTile(x, y));
                                d.setTile(getTilesMerger().merge(d.getTile(x, curr), d.getTile(x, y)), x, curr);
                                d.setTile(null, x, y);
                            }
                            catch(MergeNotPossibleException e) {
                                throw new IncorrectMovieException(e.getMessage());
                            }
                        }
                        else if(y > curr+1) {
                            d.setTile(d.getTile(x, y), x, curr+1);
                            d.setTile(null, x, y);
                        }
                        curr++;
                    }
                    y++;
                }
            }
        }
        return points;
    }

    @Override
    public int movie(Position position, FirstPlayerMovie movie, boolean clearCache) throws IncorrectMovieException {
        Result movieResult = cachedMovies.get(movie);
        if(clearCache) {
            cachedMovies.clear();
        }
        if(movieResult != null) {
            position.setTable(movieResult.getPosition().getTable());
            return movieResult.getPoints();
        } else {
            return movieCore(position, movie);
        }
    }

    @Override
    public int movie(Position position, FirstPlayerMovie movie) throws IncorrectMovieException {
       return movie(position, movie, true);
    }

    @Override
    public void movie(Position pos, SecondPlayerMovie movie) throws IncorrectMovieException {
        if(pos.getTile(movie.getX(), movie.getY()) != null) {
            throw new IncorrectMovieException();
        }
        pos.setTile(movie.getX(), movie.getY(), movie.getTile());
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
        if(pos == null) return null;
        List<Coords> list = new ArrayList<Coords>();
        for(int x=0; x<pos.getSize(); x++) {
            for(int y=0; y<pos.getSize(); y++) {
                if(pos.getTile(x, y) == null) list.add(new Coords(x, y));
            }
        }
        return list;
    }
}
