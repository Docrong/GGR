package com.boco.eoms.duty.util;

import java.util.HashMap;

public class  AlternationMap {
	
	private static HashMap alternateMap = new HashMap();

	public static HashMap getAlternateMap() {
		return alternateMap;
	}

	public static void setAlternateMap(HashMap alternateMap) {
		AlternationMap.alternateMap = alternateMap;
	}

}
