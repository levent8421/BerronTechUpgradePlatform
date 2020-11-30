package com.berrontech.upgrade.web.admin.controller.api;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.commons.exception.PermissionDeniedException;
import com.berrontech.upgrade.resource.UserResourceService;
import com.berrontech.upgrade.service.general.UserService;
import com.berrontech.upgrade.web.admin.security.UserToken;
import com.berrontech.upgrade.web.admin.security.UserTokenUtils;
import com.berrontech.upgrade.web.commons.conf.TokenConfiguration;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.security.TokenDataHolder;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import com.berrontech.upgrade.web.commons.vo.PaginationParam;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.berrontech.upgrade.web.commons.util.ParamChecker.notEmpty;
import static com.berrontech.upgrade.web.commons.util.ParamChecker.notNull;

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
    private static final int ROOT_USER_ID = 1;
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

    /**
     * 搜索用户
     *
     * @param name    名称
     * @param maxRows rows
     * @return GR
     */
    @GetMapping("/_search")
    public GeneralResult<List<User>> search(@RequestParam("name") String name, @RequestParam("maxRows") Integer maxRows) {
        final List<User> searchResult = userService.searchByName(name, maxRows);
        return GeneralResult.ok(searchResult);
    }

    /**
     * 查询所有用户
     *
     * @param param params
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<PageInfo<User>> users(PaginationParam param) {
        final PageInfo<User> users = userService.list(param.getPage(), param.getRows());
        userResourceService.resolveStaticPath(users.getList());
        return GeneralResult.ok(users);
    }

    /**
     * 创建用户
     *
     * @param param param
     * @return GR
     */
    @PutMapping("/")
    public GeneralResult<User> create(@RequestBody User param) {
        final User user = new User();
        copyCreateParams(param, user);
        final User res = userService.create(user);
        return GeneralResult.ok(res);
    }

    private void copyCreateParams(User param, User user) {
        final Class<BadRequestException> e = BadRequestException.class;
        notNull(param, e, "No param sent!");
        notEmpty(param.getLoginName(), e, "loginName is required!");
        notEmpty(param.getName(), e, "name is required!");
        notEmpty(param.getPassword(), e, "password is required!");

        user.setLoginName(param.getLoginName());
        user.setName(param.getName());
        user.setPassword(param.getPassword());
    }

    /**
     * Reset password
     *
     * @param id    id
     * @param param param
     * @return GR
     */
    @PostMapping("/{id}/_reset-password")
    public GeneralResult<User> resetPassword(@PathVariable("id") Integer id,
                                             @RequestBody User param) {
        notNull(param, BadRequestException.class, "No params sent!");
        notEmpty(param.getPassword(), BadRequestException.class, "Password is required!");
        if (id == ROOT_USER_ID) {
            final Integer currentId = UserTokenUtils.getUserId(tokenDataHolder);
            if (!Objects.equals(currentId, id)) {
                throw new PermissionDeniedException("无权重置超级用户密码！");
            }
        }

        final User user = userService.require(id);
        userService.resetPassword(id, param.getPassword());
        return GeneralResult.ok(user);
    }
}
