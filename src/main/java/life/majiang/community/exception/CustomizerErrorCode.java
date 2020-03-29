package life.majiang.community.exception;

public enum CustomizerErrorCode implements ICustomizerErrorCode {

    QUESTION_NOT_FOUND(2001, "问题找不到或已删除"),
    TARGET_PARAM_NOT_FOUND(2002, "未选择任何问题或评论进行回复"),
    NO_LOGIN(2003, "当前操作需要登陆，请先登陆"),
    SYS_ERROR(2004, "服务器出错"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了"),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读别人的信息呢"),
    NOTIFICATION_NOT_FAIL(2009, "消息飞走了"),
    ;

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
