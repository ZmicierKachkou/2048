package rules.moviemaker;

import dto.Coords;
import dto.Position;
import dto.tiles.Tile;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public interface MovieMaker {
    public boolean isCorrectMovie(Position position, FirstPlayerMovie movie);
    public boolean isCorrectMovie(Position position, SecondPlayerMovie movie);

    public Position movie(Position position, FirstPlayerMovie movie);
    public Position movie(Position position, SecondPlayerMovie movie);

    public FirstPlayerMovie[] getCorrectFirstPlayerMovies(Position position);
    public Tile[] getCorrectSecondPlayerMovies(Position position);
    public Coords[] getEmptyCells(Position position);
}
