package dto;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public class Result {
    private Position position;
    private Integer movies;
    private Integer points;

    public Result() {
    }

    public Result(Position position, Integer movies, Integer points) {
        this.position = position;
        this.movies = movies;
        this.points = points;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getMovies() {
        return movies;
    }

    public void setMovies(Integer movies) {
        this.movies = movies;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
