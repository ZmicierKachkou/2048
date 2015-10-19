package rules.gamemanager;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import dto.movies.SecondPlayerMovie;
import players.firstplayer.AbstractFirstPlayer;
import players.secondplayer.AbstractSecondPlayer;
import rules.finishchecker.FinishChecker;
import rules.moviemaker.MovieMaker;
import rules.positiongenerator.PositionGenerator;

/**
 * Created on 18.10.2015.
 *
 * @author Źmicier Dzikański
 */
public interface GameManager {
    PositionGenerator getPositionGenerator();
    void setPositionGenerator(PositionGenerator positionGenerator);
    Position generatePosition();

    MovieMaker getMovieMaker();
    void setMovieMaker(MovieMaker movieMaker);
    FirstPlayerMovie findFirstMovie(Position pos);
    SecondPlayerMovie findSecondMovie(Position pos);
    Position makeMovie(Position position, FirstPlayerMovie movie);
    Position makeMovie(Position position, SecondPlayerMovie movie);

    FinishChecker getFinishChecker();
    void setFinishChecker(FinishChecker finishChecker);
    boolean isFinish(Position position);

    void setFirst(AbstractFirstPlayer first);
    void setSecond(AbstractSecondPlayer second);
}
