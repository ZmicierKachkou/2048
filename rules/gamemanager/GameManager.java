package rules.gamemanager;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import exceptions.IncorrectMovieException;
import players.firstplayer.AbstractFirstPlayer;
import players.secondplayer.AbstractSecondPlayer;
import rules.finishchecker.FinishChecker;
import rules.moviemaker.MovieMaker;
import rules.positiongenerator.PositionGenerator;
import rules.tilesmerger.TilesMerger;

/**
 * Created on 18.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class GameManager implements Cloneable {
    private AbstractFirstPlayer first;
    private AbstractSecondPlayer second;
    private PositionGenerator positionGenerator;
    private MovieMaker movieMaker;
    private FinishChecker finishChecker;
    private int size;

    public PositionGenerator getPositionGenerator() {
        return positionGenerator;
    }

    public int getSize() {
        return size;
    }

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

    public Position generatePosition() {
        return positionGenerator.generatePosition(second, movieMaker, size);
    }

    public FirstPlayerMovie findFirstMovie(Position pos) {
        return first.movie(pos.clone(), movieMaker.getCorrectFirstPlayerMovies(pos), this.clone());
    }

    public SecondPlayerMovie findSecondMovie(Position pos) {
        return second.movie(pos.clone(), movieMaker.getCorrectSecondPlayerMovies(pos), movieMaker.getEmptyCells(pos));
    }

    public int makeMovie(Position pos, FirstPlayerMovie movie) throws IncorrectMovieException {
        return movieMaker.movie(pos, movie);
    }

    public void makeMovie(Position pos, SecondPlayerMovie movie) {
        movieMaker.movie(pos, movie);
    }

    public FinishChecker getFinishChecker() {
        return finishChecker;
    }

    public void setFinishChecker(FinishChecker finishChecker) {
        this.finishChecker = finishChecker;
    }

    public boolean isFinish(Position position) {
        return finishChecker.isFinish(position, movieMaker);
    }

    public TilesMerger getTilesMerger() {
        return movieMaker.getTilesMerger();
    }

    public void setTilesMerger(TilesMerger tilesMerger) {
         movieMaker.setTilesMerger(tilesMerger);
    }

    public void setFirst(AbstractFirstPlayer first) {
         this.first = first;
    }

    public void setSecond(AbstractSecondPlayer second) {
         this.second = second;
    }

    public boolean isReady() {
        return positionGenerator != null && movieMaker != null && finishChecker != null && size > 0;
    }

    @Override
    public GameManager clone() {
        GameManager copy = new GameManager();
        copy.setFinishChecker(this.getFinishChecker());
        copy.setMovieMaker(this.getMovieMaker());
        copy.setPositionGenerator(this.getPositionGenerator());
        copy.setTilesMerger(this.getTilesMerger());
        copy.setSize(this.getSize());
        return copy;
    }
}
