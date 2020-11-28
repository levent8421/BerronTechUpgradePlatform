package com.berrontech.upgrade.repository.mapper;

import com.berrontech.upgrade.commons.entity.UserApp;
import com.berrontech.upgrade.repository.mybatis.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 13:47
 * Class Name: UserAppMapper
 * Author: Levent8421
 * Description:
 * User App Repository component
 *
 * @author Levent8421
 */
@Repository
public interface UserAppMapper extends AbstractMapper<UserApp> {
    /**
     * Select binder by userId and AppId
     *
     * @param userId user Id
     * @param appId  app id
     * @return user app binder
     */
    UserApp selectByUserAndApp(@Param("userId") Integer userId, @Param("appId") Integer appId);

    /**
     * Select binder by user with apps
     *
     * @param userId user id
     * @return list
     */
    List<UserApp> selectByUserFetchApp(@Param("userId") Integer userId);

    /**
     * Select role by user and app
     *
     * @param userId user id
     * @param appId  app id
     * @return role
     */
    Integer selectRoleByUserAndApp(@Param("userId") Integer userId,
                                   @Param("appId") Integer appId);
}
