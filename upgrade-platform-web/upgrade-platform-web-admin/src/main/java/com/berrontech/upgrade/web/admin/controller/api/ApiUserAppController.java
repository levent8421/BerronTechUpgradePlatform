package com.berrontech.upgrade.web.admin.controller.api;

import com.berrontech.upgrade.commons.entity.UserApp;
import com.berrontech.upgrade.service.general.UserAppService;
import com.berrontech.upgrade.web.admin.security.UserTokenUtils;
import com.berrontech.upgrade.web.commons.controller.AbstractApiController;
import com.berrontech.upgrade.web.commons.security.TokenDataHolder;
import com.berrontech.upgrade.web.commons.vo.GeneralResult;
import com.berrontech.upgrade.web.commons.vo.PaginationParam;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ApiUserAppController(UserAppService userAppService,
                                TokenDataHolder tokenDataHolder) {
        this.userAppService = userAppService;
        this.tokenDataHolder = tokenDataHolder;
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
}
