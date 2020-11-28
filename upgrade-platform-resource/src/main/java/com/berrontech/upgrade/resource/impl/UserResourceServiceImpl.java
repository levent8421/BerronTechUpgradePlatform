package com.berrontech.upgrade.resource.impl;

import com.berrontech.upgrade.commons.entity.User;
import com.berrontech.upgrade.resource.ResourcePathService;
import com.berrontech.upgrade.resource.UserResourceService;
import com.berrontech.upgrade.resource.config.ResourceConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 19:00
 * Class Name: UserResourceServiceImpl
 * Author: Levent8421
 * Description:
 * User Resource Service
 *
 * @author Levent8421
 */
@Component
public class UserResourceServiceImpl extends AbstractEntityResourceService<User> implements UserResourceService {
    private final ResourceConfigurationProperties resourceConfigurationProperties;

    public UserResourceServiceImpl(ResourcePathService resourcePathService,
                                   ResourceConfigurationProperties resourceConfigurationProperties) {
        super(resourcePathService);
        this.resourceConfigurationProperties = resourceConfigurationProperties;
    }

    @Override
    public void resolveStaticPath(User entity) {
        final String avatar = withServer(resourceConfigurationProperties.getUserAvatarPath(), entity.getAvatar());
        entity.setAvatar(avatar);
    }
}
