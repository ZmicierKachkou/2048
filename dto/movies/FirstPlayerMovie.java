package dto.movies;

/**
 * Created on 29.09.2015.
 *
 * @author Źmicier Dzikański
 */
public enum FirstPlayerMovie {
    UP(0), RIGHT(1), DOWN(2), LEFT(3), UNUSUAL_MOVIE_1(4), UNUSUAL_MOVIE_2(5), UNUSUAL_MOVIE_3(6), UNUSUAL_MOVIE_4(7);

    private final int value;

    private FirstPlayerMovie(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public FirstPlayerMovie getMovie(Integer i) {
        FirstPlayerMovie[] allMovies = FirstPlayerMovie.values();
        return allMovies[i % allMovies.length];
    }

    public int getMovie(FirstPlayerMovie movie) {
        return movie.getValue();
    }
}
