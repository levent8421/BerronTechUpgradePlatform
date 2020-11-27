package com.berrontech.upgrade.commons.utils.encrypt;

import org.junit.jupiter.api.Test;

class MD5UtilsTest {

    @Test
    void md5() {
        System.out.println(MD5Utils.md5("111111", "levent"));
    }
}