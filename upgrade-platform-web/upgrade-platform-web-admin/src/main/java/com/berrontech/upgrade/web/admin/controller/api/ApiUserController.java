package com.berrontech.upgrade.web.admin.controller.api;

import com.berrontech.upgrade.service.general.UserService;
import com.berrontech.upgrade.web.commons.conf.TokenConfiguration;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
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

    public ApiUserController(UserService userService, TokenConfiguration tokenConfiguration) {
        this.userService = userService;
        this.tokenConfiguration = tokenConfiguration;
    }
}
