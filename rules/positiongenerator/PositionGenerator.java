package rules.positiongenerator;

import dto.Position;
import players.secondplayer.AbstractSecondPlayer;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public interface PositionGenerator {
    Position generatePosition(AbstractSecondPlayer player);
}
