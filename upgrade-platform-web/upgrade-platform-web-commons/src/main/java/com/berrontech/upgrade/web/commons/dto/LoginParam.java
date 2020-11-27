package com.berrontech.upgrade.web.commons.dto;

import lombok.Data;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 16:11
 * Class Name: LoginParam
 * Author: Levent8421
 * Description:
 * Login Params
 *
 * @author Levent8421
 */
@Data
public class LoginParam {
    /**
     * Login Name
     */
    private String loginName;
    /**
     * Password
     */
    private String password;
}
