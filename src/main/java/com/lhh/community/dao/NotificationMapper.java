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

    /**
     * 通过userId查询通知数量
     * @param userId userId
     * @return int
     */
    int countByUserId(@Param("userId")Long userId);

    /**
     * 通知分页
     * @param userId userId
     * @param offset offset
     * @param size size
     * @return 返回分页信息
     */
    List<Notification> selectPageByUserId(@Param("userId")Long userId, @Param("offset")Integer offset, @Param("size")Integer size);

    /**
     * 通过用户id和状态查询未读的数量
     * @param userId userId
     * @param status NotificationStatusEnum的status
     * @return Long
     */
    Long selectUnReadCount(@Param("userId")Long userId,@Param("status")Integer status);

    /**
     * 通知插入
     * @param notification 通知
     * @return 返回插入条数
     */
    int insert(Notification notification);

    /**
     * 更新
     * @param notification notification
     * @return int
     */
    int updateById(Notification notification);

    /**
     * 通过id查通知
     * @param Id
     * @return Notification
     */
    Notification selectById(Long Id);
}
