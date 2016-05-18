package rules.finishchecker;

import dto.Position;
import rules.moviemaker.MovieMaker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 *
 * This manager checkes if the game was finished
 */
public interface FinishChecker {
    /**
     * Checks if the game was finished
     * @param pos is a current position
     * @param movieMaker is an instance, which is responsible for correct movies
     * @return true if game was finished and false otherwise
     */
    boolean isFinish(Position pos, MovieMaker movieMaker);
}
