package com.berrontech.upgrade.service.general.impl;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.commons.exception.BadRequestException;
import com.berrontech.upgrade.commons.exception.InternalServerErrorException;
import com.berrontech.upgrade.commons.exception.ResourceNotFoundException;
import com.berrontech.upgrade.commons.utils.encrypt.MD5Utils;
import com.berrontech.upgrade.repository.mapper.UserMapper;
import com.berrontech.upgrade.service.basic.impl.AbstractServiceImpl;
import com.berrontech.upgrade.service.general.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private static final String USER_SEARCH_TEMPLATE = "%s%%";
    private static final String DEFAULT_AVATAR = "default.png";
    private static final int PASSWORD_SALT_LEN = 5;
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
        final User user = findByLoginName(loginName);
        if (user == null) {
            throw new ResourceNotFoundException("Can not find account for [" + loginName + "]");
        }
        return user;
    }

    private User findByLoginName(String loginName) {
        return userMapper.selectOneByLoginName(loginName);
    }

    @Override
    public List<User> searchByName(String name, Integer maxRows) {
        final String query = String.format(USER_SEARCH_TEMPLATE, name);
        return userMapper.selectByNameLike(query, maxRows);
    }

    @Override
    public User create(User user) {
        final User exist = findByLoginName(user.getLoginName());
        if (exist != null) {
            throw new BadRequestException(String.format("Already exist a user [%s/%s]", exist.getName(), exist.getLoginName()));
        }
        final String password = user.getPassword();
        final String salt = RandomStringUtils.randomAlphanumeric(PASSWORD_SALT_LEN);
        user.setPassword(MD5Utils.md5(password, salt));
        user.setAvatar(DEFAULT_AVATAR);
        return save(user);
    }

    @Override
    public void resetPassword(Integer id, String password) {
        final String encodedPassword = MD5Utils.md5(password, RandomStringUtils.randomAlphanumeric(PASSWORD_SALT_LEN));
        final int rows = userMapper.updatePassword(id, encodedPassword);
        if (rows != 1) {
            throw new InternalServerErrorException("Error on update user for reset password, rows=" + rows);
        }
    }
}
