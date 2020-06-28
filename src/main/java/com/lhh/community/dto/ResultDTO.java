package com.lhh.community.dto;

import com.lhh.community.exception.CustomizeErrorCode;
import com.lhh.community.exception.CustomizeException;
import lombok.Data;

/**
 * @program: community
 * @Date: 2020/1/14 14:36
 * @Author: lhh
 * @Description: 返回结果封装类
 */
@Data
public class ResultDTO<T> {

    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     * 返回的消息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 返回错误的封装类
     * @param code 状态码
     * @param message 消息
     * @return 结果封装类
     */
    public static ResultDTO errorOf(Integer code,String message)
    {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    /**
     * 通过枚举类返回错误的封装类
     * @param errorCode 自定义的错误枚举类
     * @return ResultDTO
     */
    public static ResultDTO errorOf(CustomizeErrorCode errorCode)
    {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    /**
     *
     * @param e 自定义错误
     * @return ResultDTO
     */
    public static ResultDTO errorOf(CustomizeException e)
    {
        return errorOf(e.getCode(),e.getMessage());
    }

    public static ResultDTO okOf()
    {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static  <T> ResultDTO okOf(T t)
    {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }
}
