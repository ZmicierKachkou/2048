package rules.moviemaker;

import dto.Coords;
import dto.Position;
import dto.tiles.Tile;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import exceptions.IncorrectMovieException;
import rules.tilesmerger.TilesMerger;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 *
 * This manager is responsible for checking movies. It uses TileMerger for correct tile merge
 */
public abstract class MovieMaker {
    private TilesMerger tilesMerger;

    public TilesMerger getTilesMerger() {
        return tilesMerger;
    }

    public void setTilesMerger(TilesMerger tilesMerger) {
        this.tilesMerger = tilesMerger;
    }

    public abstract int movie(Position position, FirstPlayerMovie movie) throws IncorrectMovieException;
    public abstract int movie(Position position, FirstPlayerMovie movie, boolean clearCache) throws IncorrectMovieException;
    public abstract void movie(Position position, SecondPlayerMovie movie);

    public abstract List<FirstPlayerMovie> getCorrectFirstPlayerMovies(Position position);
    public abstract List<Tile> getCorrectSecondPlayerMovies(Position position);
    public abstract List<Coords> getEmptyCells(Position position);
}
