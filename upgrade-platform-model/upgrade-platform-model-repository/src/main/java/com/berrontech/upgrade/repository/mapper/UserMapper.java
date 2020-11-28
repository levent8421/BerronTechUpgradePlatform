package com.berrontech.upgrade.repository.mapper;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.repository.mybatis.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 14:29
 * Class Name: UserMapper
 * Author: Levent8421
 * Description:
 * User  Mapper Interface
 *
 * @author Levent8421
 */
@Repository
public interface UserMapper extends AbstractMapper<User> {
    /**
     * Select one user by login name
     *
     * @param loginName login name
     * @return user
     */
    User selectOneByLoginName(@Param("loginName") String loginName);
}
