package shuhuai.datastructure.exceptions;

@SuppressWarnings({"unused"})
public class OverFlowException extends BaseException {
    public OverFlowException() {
        super();
    }

    public OverFlowException(String message) {
        super(message);
    }

    public OverFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverFlowException(Throwable cause) {
        super(cause);
    }
}