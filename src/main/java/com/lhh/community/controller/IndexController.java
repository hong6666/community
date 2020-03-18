package com.lhh.community.controller;

import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.entity.User;
import com.lhh.community.services.QuestionService;
import com.lhh.community.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "size",defaultValue = "5")Integer size)
    {
        PaginationDTO pagination = questionService.questionPage(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }

    /*@ResponseBody
    @GetMapping("/test")
    public List<User> test(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(15);
        List<User> users = userService.selectByIds(list);
        return users;
    }*/
}
