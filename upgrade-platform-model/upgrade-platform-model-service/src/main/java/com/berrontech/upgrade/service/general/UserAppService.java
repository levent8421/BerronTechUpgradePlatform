package com.berrontech.upgrade.service.general;

import com.berrontech.upgrade.commons.entity.UserApp;
import com.berrontech.upgrade.service.basic.AbstractService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 13:50
 * Class Name: UserAppService
 * Author: Levent8421
 * Description:
 * User App Service Definition
 *
 * @author Levent8421
 */
public interface UserAppService extends AbstractService<UserApp> {
    /**
     * Bind user as role for app
     *
     * @param userId user id
     * @param appId  appId
     * @param role   role
     * @return user app binder
     */
    UserApp bind(Integer userId, Integer appId, int role);

    /**
     * Find binder by user and app
     *
     * @param userId user id
     * @param appId  appId
     * @return User App Binder
     */
    UserApp findByUserAndApp(Integer userId, Integer appId);

    /**
     * Find app binder by user
     *
     * @param userId user id
     * @param page   page
     * @param rows   rows
     * @return app list
     */
    PageInfo<UserApp> findByUser(Integer userId, int page, int rows);

    /**
     * 查询用户是否有APP的操作权限
     *
     * @param userId user id
     * @param appId  app id
     * @return has binder?
     */
    boolean hasBind(Integer userId, Integer appId);

    /**
     * 查询用户对app的角色
     *
     * @param userId user id
     * @param appId  app id
     * @return role or null
     */
    Integer getRole(Integer userId, Integer appId);

    /**
     * Find binder by appId
     *
     * @param appId appId
     * @return appS
     */
    List<UserApp> findByApp(Integer appId);

    /**
     * Find a binder by id fetch app
     *
     * @param id id
     * @return ua
     */
    UserApp requireWithApp(Integer id);
}
