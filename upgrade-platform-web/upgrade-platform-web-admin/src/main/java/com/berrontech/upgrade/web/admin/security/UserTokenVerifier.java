package com.berrontech.upgrade.web.admin.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.berrontech.upgrade.web.commons.conf.TokenConfiguration;
import com.berrontech.upgrade.web.commons.security.jwt.AbstractJwtTokenVerifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 15:14
 * Class Name: UserTokenVerifier
 * Author: Levent8421
 * Description:
 * User Token Reader
 *
 * @author Levent8421
 */
@Component
public class UserTokenVerifier extends AbstractJwtTokenVerifier {
    public UserTokenVerifier(TokenConfiguration tokenConfiguration) {
        super(tokenConfiguration);
    }

    @Override
    protected Map<String, Object> getPayload(DecodedJWT jwt) {
        final Integer id = jwt.getClaim(UserToken.USER_ID_NAME).asInt();
        final String name = jwt.getClaim(UserToken.USER_NAME_NAME).asString();
        final String loginName = jwt.getClaim(UserToken.USER_LOGIN_NAME_NAME).asString();
        final Map<String, Object> res = new HashMap<>(16);
        res.put(UserToken.USER_ID_NAME, id);
        res.put(UserToken.USER_NAME_NAME, name);
        res.put(UserToken.USER_LOGIN_NAME_NAME, loginName);
        return res;
    }
}
