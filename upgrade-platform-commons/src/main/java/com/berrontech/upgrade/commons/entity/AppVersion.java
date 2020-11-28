package com.berrontech.upgrade.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/11/28 17:08
 * Class Name: AppVersion
 * Author: Levent8421
 * Description:
 * Application version entity
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_app_version")
public class AppVersion extends AbstractEntity {
    public static final int STATE_NOT_AVAILABLE = 0x00;
    public static final int STATE_AVAILABLE = 0x01;
    /**
     * App id
     */
    @Column(name = "app_id", length = 10, nullable = false)
    private Integer appId;
    /**
     * APP
     */
    private AppPackage app;
    /**
     * Publisher id
     */
    @Column(name = "publisher_id", length = 10, nullable = false)
    private Integer publisherId;
    /**
     * Publisher id
     */
    private User publisher;
    /**
     * version code
     */
    @Column(name = "version_code", length = 6, nullable = false)
    private Integer versionCode;
    /**
     * Version name
     */
    @Column(name = "version_name", length = 100, nullable = false)
    private String versionName;
    /**
     * Release note
     */
    @Column(name = "release_note")
    private String releaseNote;
    /**
     * Description
     */
    @Column(name = "description")
    private String description;
    /**
     * FileName
     */
    @Column(name = "filename")
    private String filename;
    /**
     * State
     */
    @Column(name = "state", length = 3, nullable = false)
    private Integer state;
}
