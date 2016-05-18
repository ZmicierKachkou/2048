package test;

import dto.Result;
import exceptions.IncorrectMovieException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import players.firstplayer.*;
import players.firstplayer.genetic.impl.BasicPositionEstimator;
import players.secondplayer.AbstractSecondPlayer;
import players.secondplayer.RandomSecondPlayer;
import rules.finishchecker.impl.InfinityGameFinishChecker;
import rules.gamemanager.GameManager;
import rules.moviemaker.impl.SimpleMovieMaker2048;
import rules.positiongenerator.impl.RandomPositionGenerator;
import rules.tilesmerger.impl.SimpleTilesMerger2048;
import server.Server2048;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 28.10.2015.
 *
 * @author Źmicier Dzikański
 */

public class Demo {
    @Autowired
    Server2048 server;

    public static void main(String[] args) throws IncorrectMovieException {
        /*AbstractFirstPlayer first = new GeneticFirstPlayer(new BasicPositionEstimator());
        AbstractSecondPlayer second = new RandomSecondPlayer(); //QuasiRandomSecondPlayer();
        GameManager gameManager = new GameManager();
        gameManager.setFinishChecker(new InfinityGameFinishChecker());
        gameManager.setMovieMaker(new SimpleMovieMaker2048());
        gameManager.setPositionGenerator(new RandomPositionGenerator());
        gameManager.setTilesMerger(new SimpleTilesMerger2048());
        gameManager.setSize(4);*/
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");
        Demo demo = context.getBean(Demo.class);

        float points = 0;
        float max = 0;
        float wins = 0;
        for(int i=0; i<1000; i++) {
            Result r = demo.server.playGame();
            System.out.println("Game " + i +": " + r.getMovies() + " movies, " + r.getPoints() + " points");
            points += r.getPoints();
            if(max < r.getPoints()) {
                max = r.getPoints();
            }
            if(20000 < r.getPoints()) {
                wins++;
            }
            System.out.println(r.getPosition());
        }
        System.out.println("average: " + points/1000. +"\nbest: " + max + "\nwins: " + wins);
    }
}
