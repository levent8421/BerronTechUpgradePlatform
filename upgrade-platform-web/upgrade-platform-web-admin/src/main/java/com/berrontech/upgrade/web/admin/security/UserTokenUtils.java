package com.berrontech.upgrade.web.admin.security;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.service.general.UserService;
import com.berrontech.upgrade.web.commons.security.TokenDataHolder;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 13:19
 * Class Name: UserTokenUtils
 * Author: Levent8421
 * Description:
 * User Token Utils
 *
 * @author Levent8421
 */

public class UserTokenUtils {
    private UserTokenUtils() {
    }

    public static Integer getUserId(TokenDataHolder holder) {
        return holder.get(UserToken.USER_ID_NAME, Integer.class);
    }

    public static String getUsername(TokenDataHolder holder) {
        return holder.get(UserToken.USER_NAME_NAME, String.class);
    }

    public static String getLoginName(TokenDataHolder holder) {
        return holder.get(UserToken.USER_LOGIN_NAME_NAME, String.class);
    }

    public static User getCurrentUser(TokenDataHolder holder, UserService userService) {
        return userService.require(getUserId(holder));
    }
}
