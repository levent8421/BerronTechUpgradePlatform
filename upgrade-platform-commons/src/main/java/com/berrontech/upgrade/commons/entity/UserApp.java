package com.berrontech.upgrade.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 13:41
 * Class Name: UserApp
 * Author: Levent8421
 * Description:
 * User App Entity
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_user_app")
public class UserApp extends AbstractEntity {
    /**
     * 角色：维护者
     */
    public static final int ROLE_MAINTAINER = 0x01;
    /**
     * 角色：拥有者
     */
    public static final int ROLE_OWNER = 0x00;
    /**
     * User ID
     */
    @Column(name = "user_id", length = 10, nullable = false)
    private Integer userId;
    /**
     * User
     */
    private User user;
    /**
     * App Id
     */
    @Column(name = "app_id", length = 10, nullable = false)
    private Integer appId;
    /**
     * App
     */
    private AppPackage app;
    /**
     * Role Code
     */
    @Column(name = "role", length = 10, nullable = false)
    private Integer role;
}
