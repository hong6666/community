package com.lhh.community.controller;

import com.lhh.community.dao.UserMapper;
import com.lhh.community.dto.AccessTokenDTO;
import com.lhh.community.dto.GithubUser;
import com.lhh.community.dto.User;
import com.lhh.community.provider.GithubProvider;
import com.lhh.community.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @program: community
 * @Date: 2019/12/30 22:22
 * @Author: lhh
 * @Description:
 */
@Controller
public class AuthorizeController {

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

    @ResponseBody
    @GetMapping("/inserttest")
    public void test(User user)
    {
        int i = userService.insert(user);
        if (i == -1)
        {
            System.out.println("更新失败");
        }else if (i ==0)
        {
            System.out.println("没有更新");
        }
    }


    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletRequest request)
    {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser);
        if(githubUser != null)
        {
            User user = new User();
            /*System.out.println("myuser.getGmtCreate:"+user.getGmtcreate());*/
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getLogin());
            user.setAccountid(String.valueOf(githubUser.getId()));
            user.setGmtcreate(System.currentTimeMillis());
            user.setGmtmodified(user.getGmtcreate());
            userService.insert(user);

            //登录成功，写cookid和session
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else
        {
            //登录失败，重新登录
            return "redirect:/";
        }

    }

}
