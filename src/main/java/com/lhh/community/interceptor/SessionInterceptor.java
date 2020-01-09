package com.lhh.community.interceptor;

import com.lhh.community.entity.User;
import com.lhh.community.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: community
 * @Date: 2020/1/9 11:07
 * @Author: lhh
 * @Description:
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;


    @Override
    //该方法在每个请求处理之前进行调用
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)throws Exception
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
        {
            for(Cookie cookie : cookies)
            {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception
    {

    }

}
