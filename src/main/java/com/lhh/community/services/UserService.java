package com.lhh.community.services;

import com.lhh.community.entity.User;

import java.util.List;

/**
 * @program: community
 * @Date: 2020/1/2 10:27
 * @Author: lhh
 * @Description:
 */

public interface UserService {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKey(User record);

    List<User> findByToken(String token);

    User findByAccountId(String accountId);

    List<User> selectByIds(List list);

    void createOrUpdate(User user);
}
