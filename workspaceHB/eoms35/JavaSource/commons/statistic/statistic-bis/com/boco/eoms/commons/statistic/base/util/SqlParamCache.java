package com.boco.eoms.commons.statistic.base.util;

import java.util.Map;
import java.util.Vector;

public class SqlParamCache {
	private static final Vector cache = new Vector(20010);
	private static final SqlParamCache sqlParamCache = new SqlParamCache();

	private SqlParamCache() {
	}

	public static SqlParamCache Instance() {
		return sqlParamCache;
	}

	public void putParams(String key, Map params) {
		Object[] o = { key, params };
		cache.add(o);
		if (cache.size() == 20000) {
			while (cache.size() > 10000) {
				cache.remove(0);
			}
		}
	}

	public Map getParams(String key) {
		for (int i = 0; i < cache.size(); i++) {
			Object[] obj = (Object[]) cache.get(i);
			if (((String) obj[0]).equals(key))
				return (Map) obj[1];
		}
		return null;
	}
}
