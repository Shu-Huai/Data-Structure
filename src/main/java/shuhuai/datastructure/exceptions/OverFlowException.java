package shuhuai.datastructure.exceptions;

public class OverFlowException extends Exception {
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