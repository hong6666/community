package com.lhh.community.advice;

import com.lhh.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: community
 * @Date: 2020/1/13 10:57
 * @Author: lhh
 * @Description:
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model)
    {
        if (e instanceof CustomizeException)
        {
            model.addAttribute("message",e.getMessage());
        }else
        {
            model.addAttribute("message","服务器冒烟了，要不然你稍后再试试！！！");
        }
        return new ModelAndView("error");
    }
}
