package com.berrontech.upgrade.resource.impl;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.commons.entity.AppVersion;
import com.berrontech.upgrade.commons.utils.CollectionUtils;
import com.berrontech.upgrade.resource.AppVersionResourceService;
import com.berrontech.upgrade.resource.ResourcePathService;
import com.berrontech.upgrade.resource.config.ResourceConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;

/**
 * Create By leven ont 2020/11/29 15:29
 * Class Name :[AppVersionResourceServiceImpl]
 * <p>
 * Application Version Resource Service Implementation
 *
 * @author leven
 */
@Slf4j
@Component
public class AppVersionResourceServiceImpl extends AbstractEntityResourceService<AppVersion> implements AppVersionResourceService {
    private final ResourceConfigurationProperties resourceConfigurationProperties;

    public AppVersionResourceServiceImpl(ResourcePathService resourcePathService,
                                         ResourceConfigurationProperties resourceConfigurationProperties) {
        super(resourcePathService);
        this.resourceConfigurationProperties = resourceConfigurationProperties;
    }

    private String generateStorageFilename(MultipartFile file, String dirName, Integer versionCode) {
        final String extName = getExtensions(file.getOriginalFilename());
        return String.format("pack-%s-%s%s", dirName, versionCode, extName);
    }

    @Override
    public String savePackageFile(MultipartFile file, String dirName, Integer versionCode) {
        final String filename = generateStorageFilename(file, dirName, versionCode);
        final File dir = createPathInRootPath(resourceConfigurationProperties.getAppFilePath(), dirName);
        final File targetFile = new File(dir, filename);
        save(file, targetFile);
        return filename;
    }

    @Override
    public void resolveStaticPath(AppVersion appVersion, AppPackage appPackage) {
        final String filename = appVersion.getFilename();
        if (StringUtils.isBlank(filename)) {
            return;
        }
        final String dirName = appPackage.getDirName();
        appVersion.setFilename(withServer(resourceConfigurationProperties.getAppFilePath(), dirName, filename));
    }

    @Override
    public void resolveStaticPath(Collection<AppVersion> appVersions, AppPackage appPackage) {
        if (CollectionUtils.isEmpry(appVersions)) {
            return;
        }
        for (AppVersion version : appVersions) {
            resolveStaticPath(version, appPackage);
        }
    }

    @Override
    @Deprecated
    public void resolveStaticPath(AppVersion entity) {
        log.warn("Method: resolveStaticPath(AppVersion entity) is Deprecated!");
        final AppPackage app = entity.getApp();
        if (app == null) {
            throw new UnsupportedOperationException("Not supported!See resolveStaticPath(AppVersion,AppPackage)");
        }
        resolveStaticPath(entity, app);
    }
}
