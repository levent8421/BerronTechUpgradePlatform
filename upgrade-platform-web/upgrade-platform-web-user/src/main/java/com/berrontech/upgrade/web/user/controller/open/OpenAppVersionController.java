package com.berrontech.upgrade.web.user.controller.open;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.commons.entity.AppVersion;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.resource.AppVersionResourceService;
import com.berrontech.upgrade.service.general.AppPackageService;
import com.berrontech.upgrade.service.general.AppVersionService;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By Levent8421
 * Create Time: 2020/12/1 13:32
 * Class Name: OpenAppVersionController
 * Author: Levent8421
 * Description:
 * Open App Version Controller
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/open/version")
public class OpenAppVersionController extends AbstractApiController {
    private final AppVersionService appVersionService;
    private final AppPackageService appPackageService;
    private final AppVersionResourceService appVersionResourceService;

    public OpenAppVersionController(AppVersionService appVersionService,
                                    AppPackageService appPackageService,
                                    AppVersionResourceService appVersionResourceService) {
        this.appVersionService = appVersionService;
        this.appPackageService = appPackageService;
        this.appVersionResourceService = appVersionResourceService;
    }

    /**
     * Find last version
     *
     * @param appId  app id
     * @param appKey app key(dirName)
     * @return GR
     */
    @GetMapping("/_last")
    public GeneralResult<AppVersion> lastVersion(Integer appId, String appKey) {
        final AppPackage app;
        if (appId != null) {
            app = appPackageService.require(appId);
        } else if (StringUtils.isNotBlank(appKey)) {
            app = appPackageService.requireByKey(appKey);
        } else {
            throw new BadRequestException("appId or appKey is required!");
        }
        final AppVersion version = appVersionService.findLastVersion(app.getId());
        version.setApp(app);
        appVersionResourceService.resolveStaticPath(version, app);
        return GeneralResult.ok(version);
    }
}
