package com.berrontech.upgrade.web.admin;

import com.berrontech.upgrade.commons.context.ContextPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Create By Levent8421
 * Create Time: 2020/11/27 15:04
 * Class Name: UpgradeAdminApplication
 * Author: Levent8421
 * Description:
 * Upgrade Platform admin application entry
 *
 * @author Levent8421
 */
@SpringBootApplication(scanBasePackages = ContextPackage.BASE_PACKAGE)
@MapperScan(basePackages = ContextPackage.MAPPER_PACKAGE)
public class UpgradeAdminApplication {
    /**
     * Main Method
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(UpgradeAdminApplication.class, args);
    }
}
