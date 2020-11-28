package com.berrontech.upgrade.web.admin.controller.api;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.resource.UserResourceService;
import com.berrontech.upgrade.service.general.UserService;
import com.berrontech.upgrade.web.admin.security.UserToken;
import com.berrontech.upgrade.web.commons.conf.TokenConfiguration;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.security.TokenDataHolder;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 15:08
 * Class Name: ApiUserController
 * Author: Levent8421
 * Description:
 * User Api Controller
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/token/user")
public class ApiUserController extends AbstractApiController {
    private final UserService userService;
    private final TokenConfiguration tokenConfiguration;
    private final TokenDataHolder tokenDataHolder;
    private final UserResourceService userResourceService;

    public ApiUserController(UserService userService,
                             TokenConfiguration tokenConfiguration,
                             TokenDataHolder tokenDataHolder,
                             UserResourceService userResourceService) {
        this.userService = userService;
        this.tokenConfiguration = tokenConfiguration;
        this.tokenDataHolder = tokenDataHolder;
        this.userResourceService = userResourceService;
    }

    /**
     * Fetch current user info
     *
     * @return GR
     */
    @GetMapping("/_me")
    public GeneralResult<User> currentUser() {
        final Integer userId = tokenDataHolder.get(UserToken.USER_ID_NAME, Integer.class);
        final User user = userService.require(userId);
        userResourceService.resolveStaticPath(user);
        return GeneralResult.ok(user);
    }
}
