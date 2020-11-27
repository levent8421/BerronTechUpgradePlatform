package com.berrontech.upgrade.web.commons.security;

/**
 * Create By Levent8421
 * Create Time: 2020/8/19 23:45
 * Class Name: WebToken
 * Author: Levent8421
 * Description:
 * Authorization Token In Web
 *
 * @author Levent8421
 */
public interface WebToken {
    /**
     * Convert to string
     *
     * @return token string
     */
    String toTokenString();
}
