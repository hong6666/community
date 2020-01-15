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
 * @Description:
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
            if(e instanceof CustomizeException)
            {
                resultDTO = ResultDTO.errorOf((CustomizeException)e);
            }else
            {
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
