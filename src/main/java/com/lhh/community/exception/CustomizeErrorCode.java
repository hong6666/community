package com.lhh.community.exception;

/**
 * @program: community
 * @Date: 2020/1/13 11:02
 * @Author: lhh
 * @Description:
 */
public enum  CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND("你找到问题不在了，要不要换个试试？");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message)
    {
        this.message = message;
    }
}
