package server;

import dto.Position;
import dto.Result;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import players.firstplayer.AbstractFirstPlayer;
import players.secondplayer.AbstractSecondPlayer;
import rules.finishchecker.FinishChecker;
import rules.moviemaker.MovieMaker;
import rules.positiongenerator.PositionGenerator;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class Server2048 {
    private AbstractFirstPlayer first;
    private AbstractSecondPlayer second;
    private PositionGenerator positionGenerator;
    private MovieMaker movieMaker;
    private FinishChecker finishChecker;

    public Server2048(AbstractFirstPlayer first, AbstractSecondPlayer second, PositionGenerator newPositionGenerator,
                      MovieMaker movieMaker, FinishChecker finishChecker) {
        this.first = first;
        this.second = second;
        this.positionGenerator = newPositionGenerator;
        this.movieMaker = movieMaker;
        this.finishChecker = finishChecker;
    }

    public AbstractFirstPlayer getFirst() {
        return first;
    }

    public void setFirst(AbstractFirstPlayer first) {
        this.first = first;
    }

    public AbstractSecondPlayer getSecond() {
        return second;
    }

    public void setSecond(AbstractSecondPlayer second) {
        this.second = second;
    }

    public PositionGenerator getPositionGenerator() {
        return positionGenerator;
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

    public FinishChecker getFinishChecker() {
        return finishChecker;
    }

    public void setFinishChecker(FinishChecker finishChecker) {
        this.finishChecker = finishChecker;
    }

    public Result playGame() {
        Position pos = positionGenerator.generatePosition(second);
        Integer movie = 0;
        Integer points = 0;
        while(!finishChecker.isFinish(pos)) {
            FirstPlayerMovie movie1;
            SecondPlayerMovie movie2;
            movie1 = first.movie(pos, movieMaker.getCorrectFirstPlayerMovies(pos));
            pos = movieMaker.movie(pos, movie1);
            movie++;
            movie2 = second.movie(pos, movieMaker.getCorrectSecondPlayerMovies(pos));
            pos = movieMaker.movie(pos, movie2);
        }
        return new Result(pos, movie, points);
    }
}
