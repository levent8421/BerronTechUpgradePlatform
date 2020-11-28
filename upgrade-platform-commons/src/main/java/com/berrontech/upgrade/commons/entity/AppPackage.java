package com.berrontech.upgrade.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 11:37
 * Class Name: AppPackage
 * Author: Levent8421
 * Description:
 * Application package definition
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_app_package")
public class AppPackage extends AbstractEntity {
    /**
     * Application name
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * Application package file storage dir name
     */
    @Column(name = "dir_name", nullable = false)
    private String dirName;
    /**
     * Description text
     */
    @Column(name = "description")
    private String description;
    /**
     * Creator Id ;
     */
    @Column(name = "creator_id", length = 10, nullable = false)
    private Integer creatorId;
    /**
     * User :Creator
     * volatile for database
     */
    private User creator;
    /**
     * Application platform
     */
    @Column(name = "platform", length = 3, nullable = false)
    private Integer platform;
}
