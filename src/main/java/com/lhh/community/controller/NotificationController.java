package com.lhh.community.controller;

import com.lhh.community.dto.NotificationDTO;
import com.lhh.community.entity.User;
import com.lhh.community.enums.NotificationTypeEnum;
import com.lhh.community.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * NotificationController
 * 通知控制类
 * @author 李弘昊
 * @since 2020/6/16
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 通知
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        //通过session得到角色信息
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        //查询该用户的通知信息
        NotificationDTO notificationDTO = notificationService.read(id,user);
        //如果有回复了评论或者问题，跳转到该问题，没有跳回首页
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }
}
