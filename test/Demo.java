package test;

import exceptions.IncorrectMovieException;
import players.firstplayer.*;
import players.firstplayer.genetic.impl.BasicPositionEstimator;
import players.secondplayer.AbstractSecondPlayer;
import players.secondplayer.QuasiRandomSecondPlayer;
import players.secondplayer.RandomSecondPlayer;
import rules.finishchecker.impl.InfinityGameFinishChecker;
import rules.gamemanager.GameManager;
import rules.gamemanager.impl.SimpleGameManager;
import rules.moviemaker.impl.SimpleMovieMaker2048;
import rules.positiongenerator.impl.RandomPositionGenerator;
import rules.tilesmerger.impl.SimpleTileMerger2048;
import server.Server2048;

/**
 * Created on 28.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class Demo {
    public static void main(String[] args) throws IncorrectMovieException {
        AbstractFirstPlayer first = new GeneticFirstPlayer(new BasicPositionEstimator());
        AbstractSecondPlayer second = new QuasiRandomSecondPlayer();
        GameManager gameManager = new SimpleGameManager();
        gameManager.setFinishChecker(new InfinityGameFinishChecker());
        gameManager.setMovieMaker(new SimpleMovieMaker2048());
        gameManager.setPositionGenerator(new RandomPositionGenerator());
        gameManager.setTileMerger(new SimpleTileMerger2048());
        gameManager.setSize(4);
        Server2048 server = new Server2048(first, second, gameManager);
        server.playGame();
    }
}
