package com.berrontech.upgrade.repository.mapper;

import com.berrontech.upgrade.commons.entity.AppVersion;
import com.berrontech.upgrade.repository.mybatis.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 17:13
 * Class Name: AppVersionMapper
 * Author: Levent8421
 * Description:
 * Appplication Version mapper
 *
 * @author Levent8421
 */
@Repository
public interface AppVersionMapper extends AbstractMapper<AppVersion> {
    /**
     * select app versions by app
     *
     * @param appId appId
     * @return version
     */
    List<AppVersion> selectByAppFetchAll(@Param("appId") Integer appId);

    /**
     * Select by appId and (versionCode or versionName)
     *
     * @param appId       appId
     * @param versionCode vCode
     * @param versionName vName
     * @return version
     */
    AppVersion selectByAppAndVersion(@Param("appId") Integer appId,
                                     @Param("versionCode") Integer versionCode,
                                     @Param("versionName") String versionName);

    /**
     * Select version by id fetcher all
     *
     * @param id id
     * @return version
     */
    AppVersion selectByIdFetchAll(@Param("id") Integer id);
}
