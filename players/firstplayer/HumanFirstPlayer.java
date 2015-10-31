package players.firstplayer;

import dto.Position;
import dto.movies.FirstPlayerMovie;

import java.util.List;
import java.util.Scanner;

/**
 * Created on 28.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class HumanFirstPlayer extends AbstractFirstPlayer {

    @Override
    public FirstPlayerMovie movie(Position position, List<FirstPlayerMovie> movies) {
        System.out.println("Your movie?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            if(!sc.hasNextInt()) {
                sc.next();
                continue;
            }
            Integer mov = sc.nextInt();
            if(mov >= 0 && mov < 4) {
                FirstPlayerMovie movie = FirstPlayerMovie.getMovie(mov);
                if(movies.contains(movie)) return movie;
            }
        }
    }
}
