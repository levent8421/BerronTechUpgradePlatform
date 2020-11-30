package com.berrontech.upgrade.service.general.impl;

import com.berrontech.upgrade.commons.entity.UserApp;
import com.berrontech.upgrade.commons.exception.ResourceNotFoundException;
import com.berrontech.upgrade.repository.mapper.UserAppMapper;
import com.berrontech.upgrade.service.basic.impl.AbstractServiceImpl;
import com.berrontech.upgrade.service.general.UserAppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 13:51
 * Class Name: UserAppServiceImpl
 * Author: Levent8421
 * Description:
 * User App Service Impl
 *
 * @author Levent8421
 */
@Service
public class UserAppServiceImpl extends AbstractServiceImpl<UserApp> implements UserAppService {
    private final UserAppMapper userAppMapper;

    public UserAppServiceImpl(UserAppMapper userAppMapper) {
        super(userAppMapper);
        this.userAppMapper = userAppMapper;
    }

    @Override
    public UserApp bind(Integer userId, Integer appId, int role) {
        UserApp binder = findByUserAndApp(userId, appId);
        if (binder != null) {
            binder.setRole(role);
            return updateById(binder);
        }
        binder = new UserApp();
        binder.setUserId(userId);
        binder.setAppId(appId);
        binder.setRole(role);
        return save(binder);
    }

    @Override
    public UserApp findByUserAndApp(Integer userId, Integer appId) {
        return userAppMapper.selectByUserAndApp(userId, appId);
    }

    @Override
    public PageInfo<UserApp> findByUser(Integer userId, int page, int rows) {
        return PageHelper.startPage(page, rows)
                .doSelectPageInfo(() -> userAppMapper.selectByUserFetchApp(userId));
    }

    @Override
    public boolean hasBind(Integer userId, Integer appId) {
        return getRole(userId, appId) != null;
    }

    @Override
    public Integer getRole(Integer userId, Integer appId) {
        return userAppMapper.selectRoleByUserAndApp(userId, appId);
    }

    @Override
    public List<UserApp> findByApp(Integer appId) {
        return userAppMapper.selectByApp(appId);
    }

    @Override
    public UserApp requireWithApp(Integer id) {
        final UserApp binder = userAppMapper.selectByIdFetchApp(id);
        if (binder == null) {
            throw new ResourceNotFoundException("Can not find UserAppBinder by id " + id);
        }
        return binder;
    }
}
