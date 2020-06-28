package com.lhh.community.exception;

/**
 * @program: community
 * @Date: 2020/1/13 11:03
 * @Author: lhh
 * @Description: 自定义错误接口
 */
public interface ICustomizeErrorCode {

    /**
     * 得到错误消息
     * @return String
     */
    String getMessage();

    /**
     * 得到错误状态码
     * @return Integer
     */
    Integer getCode();
}
