package com.lhh.community.dao;

import com.lhh.community.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NotificationMapper
 *
 * @author 李弘昊
 * @since 2020/6/17
 */
@Repository
@Mapper
public interface NotificationMapper {

    int countByUserId(@Param("userId")Integer userId);

    List<Notification> selectPageByUserId(@Param("userId")Integer userId, @Param("offset")Integer offset, @Param("size")Integer size);

    Long selectUnReadCount(@Param("userId")Integer userId);

    int insert(Notification notification);
}
