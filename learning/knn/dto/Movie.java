package learning.knn.dto;

/**
 * Created on 22.05.2016.
 *
 * @author Źmicier Dzikański
 */
public class Movie {
    int[] position;
    int movie;

    public Movie() {
    }

    public Movie(int[] position, int movie) {
        this.position = position;
        this.movie = movie;
    }

    public Movie(String s) {
        String[] ints = s.split(" ");
        position = new int[ints.length-1];
        for(int i=0; i<ints.length-1; i++) {
            position[i] = Integer.parseInt(ints[i]);
        }
        movie = Integer.parseInt(ints[ints.length-1]);
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }
}
