package com.berrontech.upgrade.web.user.controller.open;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.service.general.AppPackageService;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import com.berrontech.upgrade.web.commons.vo.PaginationParam;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By Levent8421
 * Create Time: 2020/12/12 16:06
 * Class Name: OpenAppPackageController
 * Author: Levent8421
 * Description:
 * Open App Package Controller
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/open/app")
public class OpenAppPackageController extends AbstractApiController {
    private final AppPackageService appPackageService;

    public OpenAppPackageController(AppPackageService appPackageService) {
        this.appPackageService = appPackageService;
    }

    /**
     * 分页查询
     *
     * @param param param
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<PageInfo<AppPackage>> list(PaginationParam param) {
        final PageInfo<AppPackage> packagePageInfo = appPackageService.listWithOrder(param.getPage(), param.getRows());
        return GeneralResult.ok(packagePageInfo);
    }
}
