package com.lhh.community.controller;

import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.entity.User;
import com.lhh.community.services.QuestionService;
import com.lhh.community.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @Date: 2020/1/7 16:50
 * @Author: lhh
 * @Description:
 */
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable("action") String action,
                          Model model,
                          @RequestParam(value = "page",defaultValue = "1")Integer page,
                          @RequestParam(value = "size",defaultValue = "5")Integer size)
    {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0 )
        {
            for(Cookie cookie : cookies)
            {
                if (cookie.getName().equals("token"))
                {
                    String token = cookie.getValue();
                    user = userService.findByToken(token);
                    if (user != null)
                    {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        if (user == null)
        {
            return "redirect:/";
        }

        if ("questions".equals(action))
        {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action))
        {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = questionService.questionPage(user.getId(),page,size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";

    }






}