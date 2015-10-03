package rules.finishchecker.impl;

import dto.Position;
import rules.finishchecker.FinishChecker;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class InfinityGameFinishChecker implements FinishChecker {
    @Override
    public boolean isFinish(Position position) {
        return false;
    }
}
