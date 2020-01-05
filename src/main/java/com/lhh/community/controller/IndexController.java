package com.lhh.community.controller;

import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.dto.QuestionDTO;
import com.lhh.community.entity.User;
import com.lhh.community.services.QuestionService;
import com.lhh.community.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: community
 * @Date: 2019/12/30 15:34
 * @Author: lhh
 * @Description:
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "size",defaultValue = "5")Integer size)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
        {
            for(Cookie cookie : cookies)
            {
                if(cookie.getName().equals("token"))
                {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    if(user != null)
                    {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        /*List<QuestionDTO> questionDTOList = questionService.questionDTOList();
        model.addAttribute("questions",questionDTOList);*/
        PaginationDTO pagination = questionService.questionPage(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }


}
