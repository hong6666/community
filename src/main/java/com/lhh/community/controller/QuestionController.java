package com.lhh.community.controller;

import com.lhh.community.dto.CommentDTO;
import com.lhh.community.dto.QuestionDTO;
import com.lhh.community.enums.CommentTypeEnum;
import com.lhh.community.services.CommentService;
import com.lhh.community.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CommentService commentService;

    /**
     * 问题详情页
     * @param id 问题id
     * @param model Model
     * @return question.html
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id,
                           Model model)
    {
        //问题的相关信息
        QuestionDTO questionDTO = questionService.selectByPrimaryKey(id);
        //问题的评论
        List<CommentDTO> comments = commentService.selectByTargetId(id, CommentTypeEnum.QUESTION.getType());
        //相关问题
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        //累加阅读数
        questionService.incView(id);

        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }


}
