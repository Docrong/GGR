package com.boco.eoms.commons.system.priv.util;

import java.util.*;

import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
/**
 * 
 * <p>
 * Title:排序定义规则
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 *2008-0906
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5
 * 
 */
public class Mycomparator implements Comparator {
	public int compare(Object obj1, Object obj2) {
		TawSystemPrivOperation tawSystemPrivOperation1 = (TawSystemPrivOperation) obj1;
		TawSystemPrivOperation tawSystemPrivOperation2 = (TawSystemPrivOperation) obj2;
		if (Integer.parseInt(tawSystemPrivOperation1.getOrderby()) > Integer.parseInt(tawSystemPrivOperation2
				.getOrderby()))
			return 1;
		else
			return 0;
	}

}
