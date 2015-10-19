package rules.finishchecker;

import dto.Position;
import rules.moviemaker.MovieMaker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public interface FinishChecker {
    boolean isFinish(Position pos, MovieMaker movieMaker);
}
