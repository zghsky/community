package life.majiang.community.exception;

public class CustomizerException extends RuntimeException {

    public CustomizerException(String s) {
        super(s);
    }

    public CustomizerException(ICustomizerErrorCode errorCode) {
        super(errorCode.getMessage());
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
