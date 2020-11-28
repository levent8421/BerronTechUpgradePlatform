package com.berrontech.upgrade.service.general;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.service.basic.AbstractService;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 11:44
 * Class Name: AppPackageService
 * Author: Levent8421
 * Description:
 * Application Package Service
 *
 * @author Levent8421
 */
public interface AppPackageService extends AbstractService<AppPackage> {
    /**
     * Create new app
     *
     * @param app app
     * @return res
     */
    AppPackage create(AppPackage app);

    /**
     * 查询是否存在相同DirName的APP
     *
     * @param dirName dir name
     * @return bool
     */
    boolean existByDirName(String dirName);
}
