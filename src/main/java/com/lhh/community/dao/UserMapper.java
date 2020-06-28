package com.lhh.community.dao;

import com.lhh.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    /**
     *
     * @param id 用户id
     * @return 用户实体类 User
     */
    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findByToken(String token);

    User findByAccountId(String accountId);

    List<User> selectByIds(List list);
}