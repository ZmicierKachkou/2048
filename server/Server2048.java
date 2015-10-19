package server;

import dto.Position;
import dto.Result;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import players.firstplayer.AbstractFirstPlayer;
import players.secondplayer.AbstractSecondPlayer;
import rules.finishchecker.FinishChecker;
import rules.gamemanager.GameManager;
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
    private GameManager gameManager;

    public Server2048(AbstractFirstPlayer first, AbstractSecondPlayer second, GameManager gameManager) {
        this.first = first;
        this.second = second;
        this.gameManager = gameManager;
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

    public Result playGame() {
        gameManager.setFirst(first);
        gameManager.setSecond(second);
        Position pos = gameManager.generatePosition();
        Integer movie = 0;
        Integer points = 0;
        while(!gameManager.isFinish(pos)) {
            FirstPlayerMovie movie1;
            SecondPlayerMovie movie2;
            movie1 = gameManager.findFirstMovie(pos);
            pos = gameManager.makeMovie(pos, movie1);
            movie++;
            movie2 = gameManager.findSecondMovie(pos);
            pos = gameManager.makeMovie(pos, movie2);
        }
        return new Result(pos, movie, points);
    }
}
