package com.z;

import static org.junit.jupiter.api.Assertions.*;

class ConfigMgrTest {

    @org.junit.jupiter.api.Test
    void get() {
        String s = ConfigMgr.get("gameWidth");
        int n = Integer.parseInt(s);
        System.out.println(n);
    }
}