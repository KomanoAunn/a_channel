package pers.anuu.core.exception;

public class BusinessException extends RuntimeException {
    private String msg;

    public BusinessException(String message) {
        super(message);
        this.msg = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public String getMsg() {
        return this.msg;
    }
}
