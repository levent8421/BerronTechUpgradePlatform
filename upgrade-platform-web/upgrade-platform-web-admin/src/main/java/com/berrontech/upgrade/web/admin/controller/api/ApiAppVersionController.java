package com.berrontech.upgrade.web.admin.controller.api;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.commons.entity.AppVersion;
import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.commons.exception.PermissionDeniedException;
import com.berrontech.upgrade.resource.AppVersionResourceService;
import com.berrontech.upgrade.service.general.AppVersionService;
import com.berrontech.upgrade.service.general.UserAppService;
import com.berrontech.upgrade.web.admin.security.UserTokenUtils;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.security.TokenDataHolder;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import com.berrontech.upgrade.web.commons.vo.PaginationParam;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static com.berrontech.upgrade.web.commons.util.ParamChecker.notNull;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 17:22
 * Class Name: ApiAppVersionController
 * Author: Levent8421
 * Description:
 * App version api controller
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/token/app-version")
public class ApiAppVersionController extends AbstractApiController {
    private final AppVersionService appVersionService;
    private final TokenDataHolder tokenDataHolder;
    private final UserAppService userAppService;
    private final AppVersionResourceService appVersionResourceService;

    public ApiAppVersionController(AppVersionService appVersionService,
                                   TokenDataHolder tokenDataHolder,
                                   UserAppService userAppService,
                                   AppVersionResourceService appVersionResourceService) {
        this.appVersionService = appVersionService;
        this.tokenDataHolder = tokenDataHolder;
        this.userAppService = userAppService;
        this.appVersionResourceService = appVersionResourceService;
    }

    /**
     * Find  versions by app id
     *
     * @param appId           app id
     * @param paginationParam page param
     * @return GR
     */
    @GetMapping("/_by-app")
    public GeneralResult<PageInfo<AppVersion>> findVersionByApp(@RequestParam("appId") Integer appId,
                                                                PaginationParam paginationParam) {
        final PageInfo<AppVersion> versionList = appVersionService.findByApp(appId, paginationParam.getPage(), paginationParam.getRows());
        return GeneralResult.ok(versionList);
    }

    /**
     * create a new version
     *
     * @param param param
     * @return GR
     */
    @PutMapping("/")
    public GeneralResult<AppVersion> createVersion(@RequestBody AppVersion param) {
        final AppVersion version = new AppVersion();
        copyCreateParam(param, version);
        final Integer appId = param.getAppId();
        version.setAppId(param.getAppId());
        notNull(appId, BadRequestException.class, "AppId is required!");
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);
        if (!userAppService.hasBind(userId, appId)) {
            throw new PermissionDeniedException("您暂无权限操作该应用！");
        }
        version.setPublisherId(userId);
        final AppVersion res = appVersionService.create(version);
        return GeneralResult.ok(res);
    }

    private void copyCreateParam(AppVersion param, AppVersion version) {
        final Class<BadRequestException> e = BadRequestException.class;
        notNull(param, e, "No param sent!");
        notNull(param.getVersionCode(), e, "VersionCode is required!");
        notNull(param.getVersionName(), e, "VersionName is required!");
        notNull(param.getState(), e, "State is required!");

        version.setVersionCode(param.getVersionCode());
        version.setVersionName(param.getVersionName());
        version.setReleaseNote(param.getReleaseNote());
        version.setDescription(param.getDescription());
        version.setState(param.getState());
    }

    /**
     * Find app version by id
     *
     * @param id id
     * @return GR
     */
    @GetMapping("/{id}")
    public GeneralResult<AppVersion> findVersionById(@PathVariable("id") Integer id) {
        final AppVersion appVersion = appVersionService.requireWithAll(id);
        final AppPackage app = appVersion.getApp();
        appVersionResourceService.resolveStaticPath(appVersion, app);
        return GeneralResult.ok(appVersion);
    }

    /**
     * 更新版本信息
     *
     * @param id    id
     * @param param param
     * @return GR
     */
    @PostMapping("/{id}")
    public GeneralResult<AppVersion> updateVersionInfo(@PathVariable("id") Integer id,
                                                       @RequestBody AppVersion param) {
        final AppVersion version = appVersionService.require(id);
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);
        if (!Objects.equals(version.getPublisherId(), userId)) {
            throw new PermissionDeniedException("您暂无权限操作该版本！");
        }
        copyUpdateParam(param, version);
        final AppVersion res = appVersionService.updateById(version);
        return GeneralResult.ok(res);
    }

    private void copyUpdateParam(AppVersion param, AppVersion version) {
        final Class<BadRequestException> e = BadRequestException.class;
        notNull(param, e, "No params sent!");
        notNull(param.getState(), e, "State is required!");

        version.setState(param.getState());
        version.setDescription(param.getDescription());
        version.setReleaseNote(param.getReleaseNote());
    }

    /**
     * 上传版本文件
     *
     * @param id   id
     * @param file file
     * @return GR
     */
    @PostMapping("/{id}/_file")
    public GeneralResult<AppVersion> uploadAppVersionFile(@PathVariable("id") Integer id,
                                                          @RequestBody MultipartFile file) {
        final Integer userId = UserTokenUtils.getUserId(tokenDataHolder);
        final AppVersion version = appVersionService.requireWithAll(id);
        if (StringUtils.isNoneBlank(version.getFilename())) {
            throw new BadRequestException("The version file is read-only!");
        }
        final AppPackage app = version.getApp();
        final User publisher = version.getPublisher();
        if (!Objects.equals(userId, publisher.getId())) {
            throw new PermissionDeniedException("Only the publisher of this version can upload files");
        }
        final AppVersion res = appVersionService.saveFile(file, version, app);
        return GeneralResult.ok(res);
    }
}
