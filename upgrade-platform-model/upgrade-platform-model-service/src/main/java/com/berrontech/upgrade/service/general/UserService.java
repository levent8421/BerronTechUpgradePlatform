package com.berrontech.upgrade.service.general;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.service.basic.AbstractService;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 14:48
 * Class Name: UserService
 * Author: Levent8421
 * Description:
 * user Service definition
 *
 * @author Levent8421
 */
public interface UserService extends AbstractService<User> {
    /**
     * User Login
     *
     * @param loginName login name
     * @param password  password
     * @return User
     */
    User login(String loginName, String password);

    /**
     * Find user by login name
     *
     * @param loginName login name
     * @return user
     */
    User requireByLoginName(String loginName);
}
