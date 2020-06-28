package com.lhh.community.advice;

import com.alibaba.fastjson.JSON;
import com.lhh.community.dto.ResultDTO;
import com.lhh.community.exception.CustomizeErrorCode;
import com.lhh.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: community
 * @Date: 2020/1/13 10:57
 * @Author: lhh
 * @Description: 有时候我们遇到某些校验或者问题时，需要直接结束掉当前的请求，
 * 这时便可以通过抛出自定义异常来结束，如果你项目中使用了SpringMVC比较新的版本的话有控制器增强，
 * 可以通过@ControllerAdvice注解写一个控制器增强类来拦截自定义的异常并响应给前端相应的信息。
 *
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request,
                        HttpServletResponse response)
    {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType))
        {
            ResultDTO resultDTO;
            //如果异常是自定义异常
            if(e instanceof CustomizeException)
            {
                resultDTO = ResultDTO.errorOf((CustomizeException)e);
            } else
            {
                //不是自定义异常就抛出系统异常
                resultDTO = ResultDTO.errorOf((CustomizeErrorCode.SYS_ERROR));
            }
            try
            {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
            }catch (IOException ioe){}
            return null;
        }
        else
        {
            //错误页面跳转
            if(e instanceof CustomizeException)
            {
                model.addAttribute("message",e.getMessage());
            }else
            {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
