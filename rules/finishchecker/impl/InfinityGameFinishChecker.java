package rules.finishchecker.impl;

import dto.Position;
import rules.finishchecker.FinishChecker;
import rules.moviemaker.MovieMaker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class InfinityGameFinishChecker implements FinishChecker {
    @Override
    public boolean isFinish(Position pos, MovieMaker movieMaker) {
        return movieMaker.getCorrectFirstPlayerMovies(pos).size() == 0;
    }
}
