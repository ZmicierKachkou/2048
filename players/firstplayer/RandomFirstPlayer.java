package players.firstplayer;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import rules.gamemanager.GameManager;

import java.util.List;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class RandomFirstPlayer extends AbstractFirstPlayer{

    @Override
    public FirstPlayerMovie movie(Position position, List<FirstPlayerMovie> movies, final GameManager manager) {
        return movies.get((int)Math.floor(Math.random() * movies.size()));
    }
}
