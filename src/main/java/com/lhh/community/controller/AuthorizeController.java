package com.lhh.community.controller;

import com.lhh.community.dto.AccessTokenDTO;
import com.lhh.community.dto.GithubUser;
import com.lhh.community.entity.User;
import com.lhh.community.provider.GithubProvider;
import com.lhh.community.services.UserService;
import com.lhh.community.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @program: community
 * @Date: 2019/12/30 22:22
 * @Author: lhh
 * @Description:
 */
@Controller
public class AuthorizeController {

    private Logger logger = LogUtil.logger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;



    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletResponse response)
    {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null&&githubUser.getId() != null)
        {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getLogin());
            user.setAccountid(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);

            /*//登录成功，写cookid和session
            request.getSession().setAttribute("user",githubUser);*/
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else
        {
            //登录失败，重新登录
            return "redirect:/";
        }
    }

    /**
     * 登出，退出
     * @param request request
     * @param response response
     * @return 跳回登录页面
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }


}
