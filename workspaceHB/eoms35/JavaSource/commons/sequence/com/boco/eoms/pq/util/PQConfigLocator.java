package com.boco.eoms.pq.util;

import com.boco.eoms.base.util.ApplicationContextHolder;

public class PQConfigLocator {

	public static PQConfig getPQConfig() {
		return (PQConfig) ApplicationContextHolder.getInstance().getBean(
				"pqConfig");
	}

}
