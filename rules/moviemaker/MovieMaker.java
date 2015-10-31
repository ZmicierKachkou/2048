package rules.moviemaker;

import dto.Coords;
import dto.Position;
import dto.tiles.Tile;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import exceptions.IncorrectMovieException;
import rules.tilesmerger.TileMerger;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public interface MovieMaker {
    TileMerger getTileMerger();
    void setTileMerger(TileMerger tileMerger);

    boolean isCorrectMovie(Position position, FirstPlayerMovie movie);
    boolean isCorrectMovie(Position position, SecondPlayerMovie movie);

    void movie(Position position, FirstPlayerMovie movie) throws IncorrectMovieException;
    void movie(Position position, SecondPlayerMovie movie);

    List<FirstPlayerMovie> getCorrectFirstPlayerMovies(Position position);
    List<Tile> getCorrectSecondPlayerMovies(Position position);
    List<Coords> getEmptyCells(Position position);
}
