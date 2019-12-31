package life.majiang.community.exception;

public class CustomizerException extends RuntimeException {
    private Integer code;

    public CustomizerException(String s) {
        super(s);
    }

    public CustomizerException(ICustomizerErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Integer getCode() {
        return code;
    }
}
