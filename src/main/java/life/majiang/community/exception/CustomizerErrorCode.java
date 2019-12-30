package life.majiang.community.exception;

public enum CustomizerErrorCode implements ICustomizerErrorCode {

    QUESTION_NOT_FOUND("问题找不到或已删除");

    CustomizerErrorCode(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
