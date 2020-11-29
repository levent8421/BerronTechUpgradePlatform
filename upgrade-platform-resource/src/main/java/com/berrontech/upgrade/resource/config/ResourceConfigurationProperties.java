package com.berrontech.upgrade.resource.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Create By Levent8421
 * Create Time: 2020/9/12 19:24
 * Class Name: ResourceConfigurationProperties
 * Author: Levent8421
 * Description:
 * 静态资源配置
 *
 * @author Levent8421
 */
@Data
@Component
@ConfigurationProperties(prefix = "static")
public class ResourceConfigurationProperties {
    @PostConstruct
    public void checkConfig() {
        if (StringUtils.isBlank(rootPath)) {
            throw new IllegalStateException("Can not find StaticResourceRootPath(static.root-path) config!");
        }
        if (StringUtils.isBlank(server)) {
            throw new IllegalStateException("Can not find StaticResourceServer(static.server) config!");
        }
    }

    /**
     * 静态资源根目录
     */
    private String rootPath;
    /**
     * 静态资源服务器
     */
    private String server;
    /**
     * 用户头像存放目录
     */
    private String userAvatarPath = "user/avatar";
    /**
     * 版本文件保存目录
     */
    private String appFilePath = "versions";
}
