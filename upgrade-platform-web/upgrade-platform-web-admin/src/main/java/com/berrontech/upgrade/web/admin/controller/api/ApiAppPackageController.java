package com.berrontech.upgrade.web.admin.controller.api;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.commons.entity.UserApp;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.commons.exception.PermissionDeniedException;
import com.berrontech.upgrade.service.general.AppPackageService;
import com.berrontech.upgrade.service.general.UserAppService;
import com.berrontech.upgrade.web.admin.security.UserTokenUtils;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.security.TokenDataHolder;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.berrontech.upgrade.web.commons.util.ParamChecker.notEmpty;
import static com.berrontech.upgrade.web.commons.util.ParamChecker.notNull;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 12:17
 * Class Name: ApiAppPackageController
 * Author: Levent8421
 * Description:
 * Application Package Api Controller
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/token/app")
@Slf4j
public class ApiAppPackageController extends AbstractApiController {
    private final AppPackageService appPackageService;
    private final TokenDataHolder tokenDataHolder;
    private final UserAppService userAppService;

    public ApiAppPackageController(AppPackageService appPackageService,
                                   TokenDataHolder tokenDataHolder,
                                   UserAppService userAppService) {
        this.appPackageService = appPackageService;
        this.tokenDataHolder = tokenDataHolder;
        this.userAppService = userAppService;
    }

    /**
     * 创建APP
     *
     * @param param params
     * @return GR
     */
    @PutMapping("/")
    public GeneralResult<AppPackage> create(@RequestBody AppPackage param) {
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);
        final AppPackage app = new AppPackage();
        copyCreateParam(param, app);
        app.setCreatorId(userId);
        final AppPackage res = appPackageService.create(app);
        final UserApp userApp = userAppService.bind(userId, app.getId(), UserApp.ROLE_OWNER);
        log.info("Bind user [{}] as [{}] for app [{}], bindId=[{}]", userId, UserApp.ROLE_OWNER, app.getId(), userApp.getId());
        return GeneralResult.ok(res);
    }

    private void copyCreateParam(AppPackage param, AppPackage app) {
        final Class<BadRequestException> e = BadRequestException.class;
        notNull(param, e, "No params sent!");
        notEmpty(param.getName(), e, "name is required!");
        notEmpty(param.getDirName(), e, "dirName is required!");
        notNull(param.getPlatform(), e, "platform is required!");

        app.setName(param.getName());
        app.setDirName(param.getDirName());
        app.setDescription(param.getDescription());
        app.setPlatform(param.getPlatform());
    }

    /**
     * Find app by id
     *
     * @param id id
     * @return GR
     */
    @GetMapping("/{id}")
    public GeneralResult<AppPackage> findAppById(@PathVariable("id") Integer id) {
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);
        if (!userAppService.hasBind(userId, id)) {
            throw new PermissionDeniedException("暂无权限查看该APP!");
        }
        final AppPackage appPackage = appPackageService.require(id);
        return GeneralResult.ok(appPackage);
    }

    /**
     * update the app info
     *
     * @param id    id
     * @param param params
     * @return GR
     */
    @PostMapping("/{id}")
    public GeneralResult<AppPackage> updateAppInfo(@PathVariable("id") Integer id,
                                                   @RequestBody AppPackage param) {
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);
        final Integer role = userAppService.getRole(userId, id);
        if (!Objects.equals(role, UserApp.ROLE_OWNER)) {
            throw new PermissionDeniedException("您暂无权限修改该APP信息");
        }
        final AppPackage app = appPackageService.require(id);
        copyUpdateParams(param, app);
        final AppPackage res = appPackageService.updateById(app);
        return GeneralResult.ok(res);
    }

    private void copyUpdateParams(AppPackage param, AppPackage app) {
        final Class<BadRequestException> e = BadRequestException.class;
        notNull(param, e, "No update params sent!");
        notEmpty(param.getName(), e, "update name is required!");
        notEmpty(param.getDirName(), e, "update dirName is required!");
        notNull(param.getPlatform(), e, "update platform is required!");

        app.setName(param.getName());
        app.setDirName(param.getDirName());
        app.setDescription(param.getDescription());
        app.setPlatform(param.getPlatform());
    }
}
