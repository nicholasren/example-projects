package example.exception;

/**
 * Created by IntelliJ IDEA.
 * User: nicholasren
 * Date: 11-8-2
 * Time: 下午9:00
 */
public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String s) {
        super(s);
    }
}
