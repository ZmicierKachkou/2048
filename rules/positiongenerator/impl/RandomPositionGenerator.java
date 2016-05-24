package rules.positiongenerator.impl;

import dto.Coords;
import dto.Position;
import dto.movies.SecondPlayerMovie;
import dto.tiles.Tile;
import exceptions.IncorrectMovieException;
import players.secondplayer.AbstractSecondPlayer;
import rules.gamemanager.GameManager;
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
    public Position generatePosition(AbstractSecondPlayer player, GameManager gameManager, int size) {
        Position pos = new Position(size);
        List<Coords> coords = gameManager.getMovieMaker().getEmptyCells(pos);
        List<Tile> tiles = gameManager.getMovieMaker().getCorrectSecondPlayerMovies(pos);
        int n = (int)Math.floor(Math.random() * coords.size());
        SecondPlayerMovie movie = new SecondPlayerMovie(coords.get(n), tiles.get((int)Math.floor(Math.random() * tiles.size())));
        try {
            gameManager.getMovieMaker().movie(pos, movie);
            if (coords.size() > 1) {
                coords.remove(n);
                movie = new SecondPlayerMovie(coords.get((int) Math.floor(Math.random() * (coords.size()))),
                        tiles.get((int) Math.floor(Math.random() * tiles.size())));
                gameManager.getMovieMaker().movie(pos, movie);
            }
        } catch(IncorrectMovieException e) {
            //#TODO
        }
        return pos;
    }

}
