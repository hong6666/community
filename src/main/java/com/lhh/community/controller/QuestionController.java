package com.lhh.community.controller;

import com.lhh.community.dto.QuestionDTO;
import com.lhh.community.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: community
 * @Date: 2020/1/9 11:39
 * @Author: lhh
 * @Description:
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model)
    {
        QuestionDTO questionDTO = questionService.selectByPrimaryKey(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }


}
