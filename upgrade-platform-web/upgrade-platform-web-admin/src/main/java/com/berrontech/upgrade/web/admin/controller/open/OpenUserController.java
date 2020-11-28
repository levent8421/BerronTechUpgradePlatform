package com.berrontech.upgrade.web.admin.controller.open;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.resource.UserResourceService;
import com.berrontech.upgrade.service.general.UserService;
import com.berrontech.upgrade.web.admin.security.UserToken;
import com.berrontech.upgrade.web.commons.conf.TokenConfiguration;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.dto.LoginParam;
import com.berrontech.upgrade.web.commons.security.vo.TokenAccountVo;
import com.berrontech.upgrade.web.commons.util.ParamChecker;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import org.springframework.web.bind.annotation.*;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 15:49
 * Class Name: OpenUserController
 * Author: Levent8421
 * Description:
 * User Open Api Controller
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/open/user")
public class OpenUserController extends AbstractApiController {
    private final UserService userService;
    private final TokenConfiguration tokenConfiguration;
    private final UserResourceService userResourceService;

    public OpenUserController(UserService userService,
                              TokenConfiguration tokenConfiguration,
                              UserResourceService userResourceService) {
        this.userService = userService;
        this.tokenConfiguration = tokenConfiguration;
        this.userResourceService = userResourceService;
    }

    /**
     * Mock login
     *
     * @param id id
     * @return GR
     */
    @GetMapping("/{id}/_mock-login")
    public GeneralResult<TokenAccountVo<User>> mockLogin(@PathVariable("id") Integer id) {
        final User user = userService.require(id);
        userResourceService.resolveStaticPath(user);
        final TokenAccountVo<User> vo = buildAccountVo(user);
        return GeneralResult.ok(vo);
    }

    /**
     * User Login
     *
     * @param param param
     * @return GR
     */
    @PostMapping("/_login")
    public GeneralResult<TokenAccountVo<User>> login(@RequestBody LoginParam param) {
        ParamChecker.notNull(param, BadRequestException.class, "No Params sent!");
        ParamChecker.notEmpty(param.getLoginName(), BadRequestException.class, "Login name is required!");
        ParamChecker.notEmpty(param.getPassword(), BadRequestException.class, "Password is required!");
        final User user = userService.login(param.getLoginName(), param.getPassword());
        userResourceService.resolveStaticPath(user);
        final TokenAccountVo<User> vo = buildAccountVo(user);
        return GeneralResult.ok(vo);
    }

    private TokenAccountVo<User> buildAccountVo(User user) {
        final UserToken userToken = new UserToken(user, tokenConfiguration.getKey());
        final TokenAccountVo<User> vo = new TokenAccountVo<>();
        vo.setToken(userToken.toTokenString());
        vo.setAccount(user);
        return vo;
    }
}
