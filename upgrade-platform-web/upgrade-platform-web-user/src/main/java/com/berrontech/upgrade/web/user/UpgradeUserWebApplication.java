package com.berrontech.upgrade.web.user;

import com.berrontech.upgrade.commons.context.ContextPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Create By Levent8421
 * Create Time: 2020/12/1 11:20
 * Class Name: UpgradeUserWebApplication
 * Author: Levent8421
 * Description:
 * Upgrade platfrom web user application starter
 *
 * @author Levent8421
 */
@SpringBootApplication(scanBasePackages = ContextPackage.BASE_PACKAGE)
@MapperScan(basePackages = ContextPackage.MAPPER_PACKAGE)
public class UpgradeUserWebApplication {
    /**
     * Main method
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        SpringApplication.run(UpgradeUserWebApplication.class, args);
    }
}
