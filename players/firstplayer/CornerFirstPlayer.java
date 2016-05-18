package players.firstplayer;

import dto.Position;
import dto.movies.FirstPlayerMovie;
import rules.gamemanager.GameManager;

import java.util.List;

/**
 * Created on 31.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class CornerFirstPlayer extends AbstractFirstPlayer{

    @Override
    public FirstPlayerMovie movie(Position position, List<FirstPlayerMovie> movies, final GameManager manager) {
        if(movies.get(0) == FirstPlayerMovie.DOWN && movies.size() > 1) return movies.get(1);
        return movies.get(0);
    }
}
