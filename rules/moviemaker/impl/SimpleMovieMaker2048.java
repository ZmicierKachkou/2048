package rules.moviemaker.impl;

import dto.Coords;
import dto.Position;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import dto.tiles.IntegerTile;
import dto.tiles.Tile;
import exceptions.IncorrectMovieException;
import exceptions.MergeNotPossibleException;
import rules.moviemaker.MovieMaker;
import rules.tilesmerger.TileMerger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 19.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class SimpleMovieMaker2048 implements MovieMaker {
    private TileMerger tileMerger;

    @Override
    public TileMerger getTileMerger() {
        return tileMerger;
    }

    @Override
    public void setTileMerger(TileMerger tileMerger) {
        this.tileMerger = tileMerger;
    }

    @Override
    public boolean isCorrectMovie(Position position, FirstPlayerMovie movie) {
        if(movie.getValue() < 0 || movie.getValue() > 3) return false;
        Position newPosition = position.clone();
        try {
            movie(newPosition, movie);
        }
        catch(IncorrectMovieException e) {
            return false;
        }
        return !newPosition.equals(position);
    }

    @Override
    public boolean isCorrectMovie(Position position, SecondPlayerMovie movie) {
        return position.getTile(movie.getX(), movie.getY()) == null;
    }

   @Override
    public void movie(Position position, FirstPlayerMovie movie) throws IncorrectMovieException {
        class Direction {
            private FirstPlayerMovie direction;
            private Position position;
            Direction(Position position, FirstPlayerMovie direction) {
                this.position = position;
                this.direction = direction;
            }

            private int getX(int x, int y) {
                if(direction == FirstPlayerMovie.UP) return x;
                else if(direction == FirstPlayerMovie.DOWN) return position.getSize()-x-1;
                else if(direction == FirstPlayerMovie.LEFT) return y;
                else return position.getSize()-y-1;
            }

            private int getY(int x, int y) {
                if(direction == FirstPlayerMovie.UP) return y;
                else if(direction == FirstPlayerMovie.DOWN) return position.getSize()-y-1;
                else if(direction == FirstPlayerMovie.LEFT) return x;
                else return position.getSize()-x-1;
            }

            public Tile getTile(int x, int y) {
                return position.getTile(getX(x, y), getY(x, y));
            }

            public void setTile(Tile t, int x, int y) {
                position.setTile(getX(x, y), getY(x, y), t);
            }
        }

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
                        if(tileMerger.isMerged(d.getTile(x, curr), d.getTile(x, y))) {
                            try {
                                d.setTile(tileMerger.merge(d.getTile(x, curr), d.getTile(x, y)), x, curr);
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
    }

    @Override
    public void movie(Position pos, SecondPlayerMovie movie) {
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
