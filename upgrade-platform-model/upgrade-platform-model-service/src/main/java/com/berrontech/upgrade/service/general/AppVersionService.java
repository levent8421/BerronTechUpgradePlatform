package com.berrontech.upgrade.service.general;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.commons.entity.AppVersion;
import com.berrontech.upgrade.service.basic.AbstractService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 17:19
 * Class Name: AppVersionService
 * Author: Levent8421
 * Description:
 * Application version service component
 *
 * @author Levent8421
 */
public interface AppVersionService extends AbstractService<AppVersion> {
    /**
     * Find version by app
     *
     * @param appId app id
     * @param page  page
     * @param rows  rows
     * @return page info
     */
    PageInfo<AppVersion> findByApp(Integer appId, int page, int rows);

    /**
     * Create version
     *
     * @param version version
     * @return version
     */
    AppVersion create(AppVersion version);

    /**
     * Find version by app and versionCode or version name
     *
     * @param appId       app id
     * @param versionCode version code
     * @param versionName version name
     * @return version
     */
    AppVersion findByAppAndVersion(Integer appId, Integer versionCode, String versionName);

    /**
     * Require a version with app and publisher
     *
     * @param id id
     * @return version entity
     */
    AppVersion requireWithAll(Integer id);

    /**
     * Save version file
     *
     * @param version version
     * @param app     app
     * @param file    file
     * @return app version entity
     */
    AppVersion saveFile(MultipartFile file, AppVersion version, AppPackage app);

    /**
     * Find last version by appId
     *
     * @param appId appId
     * @return version
     */
    AppVersion findLastVersion(Integer appId);
}
