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
import rules.tilesmerger.TileMerger;

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
    void makeMovie(Position position, FirstPlayerMovie movie) throws IncorrectMovieException;
    void makeMovie(Position position, SecondPlayerMovie movie);

    FinishChecker getFinishChecker();
    void setFinishChecker(FinishChecker finishChecker);
    boolean isFinish(Position position);

    TileMerger getTileMerger();
    void setTileMerger(TileMerger tileMerger);

    void setFirst(AbstractFirstPlayer first);
    void setSecond(AbstractSecondPlayer second);

    void setSize(int i);
    int getSize();

    boolean isReady();
}
