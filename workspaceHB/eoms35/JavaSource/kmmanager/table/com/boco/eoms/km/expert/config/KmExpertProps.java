package com.boco.eoms.km.expert.config;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class KmExpertProps {

    private static Properties props = null;

    public static String getDictRootId(String dictName) {
        String dictId = null;
        if (props == null) {
            // 如果你指定的路径以/开头，那么就是从ClassPath的起点开始寻找这个路径。
            URL configFileResource = (com.boco.eoms.km.expert.config.KmExpertProps.class).getResource("dictconfig.properties");
            props = new Properties();
            try {
                props.load(configFileResource.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dictId = props.getProperty(dictName);
        return dictId;
    }
}
