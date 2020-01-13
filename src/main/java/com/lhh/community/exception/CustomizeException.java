package com.lhh.community.exception;

/**
 * @program: community
 * @Date: 2020/1/13 11:11
 * @Author: lhh
 * @Description:
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode)
    {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
