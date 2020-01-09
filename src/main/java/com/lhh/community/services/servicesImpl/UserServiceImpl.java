package com.lhh.community.services.servicesImpl;

import com.lhh.community.dao.UserMapper;
import com.lhh.community.entity.User;
import com.lhh.community.services.UserService;
import com.lhh.community.utils.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: community
 * @Date: 2020/1/2 10:39
 * @Author: lhh
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LogUtil.logger(this.getClass());

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User record) {
        try {
            if(record != null)
            {
                int result = userMapper.insert(record);
                logger.info("插入结果：" + result);
                return result;
            }else
            {
                logger.info("插入参数为空！！");
                return -1;
            }
        } catch (Exception e) {
            logger.info("插入异常");
            return -1;
        }

    }

    @Override
    public int insertSelective(User record) {
        try {
            if(record != null)
            {
                int result = userMapper.insert(record);
                return result;
            }else
            {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        if (record != null)
        {
            try {
                return userMapper.updateByPrimaryKey(record);
            } catch (Exception e) {
                return -1;
            }
        }else
        {
            return -1;
        }
    }

    @Override
    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }

    @Override
    public User findByAccountId(String accountId) {
        return userMapper.findByAccountId(accountId);
    }

    @Override
    public void createOrUpdate(User user) {
        User dbuser = userMapper.findByAccountId(user.getAccountid());
        if (dbuser == null)
        {
            //插入
            user.setGmtcreate(System.currentTimeMillis());
            user.setGmtmodified(user.getGmtcreate());
            userMapper.insert(user);
            logger.info("新用户账号插入成功");
        }else
        {
            //更新
            dbuser.setGmtmodified(System.currentTimeMillis());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.updateByPrimaryKey(dbuser);
            logger.info("用户账号更新成功,token="+dbuser.getToken());
        }
    }

}
