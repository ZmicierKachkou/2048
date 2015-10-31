package exceptions;

/**
 * Created on 28.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class IncorrectMovieException extends Exception {
    public IncorrectMovieException() {
        super();
    }

    public IncorrectMovieException(String message) {
        super(message);
    }
}
