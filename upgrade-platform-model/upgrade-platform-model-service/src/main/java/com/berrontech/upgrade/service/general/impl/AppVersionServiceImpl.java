package com.berrontech.upgrade.service.general.impl;

import com.berrontech.upgrade.commons.entity.AppVersion;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.repository.mapper.AppVersionMapper;
import com.berrontech.upgrade.service.basic.impl.AbstractServiceImpl;
import com.berrontech.upgrade.service.general.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 17:20
 * Class Name: AppVersionServiceImpl
 * Author: Levent8421
 * Description:
 * Application version service
 *
 * @author Levent8421
 */
@Service
public class AppVersionServiceImpl extends AbstractServiceImpl<AppVersion> implements AppVersionService {
    private final AppVersionMapper appVersionMapper;

    public AppVersionServiceImpl(AppVersionMapper appVersionMapper) {
        super(appVersionMapper);
        this.appVersionMapper = appVersionMapper;
    }

    @Override
    public PageInfo<AppVersion> findByApp(Integer appId, int page, int rows) {
        return PageHelper.startPage(page, rows)
                .doSelectPageInfo(() -> appVersionMapper.selectByAppFetchAll(appId));
    }

    @Override
    public AppVersion create(AppVersion version) {
        final AppVersion exists = findByAppAndVersion(version.getAppId(), version.getVersionCode(), version.getVersionName());
        if (exists != null) {
            final String err = String.format("Already exist aversion [%s/%s] create by [%s]",
                    exists.getVersionName(), exists.getVersionCode(), exists.getPublisher().getName());
            throw new BadRequestException(err);
        }
        return save(version);
    }

    @Override
    public AppVersion findByAppAndVersion(Integer appId, Integer versionCode, String versionName) {
        return appVersionMapper.selectByAppAndVersion(appId, versionCode, versionName);
    }
}
