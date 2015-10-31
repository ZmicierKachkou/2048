package exceptions;

/**
 * Created on 30.10.2015.
 *
 * @author Źmicier Dzikański
 */
public class MergeNotPossibleException extends Exception {
    public MergeNotPossibleException() {
        super();
    }

    public MergeNotPossibleException(String message) {
        super(message);
    }
}
