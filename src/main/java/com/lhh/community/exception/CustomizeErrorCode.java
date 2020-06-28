package com.lhh.community.exception;

/**
 * @program: community
 * @Date: 2020/1/13 11:02
 * @Author: lhh
 * @Description: 自定义错误枚举类实现了自定义错误接口
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode{

    /**
     * 2001，问题找不到
     */
    QUESTION_NOT_FOUND(2001, "你找到问题不在了，要不要换个试试？"),
    /**
     * 2002，未选中任何问题或评论进行回复
     */
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    /**
     * 2003，没有获得用户信息，需要登录
     */
    NO_LOGIN(2003, "当前操作需要登录，请登陆后重试"),
    /**
     * 2004，系统错误
     */
    SYS_ERROR(2004, "服务冒烟了，要不然你稍后再试试！！！"),
    /**
     * 2005，评论类型错误或不存在
     */
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    /**
     * 2006，回复的评论不存在
     */
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了，要不要换个试试？"),
    /**
     * 2007，输入内容为空
     */
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    /**
     * 2008，读取通知失败
     */
    READ_NOTIFICATION_FAIL(2008,"兄弟你这是读别人的信息呢？"),
    /**
     * 2009，通知找不到
     */
    NOTIFICATION_NOT_FOUND(2009,"消息莫非是不翼而飞了？");



    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    /**
     * 自定义错误消息
     */
    private String message;

    /**
     * 自定义错误状态码
     */
    private Integer code;

    CustomizeErrorCode(Integer code ,String message)
    {
        this.code = code;
        this.message = message;
    }
}
