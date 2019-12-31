package life.majiang.community.exception;

public enum CustomizerErrorCode implements ICustomizerErrorCode {

    QUESTION_NOT_FOUND(2001, "问题找不到或已删除"),
    TARGET_PARAM_NOT_FOUND(2002, "未选择任何问题或评论进行回复"),
    NO_LOGIN(2003, "当前操作需要登陆，请先登陆"),
    SYS_ERROR(2004, "服务器出错"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了");

    CustomizerErrorCode(String message) {
        this.message = message;
    }

    private Integer code;
    private String message;

    CustomizerErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
