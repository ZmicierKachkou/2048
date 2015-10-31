package rules.gamemanager.impl;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import exceptions.IncorrectMovieException;
import players.firstplayer.AbstractFirstPlayer;
import players.secondplayer.AbstractSecondPlayer;
import rules.finishchecker.FinishChecker;
import rules.gamemanager.GameManager;
import rules.moviemaker.MovieMaker;
import rules.positiongenerator.PositionGenerator;
import rules.tilesmerger.TileMerger;

/**
 * Created on 18.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class SimpleGameManager implements GameManager {
    private AbstractFirstPlayer first;
    private AbstractSecondPlayer second;
    private PositionGenerator positionGenerator;
    private MovieMaker movieMaker;
    private FinishChecker finishChecker;
    private int size;

    public PositionGenerator getPositionGenerator() {
        return positionGenerator;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int i) {
        size = i;
    }

    public void setPositionGenerator(PositionGenerator positionGenerator) {
        this.positionGenerator = positionGenerator;
    }

    public MovieMaker getMovieMaker() {
        return movieMaker;
    }

    public void setMovieMaker(MovieMaker movieMaker) {
        this.movieMaker = movieMaker;
    }

    @Override
    public Position generatePosition() {
        Position position = new Position(size);
        return positionGenerator.generatePosition(second, movieMaker.getCorrectSecondPlayerMovies(position),
                movieMaker.getEmptyCells(position), movieMaker);
    }

    @Override
    public FirstPlayerMovie findFirstMovie(Position pos) {
        return first.movie(pos, movieMaker.getCorrectFirstPlayerMovies(pos));
    }

    @Override
    public SecondPlayerMovie findSecondMovie(Position pos) {
        return second.movie(pos, movieMaker.getCorrectSecondPlayerMovies(pos), movieMaker.getEmptyCells(pos));
    }

    @Override
    public void makeMovie(Position pos, FirstPlayerMovie movie) throws IncorrectMovieException {
        movieMaker.movie(pos, movie);
    }

    @Override
    public void makeMovie(Position pos, SecondPlayerMovie movie) {
        movieMaker.movie(pos, movie);
    }

    public FinishChecker getFinishChecker() {
        return finishChecker;
    }

    public void setFinishChecker(FinishChecker finishChecker) {
        this.finishChecker = finishChecker;
    }

    @Override
    public boolean isFinish(Position position) {
        return finishChecker.isFinish(position, movieMaker);
    }

    @Override
    public TileMerger getTileMerger() {
        return movieMaker.getTileMerger();
    }

    @Override
    public void setTileMerger(TileMerger tileMerger) {
         movieMaker.setTileMerger(tileMerger);
    }

    @Override
    public void setFirst(AbstractFirstPlayer first) {
         this.first = first;
    }

    @Override
    public void setSecond(AbstractSecondPlayer second) {
         this.second = second;
    }

    @Override
    public boolean isReady() {
        return positionGenerator != null && movieMaker != null && finishChecker != null && size > 0;
    }
}
