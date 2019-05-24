package com.boco.eoms.km.interfaces.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class KmInterProps {

	// 接口配置文件
	private static Properties props = null;

	public static Properties getConfigure(String configFileName) {
		if(props == null){
			// 如果你指定的路径以/开头，那么就是从ClassPath的起点开始寻找这个路径。
			String resource = "/com/boco/eoms/km/interfaces/config/" + configFileName;
			URL configFileResource = (com.boco.eoms.km.interfaces.util.KmInterMethod.class).getResource(resource);
			props = new Properties();
			try {
				props.load(configFileResource.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return props;
	}
}
