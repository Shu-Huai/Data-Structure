package shuhuai.datastructure.exceptions;

@SuppressWarnings({"unused"})
public class ExistException extends BaseException{
    public ExistException() {
        super();
    }

    public ExistException(String message) {
        super(message);
    }

    public ExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistException(Throwable cause) {
        super(cause);
    }
}