package com.z;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.util.Properties;

public class ConfigMgr {
    private static Properties props = null;
    static {
        props = new Properties();
        try {
            props.load(ConfigMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
            return props.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(ConfigMgr.get("gameWidth"));
    }

}
