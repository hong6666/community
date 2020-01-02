package com.lhh.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: community
 * @Date: 2020/1/2 16:03
 * @Author: lhh
 * @Description:
 */
@Controller
public class PublicController {

    @GetMapping("/publish")
    public String publicsh()
    {
        return "publish";
    }

}
