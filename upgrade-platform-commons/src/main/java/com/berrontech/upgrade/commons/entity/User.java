package com.berrontech.upgrade.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 10:54
 * Class Name: User
 * Author: Levent8421
 * Description:
 * 用户实体类
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_user")
public class User extends AbstractEntity {
    /**
     * Username
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    /**
     * Login name
     */
    @Column(name = "login_name", length = 100, nullable = false)
    private String loginName;
    /**
     * Password (Encrypted)
     */
    @Column(name = "password", nullable = false)
    private String password;
    /**
     * Avatar filename
     */
    @Column(name = "avatar", nullable = false)
    private String avatar;
}
