package com.boco.eoms.km.file.util;

import com.boco.eoms.base.util.ApplicationContextHolder;

public class KmFilesAttriubuteLocator {

	public static KmFilesAttributes getKmFilesAttributes() {
		KmFilesAttributes attributes = (KmFilesAttributes) ApplicationContextHolder
				.getInstance().getBean("kmFilesAttributes");
		return attributes;
	}
}
