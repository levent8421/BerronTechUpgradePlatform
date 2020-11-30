package com.berrontech.upgrade.web.admin.controller.api;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.commons.entity.UserApp;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.commons.exception.PermissionDeniedException;
import com.berrontech.upgrade.commons.exception.ResourceNotFoundException;
import com.berrontech.upgrade.service.general.AppPackageService;
import com.berrontech.upgrade.service.general.UserAppService;
import com.berrontech.upgrade.service.general.UserService;
import com.berrontech.upgrade.web.admin.security.UserTokenUtils;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.security.TokenDataHolder;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import com.berrontech.upgrade.web.commons.vo.PaginationParam;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.berrontech.upgrade.web.commons.util.ParamChecker.notNull;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 14:46
 * Class Name: ApiUserAppController
 * Author: Levent8421
 * Description:
 * User App Binder API Controller
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/token/user-app")
public class ApiUserAppController extends AbstractApiController {
    private final UserAppService userAppService;
    private final TokenDataHolder tokenDataHolder;
    private final UserService userService;
    private final AppPackageService appPackageService;

    public ApiUserAppController(UserAppService userAppService,
                                TokenDataHolder tokenDataHolder,
                                UserService userService,
                                AppPackageService appPackageService) {
        this.userAppService = userAppService;
        this.tokenDataHolder = tokenDataHolder;
        this.userService = userService;
        this.appPackageService = appPackageService;
    }

    /**
     * Apps for current user
     *
     * @param pageParams page params
     * @return GR
     */
    @GetMapping("/_mine")
    public GeneralResult<PageInfo<UserApp>> myApps(PaginationParam pageParams) {
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);
        final PageInfo<UserApp> binders = userAppService.findByUser(userId, pageParams.getPage(), pageParams.getRows());
        return GeneralResult.ok(binders);
    }

    /**
     * Find user app binder by appId
     *
     * @param appId appId
     * @return GR
     */
    @GetMapping("/_by-app")
    public GeneralResult<List<UserApp>> findBinderByApp(@RequestParam("appId") Integer appId) {
        final List<UserApp> userApps = userAppService.findByApp(appId);
        return GeneralResult.ok(userApps);
    }

    /**
     * 赋予用户权限
     *
     * @param param params
     * @return GR
     */
    @PostMapping("/_bind")
    public GeneralResult<UserApp> bind(@RequestBody UserApp param) {
        final Class<BadRequestException> e = BadRequestException.class;
        notNull(param, e, "No param sent!");
        notNull(param.getUserId(), e, "userId is required!");
        notNull(param.getAppId(), e, "appId is required!");
        notNull(param.getRole(), e, "role is required!");
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);


        final AppPackage app = appPackageService.require(param.getAppId());
        if (!Objects.equals(userId, app.getCreatorId())) {
            throw new PermissionDeniedException("只有管理员可以操作权限！");
        }
        if (!userService.existsById(param.getUserId())) {
            throw new ResourceNotFoundException("Can not find user by id " + param.getUserId());
        }

        final UserApp binder = userAppService.bind(param.getUserId(), param.getAppId(), param.getRole());
        return GeneralResult.ok(binder);
    }

    /**
     * Delete binder
     *
     * @param id binder id
     * @return GR
     */
    @DeleteMapping("/{id}")
    public GeneralResult<UserApp> removeBinder(@PathVariable("id") Integer id) {
        final UserApp binder = userAppService.requireWithApp(id);
        final AppPackage app = binder.getApp();
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);
        if (!Objects.equals(app.getCreatorId(), userId)) {
            throw new PermissionDeniedException("暂无权限操作该应用！");
        }
        if (Objects.equals(binder.getUserId(), userId)) {
            throw new PermissionDeniedException("不能删除自己的权限");
        }
        userAppService.deleteById(binder.getId());
        return GeneralResult.ok(binder);
    }
}
