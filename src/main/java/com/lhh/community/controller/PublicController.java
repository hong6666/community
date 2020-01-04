package com.lhh.community.controller;

import com.lhh.community.entity.Question;
import com.lhh.community.entity.User;
import com.lhh.community.services.QuestionService;
import com.lhh.community.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @Date: 2020/1/2 16:03
 * @Author: lhh
 * @Description:
 */
@Controller
public class PublicController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;


    @GetMapping("/publish")
    public String publicsh()
    {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title")String title,
                            @RequestParam("description")String description,
                            @RequestParam("tag")String tag,
                            HttpServletRequest request,
                            Model model)
    {
       model.addAttribute("title",title);
       model.addAttribute("description",description);
       model.addAttribute("tag",tag);

       if(title == null || title == "")
       {
           model.addAttribute("error","标题不能为空");
           return "publish";
       }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
        {
            for(Cookie cookie : cookies)
            {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userService.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }


        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.insert(question);
        return "redirect:/";


    }
}
