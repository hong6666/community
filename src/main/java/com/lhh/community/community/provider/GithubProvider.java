package com.lhh.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.lhh.community.community.dto.AccessTokenDTO;
import com.lhh.community.community.dto.GihubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: community
 * @Date: 2019/12/30 22:40
 * @Author: lhh
 * @Description:
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO)
    {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            /*System.out.println(str);*/
            String token = str.split("&")[0].split("=")[1];
            /*System.out.println(token);*/
            return token;
        } catch (IOException e) {
        }
        return null;
    }

    public GihubUser getUser(String accessToken)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            System.out.println(str);
            GihubUser gihubUser = JSON.parseObject(str,GihubUser.class);
            return gihubUser;
        } catch (IOException e) {
        }
        return null;
    }
}
