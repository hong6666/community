package com.lhh.community.controller;

import com.lhh.community.dto.PaginationDTO;
import com.lhh.community.entity.User;
import com.lhh.community.services.NotificationService;
import com.lhh.community.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @Date: 2020/1/7 16:50
 * @Author: lhh
 * @Description: profile个人资料，
 */
@Controller
@AllArgsConstructor
public class ProfileController {

    private QuestionService questionService;

    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable("action") String action,
                          Model model,
                          @RequestParam(value = "page",defaultValue = "1")Integer page,
                          @RequestParam(value = "size",defaultValue = "5")Integer size)
    {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
        {
            return "redirect:/";
        }

        if ("questions".equals(action))
        {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.questionPage(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);
        }else if("replies".equals(action))
        {
            PaginationDTO paginationDTO = notificationService.list(user.getId(),page,size);
            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("section","replies");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("unreadCount",unreadCount);
            model.addAttribute("sectionName","最新回复");
        }

//        PaginationDTO paginationDTO = questionService.questionPage(user.getId(),page,size);
//        model.addAttribute("pagination",paginationDTO);
        return "profile";

    }






}
