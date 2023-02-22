package shuhuai.datastructure.exceptions;

public class UnderFlowException extends BaseException {
    public UnderFlowException() {
        super();
    }

    public UnderFlowException(String message) {
        super(message);
    }

    public UnderFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnderFlowException(Throwable cause) {
        super(cause);
    }
}