package com.berrontech.upgrade.resource;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.commons.entity.AppVersion;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

/**
 * Create By leven ont 2020/11/29 15:28
 * Class Name :[AppVersionResourceService]
 * Application Version Resource Service
 *
 * @author levent
 */
public interface AppVersionResourceService extends EntityResourceService<AppVersion> {
    /**
     * Save file
     *
     * @param file        file
     * @param dirName     dir name
     * @param versionCode version code
     * @return filename
     */
    String savePackageFile(MultipartFile file, String dirName, Integer versionCode);

    /**
     * Resolve static file path in entity
     *
     * @param appVersion version
     * @param appPackage app
     */
    void resolveStaticPath(AppVersion appVersion, AppPackage appPackage);

    /**
     * Resolve static file path in entities
     *
     * @param appVersions versions
     * @param appPackage  app
     */
    void resolveStaticPath(Collection<AppVersion> appVersions, AppPackage appPackage);
}
