package com.berrontech.upgrade.service.general.impl;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.commons.exception.ResourceNotFoundException;
import com.berrontech.upgrade.commons.utils.encrypt.MD5Utils;
import com.berrontech.upgrade.repository.mapper.UserMapper;
import com.berrontech.upgrade.service.basic.impl.AbstractServiceImpl;
import com.berrontech.upgrade.service.general.UserService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 14:49
 * Class Name: UserServiceImpl
 * Author: Levent8421
 * Description:
 * User Service
 *
 * @author Levent8421
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        super(userMapper);
        this.userMapper = userMapper;
    }

    @Override
    public User login(String loginName, String password) {
        final User user = requireByLoginName(loginName);
        if (!MD5Utils.isMatched(user.getPassword(), password)) {
            throw new BadRequestException("Password not matched!");
        }
        return user;
    }

    @Override
    public User requireByLoginName(String loginName) {
        final User user = userMapper.selectOneByLoginName(loginName);
        if (user == null) {
            throw new ResourceNotFoundException("Can not find account for [" + loginName + "]");
        }
        return user;
    }
}
