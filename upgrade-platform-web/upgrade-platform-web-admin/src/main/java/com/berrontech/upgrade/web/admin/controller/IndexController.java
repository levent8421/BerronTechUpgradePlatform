package com.berrontech.upgrade.web.admin.controller;

import com.berrontech.upgrade.web.commons.controller.AbstractController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 15:40
 * Class Name: IndexController
 * Author: Levent8421
 * Description:
 * Index Controller
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api")
public class IndexController extends AbstractController {
    /**
     * Index
     *
     * @return body
     */
    @GetMapping("/")
    public String index() {
        return "UpgradeAdminApplication";
    }
}
