package com.lhh.community.services;

import com.lhh.community.entity.User;

/**
 * @program: community
 * @Date: 2020/1/2 10:27
 * @Author: lhh
 * @Description:
 */

public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);

    User findByToken(String token);
}
