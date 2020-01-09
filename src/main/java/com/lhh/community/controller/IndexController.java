package com.lhh.community.controller;

import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "size",defaultValue = "5")Integer size)
    {
        PaginationDTO pagination = questionService.questionPage(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }


}
