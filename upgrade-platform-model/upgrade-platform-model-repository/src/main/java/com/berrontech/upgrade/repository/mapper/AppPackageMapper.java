package com.berrontech.upgrade.repository.mapper;

import com.berrontech.upgrade.commons.entity.AppPackage;
import com.berrontech.upgrade.repository.mybatis.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 11:41
 * Class Name: AppPackageMapper
 * Author: Levent8421
 * Description:
 * ApplicationPackage repository component
 *
 * @author Levent8421
 */
@Repository
public interface AppPackageMapper extends AbstractMapper<AppPackage> {
    /**
     * 查询DIR_name的APP是否存在
     *
     * @param dirName dir name
     * @return 1 or null
     */
    Integer existsByDirName(@Param("dirName") String dirName);
}
