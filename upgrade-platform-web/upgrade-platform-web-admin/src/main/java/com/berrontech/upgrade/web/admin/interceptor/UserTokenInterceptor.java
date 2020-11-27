package com.berrontech.upgrade.web.admin.interceptor;

import com.berrontech.upgrade.web.admin.security.UserTokenVerifier;
import com.berrontech.upgrade.web.commons.interceptor.AbstractTokenInterceptor;
import com.berrontech.upgrade.web.commons.security.TokenDataHolder;
import com.berrontech.upgrade.web.commons.security.TokenVerifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 15:18
 * Class Name: UserTokenInterceptor
 * Author: Levent8421
 * Description:
 * User Token Interceptor
 *
 * @author Levent8421
 */
@Component
public class UserTokenInterceptor extends AbstractTokenInterceptor {
    private final TokenDataHolder tokenDataHolder;
    private final UserTokenVerifier tokenVerifier;
    private final List<String> interceptPathList;

    public UserTokenInterceptor(TokenDataHolder tokenDataHolder, UserTokenVerifier tokenVerifier) {
        this.tokenDataHolder = tokenDataHolder;
        this.tokenVerifier = tokenVerifier;
        this.interceptPathList = new ArrayList<>();
        loadPathList();
    }

    private void loadPathList() {
        interceptPathList.add("/api/token/**");
    }

    @Override
    protected boolean needAuthorization(HttpServletRequest request) {
        final String uri = request.getRequestURI();
        for (String path : interceptPathList) {
            if (matchPath(path, uri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected TokenVerifier getTokenVerifier() {
        return tokenVerifier;
    }

    @Override
    protected TokenDataHolder getDataHolder() {
        return tokenDataHolder;
    }
}
