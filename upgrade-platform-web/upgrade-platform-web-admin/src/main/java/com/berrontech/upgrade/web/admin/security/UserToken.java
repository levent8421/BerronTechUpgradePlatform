package com.berrontech.upgrade.web.admin.security;

import com.auth0.jwt.JWTCreator;
import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.web.commons.security.jwt.AbstractJwtToken;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 15:13
 * Class Name: UserToken
 * Author: Levent8421
 * Description:
 * User Token
 *
 * @author Levent8421
 */
public class UserToken extends AbstractJwtToken {
    /**
     * 默认令牌有效时间 1天
     */
    private static final long DEFAULT_TTL = 24 * 60 * 60 * 1000;
    public static final String USER_ID_NAME = "u_t_id";
    public static final String USER_NAME_NAME = "u_t_name";
    public static final String USER_LOGIN_NAME_NAME = "u_t_login_name";
    private final User user;

    public UserToken(User user, String key) {
        super(key, DEFAULT_TTL);
        this.user = user;
    }

    @Override
    protected void initClaims(JWTCreator.Builder builder) {
        builder.withClaim(USER_ID_NAME, user.getId());
        builder.withClaim(USER_NAME_NAME, user.getName());
        builder.withClaim(USER_LOGIN_NAME_NAME, user.getLoginName());
    }
}
