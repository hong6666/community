package com.lhh.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: community
 * @Date: 2019/12/30 15:34
 * @Author: lhh
 * @Description:
 */
@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("hello")
    public String hello(@RequestParam("name")String name, Model model)//
    {
        model.addAttribute("name",name);
        return "hello"+name;
    }


}
