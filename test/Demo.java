package test;

import dto.Result;
import exceptions.IncorrectMovieException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import server.Server2048;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created on 28.10.2015.
 *
 * @author Źmicier Dzikański
 */

public class Demo {
    private final static int REPEATS = 101;

    @Autowired
    Server2048 server;

    public static void main(String[] args) throws IncorrectMovieException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");
        Demo demo = context.getBean(Demo.class);

        float points = 0;
        float max = 0;
        float wins = 0;
        LinkedList<Integer> pointsList = new LinkedList<Integer>();
        for(int i=0; i<REPEATS; i++) {
            Result r = demo.server.playGame();
            System.out.println("Game " + i +": " + r.getMovies() + " movies, " + r.getPoints() + " points");
            points += r.getPoints();
            if(max < r.getPoints()) {
                max = r.getPoints();
            }
            if(20000 < r.getPoints()) {
                wins++;
            }
            pointsList.add(r.getPoints());
            System.out.println(r.getPosition());
        }
        Collections.sort(pointsList);
        System.out.println("average: " + points/REPEATS +"\nbest: " + max + "\nmedium: " + pointsList.get((REPEATS+1)/2) + "\nwins: " + wins);

    }
}
