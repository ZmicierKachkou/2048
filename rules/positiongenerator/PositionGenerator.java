package rules.positiongenerator;

import dto.Coords;
import dto.Position;
import dto.tiles.Tile;
import players.secondplayer.AbstractSecondPlayer;
import rules.gamemanager.GameManager;
import rules.moviemaker.MovieMaker;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 * this manager is used to generate first position
 */
public interface PositionGenerator {
    /**
     * Generates first position
     * @param player is a second player. It is used because generation of the initial position may be considered
     *               as a movie of the second player
     * @param gameManager is an instance, which is responsible for correct movies
     * @param size is a size of position
     * @return new position
     */
    Position generatePosition(AbstractSecondPlayer player, GameManager gameManager, int size);
}
