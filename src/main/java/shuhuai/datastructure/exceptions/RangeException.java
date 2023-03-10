package shuhuai.datastructure.exceptions;

@SuppressWarnings({"unused"})
public class RangeException extends BaseException {
    public RangeException() {
        super();
    }

    public RangeException(String message) {
        super(message);
    }

    public RangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RangeException(Throwable cause) {
        super(cause);
    }
}