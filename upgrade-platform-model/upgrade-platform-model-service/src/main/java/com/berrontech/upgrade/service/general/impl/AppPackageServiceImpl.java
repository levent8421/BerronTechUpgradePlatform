package com.berrontech.upgrade.service.general.impl;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.repository.mapper.AppPackageMapper;
import com.berrontech.upgrade.service.basic.impl.AbstractServiceImpl;
import com.berrontech.upgrade.service.general.AppPackageService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 11:45
 * Class Name: AppPackageServiceImpl
 * Author: Levent8421
 * Description:
 * Application Package Service Implementation
 *
 * @author Levent8421
 */
@Service
public class AppPackageServiceImpl extends AbstractServiceImpl<AppPackage> implements AppPackageService {
    private final AppPackageMapper appPackageMapper;

    public AppPackageServiceImpl(AppPackageMapper appPackageMapper) {
        super(appPackageMapper);
        this.appPackageMapper = appPackageMapper;
    }

    @Override
    public AppPackage create(AppPackage app) {
        if (existByDirName(app.getDirName())) {
            throw new BadRequestException("Already exists a same dirName: " + app.getDirName());
        }
        return save(app);
    }

    @Override
    public boolean existByDirName(String dirName) {
        final Integer existsFlag = appPackageMapper.existsByDirName(dirName);
        return existsFlag != null;
    }
}
